
package com.indracompany.treinamento.model.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "nome",
    "login",
    "email",
})
public class UsuarioDTO implements Serializable
{

    private final static long serialVersionUID = 7035599428517710313L;
    
    @JsonProperty("nome")
    private String nome;
    @JsonProperty("login")
    private String login;
    @JsonProperty("email")
    private String email;



}
