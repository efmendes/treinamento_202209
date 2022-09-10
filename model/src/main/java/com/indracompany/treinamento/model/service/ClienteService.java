package com.indracompany.treinamento.model.service;

<<<<<<< HEAD
import java.util.Optional;

=======
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
>>>>>>> 3fce7e377d28f554e6851157ce8cddb9cfe1a784
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.indracompany.treinamento.exception.AplicacaoException;
import com.indracompany.treinamento.exception.ExceptionValidacoes;
<<<<<<< HEAD
=======
import com.indracompany.treinamento.model.dto.ClienteDTO;
>>>>>>> 3fce7e377d28f554e6851157ce8cddb9cfe1a784
import com.indracompany.treinamento.model.entity.Cliente;
import com.indracompany.treinamento.model.repository.ClienteRepository;
import com.indracompany.treinamento.util.CpfUtil;

@Service
public class ClienteService extends GenericCrudService<Cliente, Long, ClienteRepository>{

<<<<<<< HEAD
    @Autowired
    private ClienteRepository clienteRepository;

    public Cliente buscarClientePorCpf(String cpf) {
        boolean cpfValido = CpfUtil.validaCPF(cpf);
        if(!cpfValido) {
            throw new AplicacaoException(ExceptionValidacoes.ERRO_CPF_INVALIDO, cpf);

        }
        Cliente cli = clienteRepository.findByCpf(cpf);
        if(cli == null) {
            throw new AplicacaoException(ExceptionValidacoes.ALERTA_NENHUM_REGISTRO_ENCONTRADO, cpf);
        }
        return cli;
    }


    public Cliente buscarClientePorNome(String nome) {
      Cliente cli = clienteRepository.findByNome(nome);
      if(cli == null){
            throw new AplicacaoException(ExceptionValidacoes.ALERTA_NENHUM_REGISTRO_ENCONTRADO, nome);
        }
        return cli;
    }

}
=======
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
		dto.setCpfMascarado(cli.getCpf().substring(0, 3)+"***");
		return dto;
		
	}
	  
	public List<ClienteDTO> buscarClientePorNome(String nome) {
		if (StringUtils.isBlank(nome) 
				|| StringUtils.isNumeric(nome)) {
			throw new AplicacaoException(ExceptionValidacoes.ERRO_NOME_INVALIDO, nome);
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
}
>>>>>>> 3fce7e377d28f554e6851157ce8cddb9cfe1a784
