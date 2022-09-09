package com.indracompany.treinamento.model.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class BuscaClienteDTO implements Serializable{

	private static final long serialVersionUID = 7210034607149165450L;
	
	
	String nome;
	String cpf;
	
}
