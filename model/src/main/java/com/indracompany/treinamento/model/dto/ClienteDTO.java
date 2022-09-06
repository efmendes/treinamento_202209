package com.indracompany.treinamento.model.dto;

import lombok.Data;

@Data
public class ClienteDTO {

	private Long id;

	private String nome;

	private String cpf;

	public ClienteDTO(Long id, String nome, String cpf) {
		super();
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
	}
}
