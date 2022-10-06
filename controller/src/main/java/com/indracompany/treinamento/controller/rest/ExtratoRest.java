package com.indracompany.treinamento.controller.rest;

import com.indracompany.treinamento.model.dto.ExtratoDTO;
import com.indracompany.treinamento.model.entity.Extrato;
import com.indracompany.treinamento.model.service.ExtratoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("rest/extratos")
@RequiredArgsConstructor
public class ExtratoRest extends GenericCrudRest<Extrato, Long, ExtratoService>{

    public final ExtratoService extratoService;
    
    @GetMapping("/all/{cpf}")
    public ResponseEntity<List<ExtratoDTO>> findAllByCpf(@PathVariable String cpf){

        List<ExtratoDTO> extratoList = extratoService.findAllByCpf(cpf);
        if(extratoList.isEmpty()){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

        return ResponseEntity.ok().body(extratoList);
    }

    @GetMapping("/{cpf}/{date}")
    public ResponseEntity<List<ExtratoDTO>> findAllByDate(@PathVariable String cpf,
                                                          @PathVariable String date){

        List<ExtratoDTO> extratoList = extratoService.findByData(cpf, date);
        if(extratoList.isEmpty()){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

        return ResponseEntity.ok().body(extratoList);
    }

    @GetMapping("/{cpf}/{dateIni}/{dateFim}")
    public ResponseEntity<List<ExtratoDTO>> findAllByIntervalo(@PathVariable String cpf,
                                                               @PathVariable String dateIni,
                                                               @PathVariable String dateFim){

        List<ExtratoDTO> extratoList = extratoService.findByIntervalo(cpf, dateIni, dateFim);
        if(extratoList.isEmpty()){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

        return ResponseEntity.ok().body(extratoList);
    }

}
