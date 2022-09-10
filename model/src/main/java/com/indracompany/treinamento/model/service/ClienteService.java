package com.indracompany.treinamento.model.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.indracompany.treinamento.exception.AplicacaoException;
import com.indracompany.treinamento.exception.ExceptionValidacoes;
import com.indracompany.treinamento.model.dto.ClienteDTO;
import com.indracompany.treinamento.model.entity.Cliente;
import com.indracompany.treinamento.model.repository.ClienteRepository;
import com.indracompany.treinamento.util.CpfUtil;

@Service
public class ClienteService extends GenericCrudService<Cliente, Long, ClienteRepository>{

	@Autowired
	private ClienteRepository clientRepository;
	
	public ClienteDTO buscaClientePorCpf(String cpf) {
		
		boolean cpfValido = CpfUtil.validaCPF(cpf);
		
		if(!cpfValido) {
			throw new AplicacaoException(ExceptionValidacoes.ERRO_CPF_INVALIDO, cpf);
		}
		
		ClienteDTO clienteDTO = new ClienteDTO();
		
		Cliente cliente = clientRepository.findByCpf(cpf);
		
		if(cliente == null) {
			throw new AplicacaoException(ExceptionValidacoes.ERRO_CPF_INVALIDO, cpf);
		}
		BeanUtils.copyProperties(cliente, clienteDTO);
		
//		clienteDTO.setNome(cliente.getNome());
//		clienteDTO.setCpf(cliente.getCpf());
//		clienteDTO.setObservacoes(cliente.getObservacoes());
		
		return clienteDTO;
	}
	
	
	public List<ClienteDTO> buscaClientePorNome(String nome) {
		
		List<ClienteDTO> listaClientesDTO = new ArrayList<>();
		
		List<Cliente> cliente = clientRepository.findByNomeIgnoreCaseContainingAndAtivoTrue(nome);
		for(Cliente c : cliente) {
			ClienteDTO clienteDTO = new ClienteDTO();
			BeanUtils.copyProperties(c, clienteDTO);
			listaClientesDTO.add(clienteDTO);
		}
		
		if(cliente == null) {
			throw new AplicacaoException(ExceptionValidacoes.ERRO_OBJETO_NAO_ENCONTRADO, nome);
		}
		
		return listaClientesDTO;

	} 
	  
}
