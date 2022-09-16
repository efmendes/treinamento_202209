package com.indracompany.treinamento.model.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.indracompany.treinamento.model.entity.Cliente;
import com.indracompany.treinamento.model.entity.OperacaoConta;

@Repository
public interface OperacaoContaRepository extends GenericCrudRepository<OperacaoConta, Long> {

	public Optional<List<OperacaoConta>> findByDataTransacaoBetweenAndContaBancariaAgenciaAndContaBancariaNumero(
			Date dataInicio, Date dataFim, String agencia, String numero);

	public Optional<List<OperacaoConta>> findByDataTransacaoBetween(Date dataInicio, Date dataFim);
	
	
	public Optional<List<OperacaoConta>> findByDataTransacaoGreaterThanEqualAndDataTransacaoLessThanEqual(Date dataInicio, Date dataFim);
	
	
	@Query(value = "select * from operacaoConta where dataTransacao >= (:dataInicio) and dataTransacao <= (:dataFim)", nativeQuery = true)
    public Optional<List<OperacaoConta>> findByDatas(@Param ("dataInicio") Date dataInicio, @Param ("dataFim") Date dataFim);
	
}
