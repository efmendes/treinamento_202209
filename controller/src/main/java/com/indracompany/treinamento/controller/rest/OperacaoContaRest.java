package com.indracompany.treinamento.controller.rest;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.indracompany.treinamento.model.dto.OperacaoContaDTO;
import com.indracompany.treinamento.model.entity.OperacaoConta;
import com.indracompany.treinamento.model.service.OperacaoContaService;

@RestController
@RequestMapping("rest/operacaoConta")
public class OperacaoContaRest extends GenericCrudRest<OperacaoConta, Long, OperacaoContaService> {

	@Autowired
	OperacaoContaService operacaoContaService;

	@GetMapping(value = "/listaExtratoDoCliente/{dataInicio}/{dataFim}/{agencia}/{numero}", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<List<OperacaoContaDTO>> listaExtratoPorDataEConta(
			@PathVariable @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm") Date dataInicio,
			@PathVariable @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm") Date dataFim, @PathVariable String agencia,
			@PathVariable String numero) {
		List<OperacaoContaDTO> listaDeExtratos = operacaoContaService.listaExtratoDoCliente(dataInicio, dataFim, agencia,
				numero);

		if (listaDeExtratos == null || listaDeExtratos.isEmpty()) {
			return new ResponseEntity<>(listaDeExtratos, HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<>(listaDeExtratos, HttpStatus.OK);
	}


	
	//Para a consulta ser exata digitar data e hora na requisição. EXEMPLO : de 13/09/2022 10:16:50 a 16/09/2022 10:17:00
	@GetMapping(value = "/listaExtratosEntreDatas/{dataInicio}/{dataFim}", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<List<OperacaoContaDTO>> listaExtratosEntreDatas(
			@PathVariable @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm") Date dataInicio,
			@PathVariable @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm") Date dataFim) {
		List<OperacaoContaDTO> listaDeExtratosPorData = operacaoContaService.listaTodosExtratos(dataInicio,
				dataFim);
		if (listaDeExtratosPorData == null || listaDeExtratosPorData.isEmpty()) {
			return new ResponseEntity<>(listaDeExtratosPorData, HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<>(listaDeExtratosPorData, HttpStatus.OK);
	}
}
