package com.indracompany.treinamento.controller.rest;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.indracompany.treinamento.exception.AplicacaoException;
import com.indracompany.treinamento.exception.ExceptionValidacoes;
import com.indracompany.treinamento.model.dto.UsuarioDTO;
import com.indracompany.treinamento.model.service.UsuarioService;
import com.indracompany.treinamento.util.Constantes;

@RestController()
@CrossOrigin(origins = "*")
@RequestMapping("rest/autenticacao")
public class AutenticacaoRest {


	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private HttpServletRequest request;

	@RequestMapping(value = "/login/{username}/{password}", method = RequestMethod.GET)
	public ResponseEntity<UsuarioDTO> login(final @PathVariable String username, @PathVariable String password) {
		UsuarioDTO user = usuarioService.login(username, password);
		if (user == null) {
			throw new AplicacaoException(ExceptionValidacoes.ERRO_LOGIN_SENHA_INVALIDO);
		}else {
			request.getSession().setAttribute(Constantes.USUARIO_LOGADO, user);
			return new ResponseEntity<UsuarioDTO>(user,HttpStatus.OK);
		}
	}
	
	
	@RequestMapping(value = "/usuarioLogado", method = RequestMethod.GET)
	public ResponseEntity<UsuarioDTO> usuarioLogado() {
		UsuarioDTO user = (UsuarioDTO) request.getSession().getAttribute(Constantes.USUARIO_LOGADO);
		return new ResponseEntity<UsuarioDTO>(user,HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public void logout() {
		request.getSession().removeAttribute(Constantes.USUARIO_LOGADO);
		request.getSession().invalidate();
	}
	
	

}
