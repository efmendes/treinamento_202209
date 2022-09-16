package com.indracompany.treinamento.model.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.indracompany.treinamento.model.entity.ParametroSistema;

public interface ParametroSistemaRepository extends GenericCrudRepository<ParametroSistema, Long> {

	ParametroSistema findByNomeParametro(String nomeParametro);
	
	@Query(value="select * from PARAMETRO_SISTEMA WHERE NOME_PARAMETRO = :nomeParametro",nativeQuery=true)
	ParametroSistema carregarPorNomeParametroNativeQuery(@Param("nomeParametro") String nomeParametro);
	
	@Query("select p from ParametroSistema p where p.nomeParametro = :nomeParametro")
	ParametroSistema carregarPorNomeParametroJpql(@Param("nomeParametro") String nomeParametro);
}
