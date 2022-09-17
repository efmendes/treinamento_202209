package com.indracompany.treinamento.model.repository;

import com.indracompany.treinamento.model.entity.ContaBancaria;
import com.indracompany.treinamento.model.entity.Extrato;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface ExtratoRepository extends GenericCrudRepository<Extrato, Long> {
	
	
	@Query (value = "select * from transacoes_leonardonps join contas_bancarias on transacoes_leonardonps.fk_transacoes_contabancaria = contas_bancarias.id " +
	"and contas_bancarias.agencia = :agencia " + " and contas_bancarias.numero = :numero " +
	"and transacoes_leonardonps.data between :dataInicio and :dataFinal", nativeQuery = true)
	public List<Extrato> findByAgenciaAndNumeroExtrato(@Param("agencia") String agencia, @Param("numero") String numero, @Param("dataInicio") String dataInicio, @Param("dataFinal") String dataFinal);

	@Modifying
	@Transactional
	@Query(value = "insert into transacoes_leonardonps (transacao, valor, data, fk_transacoes_contabancaria) values (:transacao, :valor, :data, :fk_transacoes_contabancaria)", nativeQuery = true)
	public void adicionarExtrato(@Param("transacao") String transacao, @Param("valor") double valor, @Param("data") Date data, @Param("fk_transacoes_contabancaria") long contaID);
	
	
	
}
