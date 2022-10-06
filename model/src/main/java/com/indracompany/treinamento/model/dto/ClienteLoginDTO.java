package com.indracompany.treinamento.model.dto;

import com.indracompany.treinamento.model.entity.Cliente;
import lombok.Data;

@Data
public class ClienteLoginDTO {

    private String username;
    private String password;
    private Cliente cliente;
}
