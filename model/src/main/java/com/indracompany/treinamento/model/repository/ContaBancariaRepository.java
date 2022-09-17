package com.indracompany.treinamento.model.repository;

import java.util.List;

import com.indracompany.treinamento.model.entity.ContaBancaria;

public interface ContaBancariaRepository extends GenericCrudRepository<ContaBancaria, Long>{
	
	public List<ContaBancaria> findByClienteCpf(String cpf);
	
	public ContaBancaria findByAgenciaAndNumero(String agencia, String numero);

	//public List<OperacaoConta> findByOperacaoContaDataTransacaoBetweenAndAgenciaAndNumero(Date dataInicio, Date dataFim, String agencia, String numero);
	
	
}
