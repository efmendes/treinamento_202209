package com.indracompany.treinamento.model.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "extrato_conta_gr")
@Data
@EqualsAndHashCode(callSuper = true)
public class Extrato extends GenericEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "fk_conta_id")
    private ContaBancaria contaBancaria;

    @Column(length = 40)
    private String tipoOperacao;

    private double valor;

    private LocalDateTime dataOperacao;

}