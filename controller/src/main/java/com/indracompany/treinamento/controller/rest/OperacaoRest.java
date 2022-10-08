package com.indracompany.treinamento.controller.rest;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.indracompany.treinamento.model.entity.Operacao;
import com.indracompany.treinamento.model.service.OperacaoService;

@RestController
@RequestMapping("rest/operacoes")
public class OperacaoRest extends GenericCrudRest<Operacao, Long, OperacaoService>{

	@Autowired
	private OperacaoService operacaoService;
	
	@GetMapping(value = "/extrato/{agencia}/{numero}", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<List<Operacao>> extratoDoCliente(@PathVariable String agencia, @PathVariable String numero){
		List<Operacao> lista = operacaoService.buscarOperacoesPorCliente(agencia, numero);
		if (lista == null || lista.isEmpty()) {
			return new ResponseEntity<>( HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(lista, HttpStatus.OK);
	}
	
	@GetMapping(value = "/extrato/{agencia}/{numero}/{dataInicial}/{dataFinal}", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<List<Operacao>> extratoPorIntervaloData(@PathVariable String agencia, @PathVariable String numero,
									@PathVariable @DateTimeFormat(pattern="yyyy-MM-dd") Date dataInicial,
									@PathVariable @DateTimeFormat(pattern="yyyy-MM-dd") Date dataFinal){
		dataFinal.setHours(23);
		dataFinal.setMinutes(59);
		dataFinal.setSeconds(59);
		if(dataInicial.compareTo(dataFinal) > 0) {
			return new ResponseEntity<>( HttpStatus.BAD_REQUEST);
		}
		
		List<Operacao> lista = operacaoService.buscarOperacoesPorIntervaloData(agencia, numero, dataInicial, dataFinal);
		
		if (lista == null || lista.isEmpty()) {
			return new ResponseEntity<>( HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(lista, HttpStatus.OK);
	}
}
