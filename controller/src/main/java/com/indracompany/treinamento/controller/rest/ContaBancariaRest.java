package com.indracompany.treinamento.controller.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.indracompany.treinamento.model.dto.ConsultaExtratoDTO;
import com.indracompany.treinamento.model.dto.DepositoDTO;
import com.indracompany.treinamento.model.dto.ExtratoBancarioDTO;
import com.indracompany.treinamento.model.dto.SaqueDTO;
import com.indracompany.treinamento.model.dto.TransferenciaBancarioDTO;
import com.indracompany.treinamento.model.entity.ContaBancaria;
import com.indracompany.treinamento.model.service.ContaBancariaService;
import com.indracompany.treinamento.model.service.ExtratoBancarioService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController()
@CrossOrigin(origins = "*")
@RequestMapping("rest/conta")
public class ContaBancariaRest extends GenericCrudRest<ContaBancaria, Long, ContaBancariaService>{
	
	@Autowired
	private ExtratoBancarioService extratoBancarioService;
	
	@ApiOperation(value = "Consulta saldo", nickname = "consultaSaldo")
	@RequestMapping(value = "/consultarSaldo/{agencia}/{numConta}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
	public @ResponseBody ResponseEntity<Double> consultarSaldo(final @PathVariable String agencia, 
			final @PathVariable String numConta){
		double saldo = getService().consultarSaldo(agencia, numConta);
		return new ResponseEntity<>(saldo, HttpStatus.OK);
	}
	
	@ApiOperation(value = "Realiza um deposito", nickname = "deposito")
	@RequestMapping(value = "/deposito", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> depositar(final @RequestBody DepositoDTO dto){
		getService().depositar(dto.getAgencia(), dto.getNumeroConta(), dto.getValor(), null);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@ApiOperation(value = "Realiza um saque", nickname = "saque")
	@RequestMapping(value = "/saque", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> sacar(final @RequestBody SaqueDTO dto){
		getService().sacar(dto.getAgencia(), dto.getNumeroConta(), dto.getValor(), null);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@ApiOperation(value = "Realiza um transferencia bancaria", nickname = "transferencia")
	@RequestMapping(value = "/transferencia", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> transferir(final @ApiParam("JSON com dados necessarios para realizar uma transferencia ") @RequestBody TransferenciaBancarioDTO dto){
		getService().transferir(dto);
		return new ResponseEntity<>(HttpStatus.OK);
	}	
	
	@ApiOperation(value = "Consulta contas de clientes por CPF", nickname = "consultaContaCliente")
	@RequestMapping(value = "/consultarContasCliente/{cpf}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
	public @ResponseBody ResponseEntity<List<ContaBancaria>> consultarContaCliente(final @PathVariable String cpf){
		List<ContaBancaria> contasDoCliente = getService().obterContas(cpf);
		return new ResponseEntity<>(contasDoCliente, HttpStatus.OK);
		
	}
	
	@ApiOperation(value = "Exibe extrado por período", nickname = "extrato")
	@RequestMapping(value = "/extratoPorPeriodo", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
	public @ResponseBody ResponseEntity<List<ExtratoBancarioDTO>> consultarExtratoBancario(final @RequestBody ConsultaExtratoDTO param){
		
		List<ExtratoBancarioDTO> extrato = extratoBancarioService.obterExtrato(param.getAgencia(), param.getNumConta(),
				param.getDataIni(), param.getDataFim());
		
		return new ResponseEntity<>(extrato, HttpStatus.OK);
	}
	
	
}
