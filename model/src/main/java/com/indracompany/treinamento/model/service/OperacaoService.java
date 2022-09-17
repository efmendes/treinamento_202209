package com.indracompany.treinamento.model.service;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.indracompany.treinamento.exception.AplicacaoException;
import com.indracompany.treinamento.exception.ExceptionValidacoes;
import com.indracompany.treinamento.model.entity.Operacao;
import com.indracompany.treinamento.model.repository.OperacaoRepository;

@Service
public class OperacaoService extends GenericCrudService<Operacao, Long, OperacaoRepository> {
	@Autowired
	private OperacaoRepository operacaoRepository;

	public List<Operacao> buscarOperacoesPorCliente(String agencia, String numero) {

		if (agencia.isBlank() || numero.isBlank()) {
			throw new AplicacaoException(ExceptionValidacoes.ERRO_CONTA_INVALIDA, "Agencia: " + agencia + " Numero: " + numero);
		}

		List<Operacao> operacoes = operacaoRepository.findByAgenciaAndNumero(agencia, numero);

		if (operacoes == null) {
			throw new AplicacaoException(ExceptionValidacoes.ALERTA_NENHUM_REGISTRO_ENCONTRADO, "Agencia: " + agencia + " Numero: " + numero);
		}

		return operacoes;

	}

	public List<Operacao> buscarOperacoesPorIntervaloData(String agencia, String numero, Date startDate, Date endDate){

		List<Operacao> operacoes = operacaoRepository.findByAgenciaAndNumeroAndDateBetween(agencia, numero, startDate, endDate);
		if (operacoes == null) {
			throw new AplicacaoException(ExceptionValidacoes.ALERTA_NENHUM_REGISTRO_ENCONTRADO, "Data inicial: " + startDate + " Data final: " + endDate);
		}

		return operacoes;
	}
}