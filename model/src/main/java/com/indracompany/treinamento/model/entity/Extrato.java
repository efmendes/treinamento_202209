package com.indracompany.treinamento.model.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "transacoes_leonardonps")
@Data
@EqualsAndHashCode(callSuper = true)

public class Extrato extends GenericEntity<Long> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private String transacao;
	
	@Column
	private Date data;
	
	@OneToOne
	@JoinColumn(name = "fk_transacoes_contabancaria")
	private ContaBancaria conta;
}
