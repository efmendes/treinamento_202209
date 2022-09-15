package com.indracompany.treinamento.model.repository;

import com.indracompany.treinamento.model.entity.ContaBancaria;
import com.indracompany.treinamento.model.entity.Extrato;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ExtratoRepositoy extends GenericCrudRepository<Extrato, Long>{

    public Optional<List<Extrato>> findByConta(ContaBancaria contaBancaria);

}
