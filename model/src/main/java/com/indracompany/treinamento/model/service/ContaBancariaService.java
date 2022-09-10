package com.indracompany.treinamento.model.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mapping.AccessOptions.GetOptions.GetNulls;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.indracompany.treinamento.exception.AplicacaoException;
import com.indracompany.treinamento.exception.ExceptionValidacoes;
import com.indracompany.treinamento.model.dto.ContaClienteDTO;
import com.indracompany.treinamento.model.dto.DepositoDTO;
import com.indracompany.treinamento.model.dto.SaqueDTO;
import com.indracompany.treinamento.model.dto.TransferenciaBancariaDTO;
import com.indracompany.treinamento.model.entity.ContaBancaria;
import com.indracompany.treinamento.model.repository.ContaBancariaRepository;
import com.indracompany.treinamento.util.CpfUtil;

@Service
public class ContaBancariaService extends GenericCrudService<ContaBancaria, Long, ContaBancariaRepository>{
	
	@Autowired
	private ContaBancariaRepository contaBancariaRepository;
	
	public ContaBancaria buscaContaBancariaPorNumeroConta(String numero) {
		return null;
		
	}
	public List<ContaClienteDTO> listarContasDoCliente(String cpf){
		
		boolean cpfValido = CpfUtil.validaCPF(cpf);
		
		if(!cpfValido){
			throw new AplicacaoException(ExceptionValidacoes.ERRO_CPF_INVALIDO, cpf);
		}
		List<ContaClienteDTO> contaClienteDTO = new ArrayList<>();
		
		List<ContaBancaria> contasCliente = contaBancariaRepository.findByClienteCpf(cpf);
		
		for(ContaBancaria c : contasCliente) {
			ContaClienteDTO cliente = new ContaClienteDTO();
			cliente.setAgencia(c.getAgencia());
			cliente.setNumero(c.getNumero());
			cliente.setNomeCliente(c.getCliente().getNome());
			contaClienteDTO.add(cliente);
		}
		return contaClienteDTO;
	}
	
	public void depositar(DepositoDTO deposito) {
		ContaBancaria contaBancaria = this.carregarConta(deposito.getAgencia(), deposito.getNumeroConta());
		if(deposito.getValor() <= 0) {
			throw new AplicacaoException(ExceptionValidacoes.ERRO_SALDO_INEXISTENTE);		
		}
		contaBancaria.setSaldo(contaBancaria.getSaldo() + deposito.getValor());
		super.salvar(contaBancaria);
		
	}
	public void sacar(SaqueDTO saque) {
		ContaBancaria contaBancaria = this.carregarConta(saque.getAgencia(), saque.getNumeroConta());
		if(contaBancaria.getSaldo() < saque.getValor()) {
			throw new AplicacaoException(ExceptionValidacoes.ERRO_SALDO_INEXISTENTE);		
		}
		contaBancaria.setSaldo(contaBancaria.getSaldo() - saque.getValor());
		super.salvar(contaBancaria);
		
	}
	
	@Transactional(rollbackFor = Exception.class)
	public void transferir(TransferenciaBancariaDTO transfDTO) {
		
		SaqueDTO saqueDto = new SaqueDTO();
		saqueDto.setAgencia(transfDTO.getAgenciaOrigem());
		saqueDto.setNumeroConta(transfDTO.getNumeroContaOrigem());
		saqueDto.setValor(transfDTO.getValor());
		
		this.sacar(saqueDto);
		
		DepositoDTO depositoDto = new DepositoDTO();
		depositoDto.setAgencia(transfDTO.getAgenciaDestino());
		depositoDto.setNumeroConta(transfDTO.getNumeroContaDestino());
		depositoDto.setValor(transfDTO.getValor());
		
		this.depositar(depositoDto);
		
	}
	
	public ContaBancaria carregarConta(String agencia, String numero) {
		
		ContaBancaria conta = contaBancariaRepository.findByAgenciaAndNumero(agencia, numero);
		
		if(conta == null) {
			throw new AplicacaoException(ExceptionValidacoes.ERRO_CONTA_INVALIDA);		
			}
		return conta;
		
	}
}
