package com.indracompany.treinamento.model.service;

import org.springframework.stereotype.Service;

import com.indracompany.treinamento.exception.AplicacaoException;
import com.indracompany.treinamento.exception.ExceptionValidacoes;
import com.indracompany.treinamento.model.dto.UsuarioDTO;
import com.indracompany.treinamento.model.entity.Usuario;
import com.indracompany.treinamento.model.repository.UsuarioRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UsuarioService extends GenericCrudService<Usuario, Long, UsuarioRepository> {

	
	
	
	public UsuarioDTO login(String username, String password) {

		if (username.equals("admin") && password.equals("1234")) {
			UsuarioDTO user = new UsuarioDTO();
			user.setLogin("admin");
			user.setEmail("admin@indracompany.com");
			user.setNome("Administrador");
			return user;
		} else {
			throw new AplicacaoException(ExceptionValidacoes.ERRO_LOGIN_SENHA_INVALIDO);
		}
	}

}
