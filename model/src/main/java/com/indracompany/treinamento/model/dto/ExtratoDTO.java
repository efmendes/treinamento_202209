package com.indracompany.treinamento.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class ExtratoDTO implements Serializable {

    private String tipoOperacao;

    private double valor;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime dataOperacao;

}
