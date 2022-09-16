package com.indracompany.treinamento.model.entity;

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
@Entity
@Table(name = "parametro_sistema")
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class ParametroSistema extends GenericEntity<Long> {
	
	private static final long serialVersionUID = 5849085922239791517L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name = "NOME_PARAMETRO")
	private String nomeParametro;
	
	@Column(name = "VALOR_PARAMETRO")
	private String valorParametro;

	
	public ParametroSistema() {
		super();
	}
	
	public ParametroSistema(Long id, String nomeParametro, String valorParametro) {
		super();
		this.id = id;
		this.nomeParametro = nomeParametro;
		this.valorParametro = valorParametro;
	}

	
}
