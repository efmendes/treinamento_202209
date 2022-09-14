package com.indracompany.treinamento.model.repository;

import java.util.List;

import com.indracompany.treinamento.model.entity.ContaBancaria;
import com.indracompany.treinamento.model.entity.ExtratoConta;

public interface ExtratoContaRepository extends GenericCrudRepository<ExtratoConta, Long>{
	
	List<ExtratoConta> findByConta(ContaBancaria conta);

}
