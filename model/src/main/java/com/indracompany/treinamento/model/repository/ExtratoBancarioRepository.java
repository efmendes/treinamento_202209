package com.indracompany.treinamento.model.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.indracompany.treinamento.model.entity.ContaBancaria;
import com.indracompany.treinamento.model.entity.ExtratoBancario;

@Repository
public interface ExtratoBancarioRepository extends GenericCrudRepository<ExtratoBancario, Long>{

	List<ExtratoBancario> findByContaAndDataBetween(ContaBancaria conta, LocalDate dataIni, LocalDate dateFim);

}
