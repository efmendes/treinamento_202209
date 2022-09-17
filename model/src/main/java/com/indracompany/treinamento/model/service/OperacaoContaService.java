package com.indracompany.treinamento.model.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.indracompany.treinamento.exception.AplicacaoException;
import com.indracompany.treinamento.exception.ExceptionValidacoes;
import com.indracompany.treinamento.model.dto.ExtratoDTO;
import com.indracompany.treinamento.model.entity.ContaBancaria;
import com.indracompany.treinamento.model.entity.OperacaoConta;
import com.indracompany.treinamento.model.repository.OperacaoContaRepository;

@Service
public class OperacaoContaService extends GenericCrudService<OperacaoConta, Long, OperacaoContaRepository>{
	
	@Autowired
	private ContaBancariaService contaBancariaService;
	
	@Autowired
	private OperacaoContaRepository operacaoContaRepository;
	
	public void salvarOperacao(ContaBancaria contaBancaria, double valor, String tipoOperacao) {
		if (!contaBancariaService.validaContaBancaria(contaBancaria) && valor == 0 && tipoOperacao == null) {
			throw new AplicacaoException(ExceptionValidacoes.ERRO_VALIDACAO);
		}
		OperacaoConta opc = new OperacaoConta();
		opc.setConta(contaBancaria);
		opc.setValor(valor);
		opc.setTpOperacao(tipoOperacao.charAt(0));
		opc.setDataHora(LocalDateTime.now());
		
		operacaoContaRepository.save(opc);
	}
	
	public List<ExtratoDTO> listarOperacaoContaPorData(ContaBancaria contaBancaria, LocalDateTime dataInicio, LocalDateTime dataFim) {
		return convertOperacaoContaToExtratoDTO(
				operacaoContaRepository.ListarOperacaoContaPorData(contaBancaria, dataInicio, dataFim));
	}
	
	private List<ExtratoDTO> convertOperacaoContaToExtratoDTO(List<OperacaoConta> operacoesConta) {
		if(operacoesConta == null || operacoesConta.isEmpty()) {
			throw new AplicacaoException(ExceptionValidacoes.ALERTA_NENHUM_REGISTRO_ENCONTRADO);
		}
		
		List<ExtratoDTO> extrato = new ArrayList<ExtratoDTO>();
		
		for (OperacaoConta operacaoConta : operacoesConta) {
			extrato.add(ExtratoDTO.builder()
					.valor(operacaoConta.getValor())
					.tipoOperacao(operacaoConta.getTpOperacao())
					.data(operacaoConta.getDataHora())
					.build());
		}
		
		return extrato;
	}
}
