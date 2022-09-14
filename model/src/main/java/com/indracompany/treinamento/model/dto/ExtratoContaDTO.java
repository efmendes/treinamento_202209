package com.indracompany.treinamento.model.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ExtratoContaDTO {

    @JsonFormat(pattern="dd/MM/yyyy  HH:mm")
    private LocalDateTime dataHora;

    private char tipoOperacao;

    private double valor;

    private String observacao;

}