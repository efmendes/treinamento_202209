package com.indracompany.treinamento.model.entity;

import lombok.Getter;
import lombok.Setter;

public enum TipoTransacao {
	
	DEPOSITO(0), SAQUE(1), TRANSFERENCIA(2);
	
	@Getter
	@Setter
	private int tipoDoEnum;

	private TipoTransacao(int tipoDoEnum) {
		this.setTipoDoEnum(tipoDoEnum);
	}

	
	
}
