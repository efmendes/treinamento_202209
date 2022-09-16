package com.indracompany.treinamento.model.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
import lombok.EqualsAndHashCode;


@Entity
@Table(name = "operacaoConta")
@Data
@EqualsAndHashCode(callSuper=false)
public class OperacaoConta extends GenericEntity<Long>{


	private static final long serialVersionUID = 1L;


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private Date dataTransacao;

	@Column
	private TipoTransacao tipoTransacao;
	
	@Column
	private double valorTransacao;
	
	@ManyToOne
	private ContaBancaria contaBancaria;

	
}
