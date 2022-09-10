package com.indracompany.treinamento.model.repository;

import com.indracompany.treinamento.model.entity.Cliente;

public interface ClienteRepository extends GenericCrudRepository<Cliente, Long>{
	
	
	public Cliente findByCpf(String cpf);
	public Cliente findByNome(String nome);

}
