package com.indracompany.treinamento.model.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.indracompany.treinamento.model.entity.ContaBancaria;

@Repository
public interface ContaBancariaRepository extends GenericCrudRepository<ContaBancaria, Long>{
	
	public List<ContaBancaria> findByClienteCpf(String cpf);
	
	public ContaBancaria findByAgenciaAndNumero(String agencia, String numero);

}
