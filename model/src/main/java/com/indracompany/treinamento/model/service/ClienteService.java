package com.indracompany.treinamento.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.indracompany.treinamento.exception.AplicacaoException;
import com.indracompany.treinamento.exception.ExceptionValidacoes;
import com.indracompany.treinamento.model.entity.Cliente;
import com.indracompany.treinamento.model.repository.ClienteRepository;
import com.indracompany.treinamento.util.CpfUtil;
import java.util.regex.*;

@Service
public class ClienteService extends GenericCrudService<Cliente, Long, ClienteRepository>{
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	public Cliente buscarClientePorCpf(String cpf) {
		boolean cpfValido = CpfUtil.validaCPF(cpf);
		if(!cpfValido) {
			throw new AplicacaoException(ExceptionValidacoes.ERRO_CPF_INVALIDO,cpf);
		}
		Cliente cli = clienteRepository.findByCpf(cpf);
		if(cli == null) {
			throw new AplicacaoException(ExceptionValidacoes.ALERTA_NENHUM_REGISTRO_ENCONTRADO,cpf);
			
		}
		return cli;

	}

	public Cliente buscarClientePorNome(String nome)  {
		String[] numeros = {"0","1","2","3","4","5","6","7","8","9"};
		// Uma melhor forma seria tratar através de REGEX no front-end, tratarei nos sábados de angular
		for(String str : numeros) {
			if(nome.contains(str)){
				throw new AplicacaoException(ExceptionValidacoes.ALERTA_NENHUM_REGISTRO_ENCONTRADO,nome);
			}
		}
		Cliente nomeCli = clienteRepository.findByNome(nome);
		return nomeCli;
		
	}
	
	
	  
}
