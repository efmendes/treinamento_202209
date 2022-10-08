package com.indracompany.treinamento.model.dto;

import java.time.LocalDateTime;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.indracompany.treinamento.model.entity.enums.TipoTransacao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class ExtratoDTO {

	private TipoTransacao tipoOperacao;
	
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	private LocalDateTime dataHora;
	
	private String valor;
	
}
