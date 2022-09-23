package com.indracompany.treinamento.model.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.time.LocalDate;


@Entity
@Table(name="extratos_Dimas")
@Data
@EqualsAndHashCode
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
