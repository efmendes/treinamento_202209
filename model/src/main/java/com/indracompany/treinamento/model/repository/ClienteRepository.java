
package com.indracompany.treinamento.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.indracompany.treinamento.model.dto.BuscaClienteDTO;
import com.indracompany.treinamento.model.entity.Cliente;

@Repository
public interface ClienteRepository extends GenericCrudRepository<Cliente, Long> {

	public Cliente findByCpf(String cpf);

	public BuscaClienteDTO findByNomeContainingIgnoreCase(String nome);

	@Query("SELECT c FROM Cliente c WHERE c.nome LIKE %?1%")
	public List<Cliente> findByNomeLike(@Param("nome") String nome);

}