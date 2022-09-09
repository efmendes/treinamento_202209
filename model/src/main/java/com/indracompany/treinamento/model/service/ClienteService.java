package com.indracompany.treinamento.model.service;

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
		
	public Cliente buscarClientePorNome(String nome){
		
		Cliente cli = clienteRepository.findByNome(nome.strip());
		
		boolean temNumeros = false;
		boolean temCaracInvalidos = false;
		
		/* 
		 * Para que esse FOR? - Foi usado para verificar se a
		 * string pesquisada no lá no swagger tem número. 
		 */
		for(int i = 0; i < 10; i++) {
			if(nome.contains(Integer.toString(i))){
				temNumeros = true;
			}
		}
	
		/* 
		 * Para que esse IF com esse matches esquisito? - Esse if foi usado para
		 * ver se tem algum caracter indesejado na hora de pesquisar a string nome
		 * (por exemplo: & $ # $). Esse conjunto de letras esquisito é uma regex
		 * (expressão regular) que peguei na internet para validar se a formatação 
		 * do nome é válida (aceita os acentos da língua portuguesa, mas não caracteres
		 * impróprios para nomes).
		 */
		if(!nome.matches("[A-Za-záàâãéèêíïóôõöúçñÁÀÂÃÉÈÍÏÓÔÕÖÚÇÑ ]+$")){
			temCaracInvalidos = true;
		}
		
	
		if(temNumeros || temCaracInvalidos){
			throw new AplicacaoException(ExceptionValidacoes.ERRO_NOME_COM_FORMATACAO_INVALIDA, nome);
		}
		
		if (cli == null) {
			throw new AplicacaoException(ExceptionValidacoes.ALERTA_NENHUM_REGISTRO_ENCONTRADO, nome);
		}
		
		return cli;
	}
	
}
