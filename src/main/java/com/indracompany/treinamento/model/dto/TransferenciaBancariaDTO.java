package com.indracompany.treinamento.model.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class TransferenciaBancariaDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String agenciaOrigem;
	private String numeroContaOrigem;
	private String agenciaDestino;
	private String numeroContaDestino;
	private double valor;
	
}
