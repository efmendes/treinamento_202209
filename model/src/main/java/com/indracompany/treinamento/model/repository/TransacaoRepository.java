package com.indracompany.treinamento.model.repository;

import com.indracompany.treinamento.model.entity.Cliente;
import com.indracompany.treinamento.model.entity.ContaBancaria;
import com.indracompany.treinamento.model.entity.Transacao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TransacaoRepository extends GenericCrudRepository<Transacao, Long>{

	@Query(value = "select * from transacao where contaBancariaId = ?1 and horario between ?2 and ?3", nativeQuery = true)
	public List<Transacao> extratoByPeriodo(long conta, 
		Date dataInicio,
		Date dataFim);
}
