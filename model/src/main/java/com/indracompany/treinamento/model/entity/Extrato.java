package com.indracompany.treinamento.model.entity;

import lombok.Data;
     
import javax.persistence.*;
import java.time.LocalDate;


@Entity
@Table(name="ExtratoJA")
@Data
public class Extrato extends GenericEntity<Long>{


	private static final long serialVersionUID = 1L;

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
    private ContaBancaria contaBancaria;
}