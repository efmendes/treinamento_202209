package com.indracompany.treinamento.model.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class DepositoDTO implements Serializable{

	private String agencia;
	private String numeroConta;
	private double valor;
	
}
