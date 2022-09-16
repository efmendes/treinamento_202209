package com.indracompany.treinamento.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.indracompany.treinamento.model.entity.ParametroSistema;
import com.indracompany.treinamento.model.repository.ParametroSistemaRepository;
import com.indracompany.treinamento.model.repository.ParametroSistemaRepositoryJdbc;

@Service
public class ParametroSistemaService extends GenericCrudService<ParametroSistema, Long, ParametroSistemaRepository> {

	@Autowired
	private ParametroSistemaRepositoryJdbc parametroSistemaRepositoryJdbc;
	
	public ParametroSistema obterParametroSistema(String nomeParametro) {
		return parametroSistemaRepositoryJdbc.carregarPorNomeParametroJdbcTemplate(nomeParametro);
	}
}
