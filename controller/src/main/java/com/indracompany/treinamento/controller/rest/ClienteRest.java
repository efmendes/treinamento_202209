package com.indracompany.treinamento.controller.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.indracompany.treinamento.model.dto.ClienteDTO;
import com.indracompany.treinamento.model.entity.Cliente;
import com.indracompany.treinamento.model.service.ClienteService;

@RestController
@RequestMapping("rest/clientes")
public class ClienteRest extends GenericCrudRest<Cliente, Long, ClienteService>{
	
	@Autowired
	private ClienteService clienteService;
	
	@GetMapping(value = "/buscarPorCpf/{cpf}", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<ClienteDTO> buscarClientePorCpf(@PathVariable String cpf) {
		ClienteDTO cli = clienteService.buscarClientePorCpf(cpf);
		return new ResponseEntity<>(cli, HttpStatus.OK);
	}
	
	@GetMapping(value = "/buscarPorNome/{nome}", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<List<ClienteDTO>> buscarClientePorNomes(@PathVariable String nome){
		List<ClienteDTO> lista = clienteService.buscarClientePorNome(nome);
		if (lista == null || lista.isEmpty()) {
			return new ResponseEntity<>( HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(lista, HttpStatus.OK);
	}
	

}
