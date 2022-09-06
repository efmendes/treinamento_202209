package com.indracompany.treinamento.model.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class BuscaClienteDTO implements Serializable{

	private static final long serialVersionUID = 7210034607149165450L;
	
	
	private String nome;
	private String cpf;
	
	public BuscaClienteDTO(String nome, String cpf) {
		super();
		this.nome = nome;
		this.cpf = cpf;
		
	}
	
}
