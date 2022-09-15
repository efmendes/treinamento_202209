package com.indracompany.treinamento.model.dto;

import lombok.Data;

import java.util.Date;

@Data
public class ExtratoDTO{

    private String date;
    private String hour;
    private String agencia;
    private String conta;
    private String description;
}
