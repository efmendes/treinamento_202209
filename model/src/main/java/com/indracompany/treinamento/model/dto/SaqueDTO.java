package com.indracompany.treinamento.model.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class SaqueDTO extends GenericDTO{

	private static final long serialVersionUID = 4320607655918916745L;
	
	private String agencia;
	private String numeroConta;
	private double valor;
	
	
	

}
