package com.indracompany.treinamento.model.entity;


import com.indracompany.treinamento.model.dto.ContaClienteDTO;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name="extratos_ryan")
@Data
public class ExtratoBancario extends GenericEntity<Long>{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    @Column
    private LocalDate  data;

    @Column(length = 20)
    private  String tipoDeOperacao;

    @Column(nullable = false)
    private Double valor;

    @Column(length = 100)
    private String observacao;


    @ManyToOne
    @JoinColumn(name = "fk_conta_id")
    private ContaBancaria conta;
}
