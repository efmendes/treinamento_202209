package com.indracompany.treinamento.model.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.indracompany.treinamento.model.entity.ContaBancaria;
import com.indracompany.treinamento.model.entity.OperacaoConta;

public interface OperacaoContaRepository extends GenericCrudRepository<OperacaoConta, Long>{
	
	@Query(value = "select c from OperacaoConta c where c.conta = :conta and c.dataHora between :dataInicio and :dataFim")
	List<OperacaoConta> ListarOperacaoContaPorData(@Param("conta") ContaBancaria conta, 
			@Param("dataInicio") LocalDateTime dataInicio,
			@Param("dataFim") LocalDateTime dataFim);
}
