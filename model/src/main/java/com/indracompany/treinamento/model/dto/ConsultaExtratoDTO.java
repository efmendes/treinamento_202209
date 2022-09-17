package com.indracompany.treinamento.model.dto;

import java.time.LocalDate;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class ConsultaExtratoDTO extends GenericDTO{


	private LocalDate dataIni;
	
	private LocalDate dataFim;

	private String agencia;

	private String numConta;

}