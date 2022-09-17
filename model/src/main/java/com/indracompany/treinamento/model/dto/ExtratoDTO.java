package com.indracompany.treinamento.model.dto;

import java.io.Serializable;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class ExtratoDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;

	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	private Date data;

	private String agencia;

	private String numero;

	private double saldo;

	
}
