package com.indracompany.treinamento.model.dto;

import java.time.LocalDate;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class ExtratoBancarioDTO extends GenericDTO{

	private static final long serialVersionUID = 2015177288915760067L;

	private LocalDate data;

	private String agencia;

	private String numConta;

	private String descricao;
	
	private double valor;
	
	private char tpOperacao;
}

