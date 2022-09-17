package com.indracompany.treinamento.model.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.indracompany.treinamento.exception.AplicacaoException;
import com.indracompany.treinamento.exception.ExceptionValidacoes;
import com.indracompany.treinamento.model.entity.Extrato;
import com.indracompany.treinamento.model.repository.ContaBancariaRepository;
import com.indracompany.treinamento.model.repository.ExtratoRepository;



@Service
public class ExtratoService extends GenericCrudService<Extrato, Long, ExtratoRepository>{

	/*
	@Autowired
	private ClienteService clienteService;
	
	@Autowired
	private ContaBancariaRepository contaBancariaRepository;
	*/
	@Autowired
	private ExtratoRepository extratoRepository;
	
	public List<Extrato> pesquisarExtrato(String agencia, String numero, String dataInicio, String dataFinal){
		
		if(StringUtils.isBlank(agencia)
				|| StringUtils.isBlank(numero)
				|| StringUtils.isBlank(dataInicio)
				|| StringUtils.isBlank(dataFinal)
				|| !StringUtils.isNumeric(agencia)
				|| !numero.matches("\\d{4}-\\d{1}")
				|| !dataInicio.matches("\\d{4}-\\d{2}-\\d{2}")
				|| !dataFinal.matches("\\d{4}-\\d{2}-\\d{2}")
		){
			throw new AplicacaoException(ExceptionValidacoes.ERRO_ALGUM_CAMPO_COM_FORMATACAO_INVALIDA);
		}
		
		
		
		List<Extrato> extrato = extratoRepository.findByAgenciaAndNumeroExtrato(agencia, numero, dataInicio, dataFinal);
		
		
		if (extrato == null || extrato.isEmpty()) {
			throw new AplicacaoException(ExceptionValidacoes.ALERTA_NENHUM_REGISTRO_ENCONTRADO);
		}
		
		
		return extrato;
		
	}

}
