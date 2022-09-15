package com.indracompany.treinamento.model.repository;

import java.util.List;
import java.util.Optional;

import com.indracompany.treinamento.model.entity.ContaBancaria;

public interface ContaBancariaRepository extends GenericCrudRepository<ContaBancaria, Long>{

    public Optional<List<ContaBancaria>> findByClienteCpf(String cpf);

    public Optional<ContaBancaria> findByAgenciaAndNumero(String agencia, String numero);

}