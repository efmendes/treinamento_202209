package com.indracompany.treinamento.model.repository;

import com.indracompany.treinamento.model.entity.ClienteLogin;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientLoginRepository extends GenericCrudRepository<ClienteLogin, Long>{

    Optional<ClienteLogin> findByUsername(String username);
}
