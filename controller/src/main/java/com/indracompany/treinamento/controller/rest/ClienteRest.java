package com.indracompany.treinamento.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.indracompany.treinamento.model.entity.Cliente;
import com.indracompany.treinamento.model.service.ClienteService;

@RestController
@RequestMapping("rest/clientes")
public class ClienteRest extends GenericCrudRest<Cliente, Long, ClienteService>{
	
	@Autowired
	private ClienteService clienteService;
	
	@GetMapping(value = "/buscarPorCpf/{cpf}", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity <Cliente> buscaClientePorCpf(@PathVariable String cpf) {
		Cliente client = clienteService.buscaClientePorCpf(cpf);
		return new ResponseEntity<>(client, HttpStatus.OK);
	}
	
	@GetMapping(value = "/buscarPorNome/{nome}", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity <Cliente> buscaClientePorNome(@PathVariable String nome) {
		Cliente client = clienteService.buscaClientePorNome(nome);
		return new ResponseEntity<>(client, HttpStatus.OK);
	}
}
