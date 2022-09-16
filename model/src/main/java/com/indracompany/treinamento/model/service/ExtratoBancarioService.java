package com.indracompany.treinamento.model.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.indracompany.treinamento.exception.AplicacaoException;
import com.indracompany.treinamento.exception.ExceptionValidacoes;
import com.indracompany.treinamento.model.dto.ExtratoBancarioDTO;
import com.indracompany.treinamento.model.entity.ContaBancaria;
import com.indracompany.treinamento.model.entity.ExtratoBancario;
import com.indracompany.treinamento.model.repository.ExtratoBancarioRepository;

@Service
public class ExtratoBancarioService extends GenericCrudService<ExtratoBancario, Long, ExtratoBancarioRepository>{

	@Autowired
	private ContaBancariaService contaBancariaService;
	
	public List<ExtratoBancarioDTO> obterExtrato(String agencia, String numConta, LocalDate dataInicio, LocalDate dataFim) {
		ContaBancaria conta = contaBancariaService.consultaConta(agencia, numConta);
		
		List<ExtratoBancario> extrato  = getRepository().findByContaAndDataBetween(conta, dataInicio, dataFim);
		
		if (extrato==null || extrato.isEmpty()) {
			throw new AplicacaoException(ExceptionValidacoes.ALERTA_NENHUM_REGISTRO_ENCONTRADO);
		}
		
		List<ExtratoBancarioDTO> extratoBancarioDto = new ArrayList<>();
		
		for (ExtratoBancario e : extrato) {
			ExtratoBancarioDTO dto = new ExtratoBancarioDTO();
			
			BeanUtils.copyProperties(e, dto);
			
			dto.setAgencia(e.getConta().getAgencia());
			dto.setNumConta(e.getConta().getNumero());
			
			extratoBancarioDto.add(dto);
			
		}
		
		return extratoBancarioDto;
	}
	
	public void registarOperacaoExtrato(ContaBancaria conta, String descricao, double valor, char tpOperacao, LocalDate data) {
		ExtratoBancario e  = new ExtratoBancario();
		e.setConta(conta);
		e.setData(data);
		e.setDescricao(descricao);
		e.setValor(valor);
		e.setTpOperacao(tpOperacao);
		this.salvar(e);
	}

}
