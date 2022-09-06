package com.indracompany.treinamento.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.indracompany.treinamento.exception.AplicacaoException;
import com.indracompany.treinamento.exception.ExceptionValidacoes;
import com.indracompany.treinamento.model.dto.BuscaClienteDTO;
import com.indracompany.treinamento.model.entity.Cliente;
import com.indracompany.treinamento.model.repository.ClienteRepository;
import com.indracompany.treinamento.util.CpfUtil;

@Service
public class ClienteService extends GenericCrudService<Cliente, Long, ClienteRepository> {

	@Autowired
	private ClienteRepository clienteRepository;

	public Cliente buscarClientePorCpf(String cpf) {

		boolean cpfValido = CpfUtil.validaCPF(cpf);
		if (!cpfValido) {
			throw new AplicacaoException(ExceptionValidacoes.ERRO_CPF_INVALIDO, cpf);
		}
		Cliente cli = clienteRepository.findByCpf(cpf);
		if (cli == null) {
			throw new AplicacaoException(ExceptionValidacoes.ALERTA_NENHUM_REGISTRO_ENCONTRADO, cpf);
		}
		return cli;

	}

	public BuscaClienteDTO buscarClientePorNome(String nome) {

		BuscaClienteDTO cli = clienteRepository.findByNomeContainingIgnoreCase(nome);
		if (cli == null) {
			throw new AplicacaoException(ExceptionValidacoes.ALERTA_NENHUM_REGISTRO_ENCONTRADO, nome);
		}
		return cli;

	}

	public List<Cliente> buscarListaClientesPorNome(String nome) {

		List<Cliente> cli = clienteRepository.findByNomeLike(nome);
		if (cli == null) {
			throw new AplicacaoException(ExceptionValidacoes.ALERTA_NENHUM_REGISTRO_ENCONTRADO, nome);
		}
		return cli;

	}

}
