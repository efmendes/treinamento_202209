package com.indracompany.treinamento.model.repository;

import com.indracompany.treinamento.model.entity.Cliente;

public interface ClienteRepository extends GenericCrudRepository<Cliente, Long>{
	
<<<<<<< Updated upstream
	public Cliente findByCpf(String cpf);
	
	public Cliente findByNomeIgnoreCase(String nome);
=======
	
	public Cliente findByCpf(String cpf);
	
	public List<Cliente> findByNomeContainingIgnoreCaseAndAtivoTrue(String nome);
	
	@Query("select c from Cliente c where upper(c.nome) like upper(:name) and ativo=true ")
	public List<Cliente> findByName(@Param("name") String name);
	
>>>>>>> Stashed changes
	
	@Query(value = "select * from clientes " + 
			"where (upper(nome) like upper(:nome) ) and ativo=1",nativeQuery = true)
	public List<Cliente> findByNomeSqlNative(@Param("nome")String nome);
	

}

