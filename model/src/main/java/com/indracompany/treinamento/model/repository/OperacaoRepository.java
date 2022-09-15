package com.indracompany.treinamento.model.repository;

import java.util.Date;
import java.util.List;

import com.indracompany.treinamento.model.entity.Operacao;

public interface OperacaoRepository extends GenericCrudRepository<Operacao, Long>{
	
	
	public List<Operacao> findByAgenciaAndNumero(String agencia, String numero);
	
	public List<Operacao> findByAgenciaAndNumeroAndDateBetween(String agencia, String numero, Date startDate, Date endDate);


}
