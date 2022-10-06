package com.indracompany.treinamento.model.service;

import com.indracompany.treinamento.exception.AplicacaoException;
import com.indracompany.treinamento.exception.ExceptionValidacoes;
import com.indracompany.treinamento.model.dto.ClienteDTO;
import com.indracompany.treinamento.model.entity.Cliente;
import com.indracompany.treinamento.model.entity.ContaBancaria;
import com.indracompany.treinamento.model.repository.ClienteRepository;
import com.indracompany.treinamento.model.repository.ContaBancariaRepository;
import com.indracompany.treinamento.util.CpfUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteService extends GenericCrudService<Cliente, Long, ClienteRepository>{

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private ContaBancariaRepository contaRepository;

	public Cliente buscarClientePorCpf(String cpf) {

		boolean cpfValido = CpfUtil.validaCPF(cpf);
		if (!cpfValido) {
			throw new AplicacaoException(ExceptionValidacoes.ERRO_CPF_INVALIDO, cpf);
		}
		Cliente cli = clienteRepository.findByCpf(cpf)
				.orElseThrow(() -> new AplicacaoException(ExceptionValidacoes.ALERTA_NENHUM_REGISTRO_ENCONTRADO, cpf));


		return cli;

	}

	public List<ClienteDTO> buscarClientePorNome(String nome) {
		if (StringUtils.isBlank(nome)
				|| StringUtils.isNumeric(nome)) {
			throw new AplicacaoException(ExceptionValidacoes.ERRO_VALIDACAO, nome);
		}
		List<Cliente> listaCliente = clienteRepository.findByNomeContainingIgnoreCaseAndAtivoTrue(nome);
		List<ClienteDTO> listaRetornoDto = new ArrayList<>();
		for (Cliente c : listaCliente) {
			ClienteDTO dto = new ClienteDTO();
			BeanUtils.copyProperties(c, dto);
			dto.setCpfMascarado(c.getCpf().substring(0, 3)+"***");
			listaRetornoDto.add(dto);
		}

		return listaRetornoDto;
	}

	public Cliente adicionarCliente(Cliente cliente){
		boolean cpf = CpfUtil.validaCPF(cliente.getCpf());
		if(!cpf){
			throw new AplicacaoException(ExceptionValidacoes.ERRO_CPF_INVALIDO, cliente.getCpf());
		}

		clienteRepository.save(cliente);

		return cliente;
	}

	public void deletarCLiente(Long id){
		Cliente exists = clienteRepository.findById(id)
				.orElseThrow(() -> new AplicacaoException(ExceptionValidacoes.ALERTA_NENHUM_REGISTRO_ENCONTRADO));

		Optional<List<ContaBancaria>> contasList = contaRepository.findByClienteCpf(exists.getCpf());
		if(contasList.isPresent()){
			contasList.get().forEach(e -> contaRepository.delete(e));
		}

		clienteRepository.delete(exists);
	}
	  
}
