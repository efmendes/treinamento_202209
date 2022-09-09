package com.indracompany.treinamento.model.repository;

import com.indracompany.treinamento.model.entity.Cliente;

import java.util.List;

public interface ClienteRepository extends GenericCrudRepository<Cliente, Long>{
	

	public Cliente findByCpf(String cpf);
	
	public List<Cliente> findByNome(String nome);

}
