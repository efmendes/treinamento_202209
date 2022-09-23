package com.indracompany.treinamento.controller.rest;



import com.indracompany.treinamento.model.entity.ExtratoBancario;
import com.indracompany.treinamento.model.service.ExtratoBancarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/rest/extratos")
public class ExtratoBancarioRest extends GenericCrudRest<ExtratoBancario, Long, ExtratoBancarioService>{

    @Autowired
    private ExtratoBancarioService extratoBancarioService;

    @GetMapping(value = "/buscarExtratos/{numero}/{agencia}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody   ResponseEntity<List<ExtratoBancario>> buscarExtratoBancario(@PathVariable String numero, @PathVariable String agencia){
        List<ExtratoBancario> extratos = extratoBancarioService.pesquisarExtratoBancario(numero, agencia);

        if(extratos.isEmpty()){
            return new ResponseEntity<>( HttpStatus.NO_CONTENT);
        }
        return  new ResponseEntity<>(extratos,HttpStatus.OK);

    }

    @GetMapping(value = "/buscarExtratos/{numero}/{agencia}/{data}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody   ResponseEntity<List<ExtratoBancario>> buscarExtratoBancario(@PathVariable String numero, @PathVariable String agencia, @PathVariable String data){
        List<ExtratoBancario> extratos = extratoBancarioService.pesquisarExtratoPorData(numero, agencia, data);
        if(extratos.isEmpty()){
            return new ResponseEntity<>( HttpStatus.NO_CONTENT);
        }
        return  new ResponseEntity<>(extratos,HttpStatus.OK);

    }

    @GetMapping(value = "/buscarExtratos/{numero}/{agencia}/{dataInicio}/{dataFim}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody   ResponseEntity<List<ExtratoBancario>> buscarExtratoBancario(@PathVariable String numero, @PathVariable String agencia, @PathVariable String dataInicio, @PathVariable String dataFim){
        List<ExtratoBancario> extratos = extratoBancarioService.pesquisarExtratoPorIntervaloDeData(numero, agencia, dataInicio, dataFim);
        if(extratos.isEmpty()){
            return new ResponseEntity<>( HttpStatus.NO_CONTENT);
        }
        return  new ResponseEntity<>(extratos,HttpStatus.OK);

    }


}