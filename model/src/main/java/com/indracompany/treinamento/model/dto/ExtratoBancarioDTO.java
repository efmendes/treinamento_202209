package com.indracompany.treinamento.model.dto;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Data
public class ExtratoBancarioDTO implements Serializable {

    private LocalDate data;
    private String tipoDeOperacao;
    private Double valor;
    private String agencia;
    private String numeroConta;
    private String observacao;

}
