package com.indracompany.treinamento.model.dto;

import java.util.Date;

import com.indracompany.treinamento.model.entity.TipoTransacao;

import lombok.Data;
@Data
public class OperacaoContaDTO {

	private Long id;
	
	private String dataTransacao;
	
	private TipoTransacao tipoTransacao;
	private double valorTransacao;
	
	
}
