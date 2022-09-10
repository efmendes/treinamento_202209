package com.indracompany.treinamento.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.indracompany.treinamento.model.entity.Cliente;

public interface ClienteRepository extends GenericCrudRepository<Cliente, Long>{
	
	
	public Cliente findByCpf(String cpf);
	
	public List<Cliente> findByNomeContainingIgnoreCaseAndAtivoTrue(String nome);
	
	@Query("select c from Cliente c where upper(c.nome) like upper(:name) and ativo=true ")
	public List<Cliente> findByName(@Param("name") String name);
	
	
	@Query(value = "select * from clientes " + 
			"where (upper(nome) like upper(:nome) ) and ativo=1",nativeQuery = true)
	public List<Cliente> findByNomeSqlNative(@Param("nome")String nome);
	

}
