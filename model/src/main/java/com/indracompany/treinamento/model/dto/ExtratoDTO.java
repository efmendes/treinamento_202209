package com.indracompany.treinamento.model.dto;

import java.util.Date;

import javax.persistence.Column;

import com.indracompany.treinamento.model.entity.ContaBancaria;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ExtratoDTO {

	
	private String tipo;
	
	private Double valor;
	
	private Date data;
	
}
