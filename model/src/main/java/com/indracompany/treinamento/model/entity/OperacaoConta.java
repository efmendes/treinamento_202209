package com.indracompany.treinamento.model.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.indracompany.treinamento.model.entity.enums.TipoTransacao;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "operacoes")
@Data
@EqualsAndHashCode(callSuper = true)
public class OperacaoConta extends GenericEntity<Long>{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private LocalDateTime dataHora;
	
	@Column
	private TipoTransacao tpOperacao;
	
	private double valor;
	
	@ManyToOne
	@JoinColumn(name = "fk_conta_id")
	private ContaBancaria conta;
	
}


