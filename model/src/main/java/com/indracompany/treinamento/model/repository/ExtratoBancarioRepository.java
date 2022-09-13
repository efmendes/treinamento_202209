package com.indracompany.treinamento.model.repository;


import com.indracompany.treinamento.model.entity.ExtratoBancario;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ExtratoBancarioRepository extends GenericCrudRepository<ExtratoBancario,Long> {

        @Query(value="select e from ExtratoBancario e where e.conta.numero = :numero and e.conta.agencia = :agencia")
        public List<ExtratoBancario> findByExtrato(String numero, String agencia);


}
