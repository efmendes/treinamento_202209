package com.indracompany.treinamento.model.entity;

import javax.persistence.*;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Entity
@Table(name = "contas_bancarias_lucasHBDutra")
@Data
@EqualsAndHashCode(callSuper = true)
public class ContaBancaria extends GenericEntity<Long>{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 4)
    private String agencia;

    @Column(length = 6)
    private String numero;

    @Column
    private double saldo;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "fk_cliente_id")
    private Cliente cliente;

}