package com.indracompany.treinamento.controller.rest;

import com.indracompany.treinamento.model.dto.ClienteDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.indracompany.treinamento.model.entity.Cliente;
import com.indracompany.treinamento.model.service.ClienteService;

import java.util.List;

@RestController
@RequestMapping("rest/clientes")
public class ClienteRest extends GenericCrudRest<Cliente, Long, ClienteService>{

	@Autowired
	private ClienteService clienteService;

	@GetMapping(value = "/buscarPorCpf/{cpf}", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<Cliente> buscarClientePorCpf(@PathVariable String cpf) {
		Cliente cli = clienteService.buscarClientePorCpf(cpf);
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

	@PostMapping
	public ResponseEntity<Cliente> adicionarCliente(@RequestBody Cliente cliente){

		return ResponseEntity.status(HttpStatus.CREATED).body(clienteService.adicionarCliente(cliente));
	}

	@DeleteMapping("/del/{id}")
	public ResponseEntity deletar(@PathVariable Long id){
		clienteService.deletarCLiente(id);
		return ResponseEntity.ok().build();
	}

}
