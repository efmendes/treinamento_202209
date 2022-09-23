package com.indracompany.treinamento.model.dto;


import java.util.Date;

import lombok.Data;

@Data
public class TransacaoDTO {

	private Date horario;
	private String descricao;
	private double valor;

}
