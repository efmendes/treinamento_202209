package com.indracompany.treinamento.model.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.indracompany.treinamento.model.entity.ContaBancaria;
import com.indracompany.treinamento.model.entity.Transacao;

public interface ContaBancariaRepository extends GenericCrudRepository<ContaBancaria, Long>{
	
	public List<ContaBancaria> findByClienteCpf(String cpf);
	
	public ContaBancaria findByAgenciaAndNumero(String agencia, String numero);
	
	@Query("FROM Transacao AS c WHERE c.contaId = :conta and c.data BETWEEN :dataInicio AND :dataFim")
	public List<Transacao> findByDate(Date dataInicio, Date dataFim, Long conta);

	
	

}
