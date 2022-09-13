package com.indracompany.treinamento.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "contas_bancarias")
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
	
	@ManyToOne
	@JoinColumn(name = "fk_cliente_id")
	private Cliente cliente;
	
}
