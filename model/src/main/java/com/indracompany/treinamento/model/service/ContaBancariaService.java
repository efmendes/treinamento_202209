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
import com.indracompany.treinamento.model.entity.Operacao;
import com.indracompany.treinamento.model.repository.ContaBancariaRepository;
import com.indracompany.treinamento.model.repository.OperacaoRepository;
import com.indracompany.treinamento.util.CpfUtil;

@Service
public class ContaBancariaService extends GenericCrudService<ContaBancaria, Long, ContaBancariaRepository>{
	
	@Autowired
	private ClienteService clienteService;
	
	@Autowired
	private ContaBancariaRepository contaBancariaRepository;
	
	@Autowired
	private OperacaoRepository operacaoRepository;
	
	public List<ContaClienteDTO> listarContasDoCliente(String cpf){
		
		boolean cpfValido = CpfUtil.validaCPF(cpf);
		if (!cpfValido) {
			throw new AplicacaoException(ExceptionValidacoes.ERRO_CPF_INVALIDO, cpf);
		}
		
		List<ContaBancaria> contasBancarias = contaBancariaRepository.findByClienteCpf(cpf);
		
		if (contasBancarias== null || contasBancarias.isEmpty()) {
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
		
		Operacao op = new Operacao();
		op.setAgencia(contaBancaria.getAgencia());
		op.setNumero(contaBancaria.getNumero());
		op.setDate(new Date());
		op.setOperationType("Deposito");
		op.setValue(dto.getValor());
		operacaoRepository.save(op);
		
		super.salvar(contaBancaria);
	}
	
	public void depositarPorTransferencia(DepositoDTO dto) {
		ContaBancaria contaBancaria = this.carregarConta(dto.getAgencia(), dto.getNumeroConta());
		contaBancaria.setSaldo(contaBancaria.getSaldo() + dto.getValor());
		
		super.salvar(contaBancaria);
	}
	
	public void sacar(SaqueDTO dto) {
		ContaBancaria contaBancaria = this.carregarConta(dto.getAgencia(), dto.getNumeroConta());
		if (contaBancaria.getSaldo() < dto.getValor()) {
			throw new AplicacaoException(ExceptionValidacoes.ERRO_SALDO_INEXISTENTE);
		}
		contaBancaria.setSaldo(contaBancaria.getSaldo() - dto.getValor());
		
		Operacao op = new Operacao();
		op.setAgencia(contaBancaria.getAgencia());
		op.setNumero(contaBancaria.getNumero());
		op.setDate(new Date());
		op.setOperationType("Saque");
		op.setValue(dto.getValor());
		operacaoRepository.save(op);
		
		super.salvar(contaBancaria);
	}
	
	public void sacarPorTransferencia(SaqueDTO dto) {
		ContaBancaria contaBancaria = this.carregarConta(dto.getAgencia(), dto.getNumeroConta());
		if (contaBancaria.getSaldo() < dto.getValor()) {
			throw new AplicacaoException(ExceptionValidacoes.ERRO_SALDO_INEXISTENTE);
		}
		contaBancaria.setSaldo(contaBancaria.getSaldo() - dto.getValor());
		
		super.salvar(contaBancaria);
	}
	
	@Transactional(rollbackFor = Exception.class)
	public void transferir(TransferenciaBancariaDTO transferenciaDto) {
		
		SaqueDTO saqueDto = new SaqueDTO();
		saqueDto.setAgencia(transferenciaDto.getAgenciaOrigem());
		saqueDto.setNumeroConta(transferenciaDto.getNumeroContaOrigem());
		saqueDto.setValor(transferenciaDto.getValor());

		this.sacarPorTransferencia(saqueDto);
		
		DepositoDTO depositoDto = new DepositoDTO();
		depositoDto.setAgencia(transferenciaDto.getAgenciaDestino());
		depositoDto.setNumeroConta(transferenciaDto.getNumeroContaDestino());
		depositoDto.setValor(transferenciaDto.getValor());
		
		this.depositarPorTransferencia(depositoDto);	
		
		
		ContaBancaria contaBancariaDeOrigem = this.carregarConta(transferenciaDto.getAgenciaOrigem(), transferenciaDto.getNumeroContaOrigem());
		Operacao opEnviada = new Operacao();
		opEnviada.setAgencia(contaBancariaDeOrigem.getAgencia());
		opEnviada.setNumero(contaBancariaDeOrigem.getNumero());
		opEnviada.setDate(new Date());
		opEnviada.setOperationType("Transferencia enviada");
		opEnviada.setValue(saqueDto.getValor());
		operacaoRepository.save(opEnviada);
		
		ContaBancaria contaBancariaDeDestino = this.carregarConta(transferenciaDto.getAgenciaDestino(), transferenciaDto.getNumeroContaDestino());
		Operacao opRecebida = new Operacao();
		opRecebida.setAgencia(contaBancariaDeDestino.getAgencia());
		opRecebida.setNumero(contaBancariaDeDestino.getNumero());
		opRecebida.setDate(new Date());
		opRecebida.setOperationType("Transfencia recebida");
		opRecebida.setValue(depositoDto.getValor());
		operacaoRepository.save(opRecebida);
		
	}
	
	public ContaBancaria carregarConta(String agencia, String numero) {
		ContaBancaria conta = contaBancariaRepository.findByAgenciaAndNumero(agencia, numero);
		
		if (conta == null) {
			throw new AplicacaoException(ExceptionValidacoes.ERRO_CONTA_INVALIDA);
		}
		
		return conta;
	}
}
