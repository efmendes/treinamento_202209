package com.indracompany.treinamento.model.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "tb_usuario")
public class Usuario extends GenericEntity<Long> {
	
	private static final long serialVersionUID = 99132055470833989L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name="noLogin")
	private String noLogin;
	
	@Column(name="dsSenha")
	private String dsSenha;
	
	@Column(name="noUsuario")
	private String noUsuario;
	
	@Column(name="stUsuario")
	private Integer stUsuario;
	
	@Column(name="dtUltimaAlt")
	private LocalDate dtUltimaAlt;
	
}
