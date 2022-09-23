package com.indracompany.treinamento.model.service;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.indracompany.treinamento.exception.AplicacaoException;
import com.indracompany.treinamento.exception.ExceptionValidacoes;
import com.indracompany.treinamento.model.dto.ClienteDTO;
import com.indracompany.treinamento.model.dto.ContaClienteDTO;
import com.indracompany.treinamento.model.dto.DepositoDTO;
import com.indracompany.treinamento.model.dto.SaqueDTO;
import com.indracompany.treinamento.model.dto.TransacaoDTO;
import com.indracompany.treinamento.model.dto.TransferenciaBancariaDTO;
import com.indracompany.treinamento.model.entity.Cliente;
import com.indracompany.treinamento.model.entity.ContaBancaria;
import com.indracompany.treinamento.model.entity.Transacao;
import com.indracompany.treinamento.model.repository.ContaBancariaRepository;
import com.indracompany.treinamento.model.repository.TransacaoRepository;
import com.indracompany.treinamento.util.CpfUtil;

@Service
public class ContaBancariaService extends GenericCrudService<ContaBancaria, Long, ContaBancariaRepository>{
	
	@Autowired
	private ClienteService clienteService;
	
	@Autowired
	private ContaBancariaRepository contaBancariaRepository;
	
	@Autowired
	private TransacaoRepository transacaoRepository;
	
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
		super.salvar(contaBancaria);
		Transacao deposito = new Transacao();
		deposito.setDescricao("DEPOSITO");
		deposito.setHorario(new Date(System.currentTimeMillis()));
		deposito.setValor(dto.getValor());
		deposito.setContaBancaria(contaBancaria);
		this.transacaoRepository.save(deposito);
	}
	
	public void sacar(SaqueDTO dto) {
		ContaBancaria contaBancaria = this.carregarConta(dto.getAgencia(), dto.getNumeroConta());
		if (contaBancaria.getSaldo() < dto.getValor()) {
			throw new AplicacaoException(ExceptionValidacoes.ERRO_SALDO_INEXISTENTE);
		}
		contaBancaria.setSaldo(contaBancaria.getSaldo() - dto.getValor());
		super.salvar(contaBancaria);
		Transacao transacao = new Transacao();
		transacao.setDescricao("SAQUE");
		transacao.setHorario(new Date(System.currentTimeMillis()));
		transacao.setValor(dto.getValor()*-1);
		transacao.setContaBancaria(contaBancaria);
		this.transacaoRepository.save(transacao);
	}
	
	@Transactional(rollbackFor = Exception.class)
	public void transferir(TransferenciaBancariaDTO transferenciaDto) {
		
		ContaBancaria contaOrigem = this.carregarConta(transferenciaDto.getAgenciaOrigem(), transferenciaDto.getNumeroContaOrigem());
		if (contaOrigem.getSaldo() < transferenciaDto.getValor()) {
			throw new AplicacaoException(ExceptionValidacoes.ERRO_SALDO_INEXISTENTE);
		}
		contaOrigem.setSaldo(contaOrigem.getSaldo() - transferenciaDto.getValor());
		super.salvar(contaOrigem);
		Transacao transferencia1 = new Transacao();
		transferencia1.setDescricao("TRANSFERENCIA");
		transferencia1.setHorario(new Date(System.currentTimeMillis()));
		transferencia1.setValor(transferenciaDto.getValor()*-1);
		transferencia1.setContaBancaria(contaOrigem);
		this.transacaoRepository.save(transferencia1);
		
		ContaBancaria contaDestino = this.carregarConta(transferenciaDto.getAgenciaDestino(), transferenciaDto.getNumeroContaDestino());
		contaOrigem.setSaldo(contaDestino.getSaldo() + transferenciaDto.getValor());
		super.salvar(contaDestino);
		Transacao transferencia2 = new Transacao();
		transferencia2.setDescricao("TRANSFERENCIA");
		transferencia2.setHorario(new Date(System.currentTimeMillis()));
		transferencia2.setValor(transferenciaDto.getValor());
		transferencia2.setContaBancaria(contaDestino);
		this.transacaoRepository.save(transferencia2);
	}
	
	public ContaBancaria carregarConta(String agencia, String numero) {
		ContaBancaria conta = contaBancariaRepository.findByAgenciaAndNumero(agencia, numero);
		
		if (conta == null) {
			throw new AplicacaoException(ExceptionValidacoes.ERRO_CONTA_INVALIDA);
		}
		
		return conta;
	}
	
	
	public List<TransacaoDTO> extratoByPeriodo(String agencia, String numero, Date dataInicio, Date dataFinal){
		ContaBancaria conta = this.carregarConta(agencia, numero);
		System.out.println(dataInicio);
		
		
		List<Transacao> listaTransacao = this.transacaoRepository.extratoByPeriodo(conta.getId(),dataInicio, dataFinal);
		List<TransacaoDTO> listaRetornoDto = new ArrayList<>();
		
		for (Transacao c : listaTransacao) {
			TransacaoDTO dto = new TransacaoDTO();
			BeanUtils.copyProperties(c, dto);
			listaRetornoDto.add(dto);
		}
		
		return listaRetornoDto;
	}
}
