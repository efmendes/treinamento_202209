package com.indracompany.treinamento.model.repository;

import com.indracompany.treinamento.model.entity.Extrato;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ExtratoRepository extends GenericCrudRepository<Extrato,Long> {

        @Query(value="select e from Extrato e where e.contaBancaria.numero = :numero and e.contaBancaria.agencia = :agencia")
        List<Extrato> findByExtrato(String numero, String agencia);

        @Query(value = "select e from Extrato e where e.contaBancaria.agencia =:agencia and e.contaBancaria.numero=:numero and e.data between :dataInicio and :dataFim")
         List<Extrato> findByExtratoPorIntervalo(String numero, String agencia, LocalDate dataInicio, LocalDate dataFim);
        
}