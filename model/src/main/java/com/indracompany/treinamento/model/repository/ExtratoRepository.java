package com.indracompany.treinamento.model.repository;


import com.indracompany.treinamento.model.entity.ContaBancaria;
import com.indracompany.treinamento.model.entity.Extrato;

import java.util.List;

public interface ExtratoRepository extends GenericCrudRepository<Extrato, Long>{

    List<Extrato> findByContaBancaria (ContaBancaria contaBancaria);

}
