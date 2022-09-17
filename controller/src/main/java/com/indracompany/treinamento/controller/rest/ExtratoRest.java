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

import com.indracompany.treinamento.model.entity.Extrato;
import com.indracompany.treinamento.model.service.ExtratoService;

@RestController
@RequestMapping("rest/extratos")

public class ExtratoRest extends GenericCrudRest<Extrato, Long, ExtratoService> {

	@Autowired
	private ExtratoService extratoService;
	
	@GetMapping(value = "/buscarExtrato/{agencia}/{numero}/{dataInicio}/{dataFinal}", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<List<Extrato>> pesquisarExtrato(@PathVariable String agencia, @PathVariable String numero, @PathVariable String dataInicio, @PathVariable String dataFinal){
		List<Extrato> lista = extratoService.pesquisarExtrato(agencia, numero, dataInicio, dataFinal);
	
		if (lista == null || lista.isEmpty()) {
			return new ResponseEntity<>( HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(lista, HttpStatus.OK);
	}
	
}
