package com.indracompany.treinamento.controller.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.indracompany.treinamento.model.entity.ParametroSistema;
import com.indracompany.treinamento.model.service.ParametroSistemaService;

@RestController()
@RequestMapping("rest/parametros")
public class ParametroSistemaRest extends GenericCrudRest<ParametroSistema, Long, ParametroSistemaService>{





}
