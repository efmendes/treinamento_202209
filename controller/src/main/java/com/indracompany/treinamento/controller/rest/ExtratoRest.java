package com.indracompany.treinamento.controller.rest;

import com.indracompany.treinamento.model.entity.Extrato;

import com.indracompany.treinamento.model.service.ExtratoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/rest/extratos")
public class ExtratoRest extends GenericCrudRest<Extrato, Long, ExtratoService>{

    @Autowired
    private ExtratoService extratoService;

    @GetMapping(value = "/buscarExtratos/{numero}/{agencia}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody   ResponseEntity<List<Extrato>> buscarExtrato(@PathVariable String numero, @PathVariable String agencia){
        List<Extrato> extratos = extratoService.pesquisarExtrato(numero, agencia);

        if(extratos.isEmpty()){
            return new ResponseEntity<>( HttpStatus.NO_CONTENT);
        }
        return  new ResponseEntity<>(extratos,HttpStatus.OK);

    }

    @GetMapping(value = "/buscarExtratos/{numero}/{agencia}/{dataInicio}/{dataFim}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody   ResponseEntity<List<Extrato>> buscarExtrato(@PathVariable String numero, @PathVariable String agencia, @PathVariable String dataInicio, @PathVariable String dataFim){
        List<Extrato> extratos = extratoService.pesquisarExtratoPorIntervaloDeData(numero, agencia, dataInicio, dataFim);
        if(extratos.isEmpty()){
            return new ResponseEntity<>( HttpStatus.NO_CONTENT);
        }
        return  new ResponseEntity<>(extratos,HttpStatus.OK);

    }


}