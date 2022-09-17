package com.indracompany.treinamento.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.indracompany.treinamento.model.entity.ContaBancaria;

public interface ContaBancariaRepository extends GenericCrudRepository<ContaBancaria, Long>{
	
	public List<ContaBancaria> findByClienteCpf(String cpf);
	
	public ContaBancaria findByAgenciaAndNumero(String agencia, String numero);
	
	@Query(value = "select cb.id from contas_bancarias as cb where cb.agencia = :agencia and cb.numero = :numero ", nativeQuery = true)
	public Long procurarContaId(@Param("agencia") String agencia, @Param("numero") String numero);
	

}
