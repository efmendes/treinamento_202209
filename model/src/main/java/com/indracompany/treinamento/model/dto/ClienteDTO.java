package com.indracompany.treinamento.model.dto;


import lombok.Data;

@Data
public class ClienteDTO {
	
	private String nome;
	
	private String observacoes;
	
	private String cpf;
	
	private String CpfMascarado;
	
	private String email;
	
	private Number id;

}
