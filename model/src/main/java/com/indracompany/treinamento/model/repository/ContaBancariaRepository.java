package com.indracompany.treinamento.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.indracompany.treinamento.model.entity.Cliente;
import com.indracompany.treinamento.model.entity.ContaBancaria;

public interface ContaBancariaRepository extends GenericCrudRepository<ContaBancaria, Long> {
	
	public ContaBancaria findByAgenciaAndNumero(String agencia, String conta);
	
	
	public List<ContaBancaria> findByCliente(Cliente cli);
	
	@Query("select c from ContaBancaria c where c.cliente = :cli")
	public List<ContaBancaria> buscarContasDoClienteJpql(@Param("cli")Cliente cli);
	
	@Query(nativeQuery = true, value = "select con.* from clientes cli, contas con "
			+ " where cli.id = con.fk_cliente_id and cli.id = :idCliente")
	public List<ContaBancaria> buscarContasDoClienteSql(@Param("idCliente") Long idCliente);

}
