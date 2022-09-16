package com.indracompany.treinamento.model.dto;

import java.time.LocalDate;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class ConsultaExtratoDTO extends GenericDTO{

	private static final long serialVersionUID = 2015177288915760067L;

	private LocalDate dataIni;
	
	private LocalDate dataFim;

	private String agencia;

	private String numConta;

}
