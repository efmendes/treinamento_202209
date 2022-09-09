package com.indracompany.treinamento.model.repository;

import java.util.List;

import com.indracompany.treinamento.model.entity.Cliente;

public interface ClienteRepository extends GenericCrudRepository<Cliente, Long>{
	
	
	public Cliente findByCpf(String cpf);
	
	public List<Cliente> findByNome(String nome);

}
