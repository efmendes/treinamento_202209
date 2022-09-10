package com.indracompany.treinamento.controller.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.indracompany.treinamento.model.dto.ContaClienteDTO;
import com.indracompany.treinamento.model.dto.DepositoDTO;
import com.indracompany.treinamento.model.dto.SaqueDTO;
import com.indracompany.treinamento.model.dto.TransferenciaBancariaDTO;
import com.indracompany.treinamento.model.entity.ContaBancaria;
import com.indracompany.treinamento.model.service.ContaBancariaService;

@RestController
@RequestMapping("rest/contas")
public class ContaBancariaRest extends GenericCrudRest<ContaBancaria, Long, ContaBancariaService>{
	
	@Autowired
	private ContaBancariaService contaBancariaService;
	
	@GetMapping(value = "/buscarContasDoCliente/{cpf}", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<List<ContaClienteDTO>> buscarContasDoCliente(@PathVariable String cpf){
		List<ContaClienteDTO> lista = contaBancariaService.listarContasDoCliente(cpf);
		if (lista == null || lista.isEmpty()) {
			return new ResponseEntity<>( HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(lista, HttpStatus.OK);
	}
	
	@GetMapping(value = "/consultarSaldo/{agencia}/{numeroConta}", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<Double> consultarSaldo (@PathVariable String agencia, @PathVariable String numeroConta){
		ContaBancaria conta = contaBancariaService.carregarConta(agencia, numeroConta);
		return new ResponseEntity<>(conta.getSaldo(), HttpStatus.OK);
	}
	
	@PutMapping(value = "/deposito", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<Void> depositar(@RequestBody DepositoDTO dto){
		contaBancariaService.depositar(dto);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	
	@PutMapping(value = "/sacar", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<Void> sacar (@RequestBody SaqueDTO dto){
		contaBancariaService.sacar(dto);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	
	@PutMapping(value = "/transferencia", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<Void> transferir (@RequestBody TransferenciaBancariaDTO dto){
		contaBancariaService.transferir(dto);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
}
