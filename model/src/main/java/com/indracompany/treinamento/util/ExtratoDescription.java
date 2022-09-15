package com.indracompany.treinamento.util;

import lombok.Getter;

public enum ExtratoDescription {

    SAQUE("Saque efetuado no valor de R$ "),
    DEPOSITO("Depósito efetuado no valor de R$ "),
    TRANSFERENCIAORIGEM("Trasferência enviada no valor de R$ "),
    TRANSFERENCIADESTINO("Trasferência recebida no valor de R$ ");

    @Getter
    private final String description;

    ExtratoDescription (String description){
        this.description = description;
    }
}
