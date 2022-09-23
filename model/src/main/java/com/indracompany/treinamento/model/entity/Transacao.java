package com.indracompany.treinamento.model.entity;



import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;

import org.hibernate.validator.constraints.br.CPF;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Data;
import lombok.EqualsAndHashCode;


@Entity
@Table(name = "transacao")
@Data
@EqualsAndHashCode(callSuper = true)
public class Transacao extends GenericEntity<Long>{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(length = 50)
	private String descricao;
	
	
	@Column()
	private Date horario;
	
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "contaBancariaId")
	public ContaBancaria contaBancaria;
	
	@Column()
	private double valor;
	


	

	
}
