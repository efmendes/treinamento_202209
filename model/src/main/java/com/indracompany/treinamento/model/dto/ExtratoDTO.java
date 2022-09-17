package com.indracompany.treinamento.model.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class ExtratoDTO {
	private char tipoOperacao;
	
	private LocalDateTime data;
	
	private Double valor;	
}
