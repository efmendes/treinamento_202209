package com.indracompany.treinamento.model.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.indracompany.treinamento.exception.AplicacaoException;
import com.indracompany.treinamento.exception.ExceptionValidacoes;
import com.indracompany.treinamento.model.dto.ContaClienteDTO;
import com.indracompany.treinamento.model.dto.DepositoDTO;
import com.indracompany.treinamento.model.dto.SaqueDTO;
import com.indracompany.treinamento.model.dto.TransferenciaBancariaDTO;
import com.indracompany.treinamento.model.entity.ContaBancaria;
import com.indracompany.treinamento.model.entity.OperacaoConta;
import com.indracompany.treinamento.model.entity.TipoTransacao;
import com.indracompany.treinamento.model.repository.ContaBancariaRepository;
import com.indracompany.treinamento.model.repository.OperacaoContaRepository;
import com.indracompany.treinamento.util.CpfUtil;

@Service
public class ContaBancariaService extends GenericCrudService<ContaBancaria, Long, ContaBancariaRepository> {

	@Autowired
	private ClienteService clienteService;

	@Autowired
	private ContaBancariaRepository contaBancariaRepository;

	@Autowired
	private OperacaoContaRepository operacaoContaRepository;

	public List<ContaClienteDTO> listarContasDoCliente(String cpf) {

		boolean cpfValido = CpfUtil.validaCPF(cpf);
		if (!cpfValido) {
			throw new AplicacaoException(ExceptionValidacoes.ERRO_CPF_INVALIDO, cpf);
		}

		List<ContaBancaria> contasBancarias = contaBancariaRepository.findByClienteCpf(cpf);

		if (contasBancarias == null || contasBancarias.isEmpty()) {
			throw new AplicacaoException(ExceptionValidacoes.ALERTA_NENHUM_REGISTRO_ENCONTRADO);
		}

		List<ContaClienteDTO> listaRetornoDTO = new ArrayList<>();

		for (ContaBancaria conta : contasBancarias) {
			ContaClienteDTO dto = new ContaClienteDTO();
			BeanUtils.copyProperties(conta, dto);
			dto.setNomeCliente(conta.getCliente().getNome());
			listaRetornoDTO.add(dto);
		}

		return listaRetornoDTO;
	}

	public void depositar(DepositoDTO dto) {
		ContaBancaria contaBancaria = this.carregarConta(dto.getAgencia(), dto.getNumeroConta());
		contaBancaria.setSaldo(contaBancaria.getSaldo() + dto.getValor());
		super.salvar(contaBancaria);
		this.adicionaOperacaoNoExtrato(TipoTransacao.DEPOSITO, dto.getValor(), dto.getAgencia(), dto.getNumeroConta());
	}

	public void sacar(SaqueDTO dto) {
		ContaBancaria contaBancaria = this.carregarConta(dto.getAgencia(), dto.getNumeroConta());
		if (contaBancaria.getSaldo() < dto.getValor()) {
			throw new AplicacaoException(ExceptionValidacoes.ERRO_SALDO_INEXISTENTE);
		}
		super.salvar(contaBancaria);
		contaBancaria.setSaldo(contaBancaria.getSaldo() - dto.getValor());
		this.adicionaOperacaoNoExtrato(TipoTransacao.SAQUE, dto.getValor(), dto.getAgencia(), dto.getNumeroConta());
	}

	@Transactional(rollbackFor = Exception.class)
	public void transferir(TransferenciaBancariaDTO transferenciaDto) {

		SaqueDTO saqueDto = new SaqueDTO();
		saqueDto.setAgencia(transferenciaDto.getAgenciaOrigem());
		saqueDto.setNumeroConta(transferenciaDto.getNumeroContaOrigem());
		saqueDto.setValor(transferenciaDto.getValor());

		this.sacar(saqueDto);

		DepositoDTO depositoDto = new DepositoDTO();
		depositoDto.setAgencia(transferenciaDto.getAgenciaDestino());
		depositoDto.setNumeroConta(transferenciaDto.getNumeroContaDestino());
		depositoDto.setValor(transferenciaDto.getValor());

		this.depositar(depositoDto);
		
		this.adicionaOperacaoNoExtrato(TipoTransacao.TRANSFERENCIA, transferenciaDto.getValor(), transferenciaDto.getAgenciaOrigem(), transferenciaDto.getNumeroContaOrigem());
	}

	public ContaBancaria carregarConta(String agencia, String numero) {
		ContaBancaria conta = contaBancariaRepository.findByAgenciaAndNumero(agencia, numero);

		if (conta == null) {
			throw new AplicacaoException(ExceptionValidacoes.ERRO_CONTA_INVALIDA);
		}

		return conta;
	}

	public void adicionaOperacaoNoExtrato(TipoTransacao tipoTransacao, double valorTransacao, String agencia, String numero) {

		Date dataDaOperacao = new Date();
		OperacaoConta operacaoConta = new OperacaoConta();
		ContaBancaria contaBancaria = contaBancariaRepository.findByAgenciaAndNumero(agencia, numero);
		operacaoConta.setContaBancaria(contaBancaria);
		operacaoConta.setTipoTransacao(tipoTransacao);
		operacaoConta.setValorTransacao(valorTransacao);
		operacaoConta.setDataTransacao(dataDaOperacao);
		operacaoContaRepository.save(operacaoConta);
		
	}
}
