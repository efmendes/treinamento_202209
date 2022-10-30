package com.indracompany.treinamento.controller.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/all")
    public ResponseEntity<List<ContaBancaria>> findAll(){

        return ResponseEntity.ok().body(contaBancariaService.findAll());
    }

    @GetMapping(value = "/buscarContas/{cpf}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<List<ContaBancaria>> buscarContasDoCliente(@PathVariable String cpf){
        List<ContaBancaria> lista = contaBancariaService.listarContasDoCliente(cpf);
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
        contaBancariaService.depositar(dto, false);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }


    @PutMapping(value = "/sacar", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<Void> sacar (@RequestBody SaqueDTO dto){
        contaBancariaService.sacar(dto, false);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }


    @PutMapping(value = "/transferencia", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<Void> transferir (@RequestBody TransferenciaBancariaDTO dto){
        contaBancariaService.transferir(dto);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<ContaBancaria> create(@RequestBody String cpf){

        return ResponseEntity.status(HttpStatus.CREATED).body(contaBancariaService.createConta(cpf));
    }

    @DeleteMapping("/del/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        this.contaBancariaService.deletarConta(id);
        return ResponseEntity.ok().build();
    }
}