package com.indracompany.treinamento.model.dto;

import java.util.List;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Getter
@Setter
@Slf4j
public class RelatorioClienteDTO {
	
	private String nome;
	private String cpf;
	private List<String> contas;
	
	
	public void teste() {
		log.info("");
	}
}
