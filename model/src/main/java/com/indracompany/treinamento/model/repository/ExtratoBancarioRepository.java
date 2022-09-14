package com.indracompany.treinamento.model.repository;


import com.indracompany.treinamento.model.entity.ExtratoBancario;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface ExtratoBancarioRepository extends GenericCrudRepository<ExtratoBancario,Long> {

        @Query(value="select e from ExtratoBancario e where e.conta.numero = :numero and e.conta.agencia = :agencia")
        List<ExtratoBancario> findByExtrato(String numero, String agencia);

        @Query(value = "select e from ExtratoBancario e where e.conta.numero = :numero and e.conta.agencia = :agencia and e.data = :data")
        List<ExtratoBancario> findByExtratoPorDataEspecifica(String numero, String agencia, LocalDate data);

        @Query(value = "select e from ExtratoBancario e where e.conta.agencia =:agencia and e.conta.numero=:numero and e.data between :dataInicio and :dataFim")
         List<ExtratoBancario> findByExtratoPorIntervalo(String numero, String agencia, LocalDate dataInicio, LocalDate dataFim);
}
