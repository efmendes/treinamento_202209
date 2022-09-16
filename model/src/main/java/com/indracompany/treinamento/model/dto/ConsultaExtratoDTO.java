package com.indracompany.treinamento.model.dto;

import java.util.Date;

import lombok.Data;

@Data
public class ConsultaExtratoDTO {
	
	private String agencia;
	private String numero;
	private String dataInicio;
	private String dataFim;
}
