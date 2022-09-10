package com.indracompany.treinamento.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.indracompany.treinamento.exception.AplicacaoException;
import com.indracompany.treinamento.exception.ExceptionValidacoes;
import com.indracompany.treinamento.model.entity.Cliente;
import com.indracompany.treinamento.model.repository.ClienteRepository;
import com.indracompany.treinamento.util.CpfUtil;

@Service
public class ClienteService extends GenericCrudService<Cliente, Long, ClienteRepository>{

	@Autowired
	private ClienteRepository clientRepository;
	
	public Cliente buscaClientePorCpf(String cpf) {
		
		boolean cpfValido = CpfUtil.validaCPF(cpf);
		
		if(!cpfValido) {
			throw new AplicacaoException(ExceptionValidacoes.ERRO_CPF_INVALIDO, cpf);
		}
		
		Cliente cliente = clientRepository.findByCpf(cpf);
		
		if(cliente == null) {
			throw new AplicacaoException(ExceptionValidacoes.ERRO_CPF_INVALIDO, cpf);
		}
		return cliente;
	}
	
	public List<Cliente> buscaClientePorNome(String nome) {
		
		List<Cliente> cliente = clientRepository.findByNomeIgnoreCaseContainingAndAtivoTrue(nome);
		
		if(cliente == null) {
			throw new AplicacaoException(ExceptionValidacoes.ERRO_OBJETO_NAO_ENCONTRADO, nome);
		}
		
		return cliente;

	} 
	  
}
