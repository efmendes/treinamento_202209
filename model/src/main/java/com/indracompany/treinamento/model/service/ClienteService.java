package com.indracompany.treinamento.model.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
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
	private ClienteRepository clienteRepository;
	
	public ClienteDTO buscarClientePorCpf(String cpf) {
		
		boolean cpfValido = CpfUtil.validaCPF(cpf);
		if (!cpfValido) {
			throw new AplicacaoException(ExceptionValidacoes.ERRO_CPF_INVALIDO, cpf);
		}
		
		Cliente cli = clienteRepository.findByCpf(cpf);
		
		if (cli == null) {
			throw new AplicacaoException(ExceptionValidacoes.ALERTA_NENHUM_REGISTRO_ENCONTRADO, cpf);
		}
		
		ClienteDTO dto = new ClienteDTO();
		BeanUtils.copyProperties(cli, dto);
		dto.setCpfMacarado(cli.getCpf().substring(0, 3)+"***");
		
		return dto;
		
	}
	
public List<ClienteDTO> buscarClientePorNome(String nome) {
	
		if(nome.isEmpty() || StringUtils.isNumeric(nome) ) {
			throw new AplicacaoException(ExceptionValidacoes.ERRO_NOME_INVALIDO, nome);
		}
			
		List<Cliente> listCli = clienteRepository.findByNomeContainingIgnoreCaseAndAtivoTrue(nome);
		
		if (listCli.isEmpty()) {
			throw new AplicacaoException(ExceptionValidacoes.ALERTA_NENHUM_REGISTRO_ENCONTRADO, nome);
		}
//		
		List<ClienteDTO> listDTO =new ArrayList<>();
		for(Cliente c : listCli) {
			ClienteDTO dto = new ClienteDTO();
			BeanUtils.copyProperties(c, dto);
			dto.setCpfMacarado(c.getCpf().substring(0, 3)+"***");
			listDTO.add(dto);
		}
//		
		return listDTO;
		
	}
	  
}
