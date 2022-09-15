package com.indracompany.treinamento.model.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.jpa.repository.Temporal;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "operacoes_Dandaraestrela")
@Data
@EqualsAndHashCode(callSuper = true)
public class Operacao extends GenericEntity<Long>{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Date date;
	
	@Column(length = 4)
	private String agencia;
	
	@Column(length = 6)
	private String numero;
	
	private String operationType;
	
	private double value;
	
}
