package com.indracompany.treinamento.model.entity;

import javax.persistence.*;
import javax.validation.constraints.Email;

import org.hibernate.validator.constraints.br.CPF;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "clientes_lucasHBDutra")
@Data
@EqualsAndHashCode(callSuper = true)
public class Cliente extends GenericEntity<Long>{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(length = 50)
	private String nome;
	
	@CPF
	@Column(length = 11)
	private String cpf;
	
	@Email
	private String email;
	
	private boolean ativo;
	
	private String observacoes;
	

	
}
