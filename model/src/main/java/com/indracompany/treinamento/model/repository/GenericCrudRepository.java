package com.indracompany.treinamento.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import com.indracompany.treinamento.model.entity.GenericEntity;



/**
 * @author efmendes
 *
 * @param <T>
 * @param <ID>
 */
@NoRepositoryBean
public interface GenericCrudRepository<T extends GenericEntity<I>, I> extends JpaRepository<T, I> {



}
