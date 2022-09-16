package com.indracompany.treinamento.model.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "transacao_bancarias_MariaRitaGS")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Transacao extends GenericEntity<Long>{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(length = 21)
	private String tipo;
	
	private Double valor;
	
	private Date data;
	
	@Column(name = "conta_id")
	private Long contaId;
	
	public Transacao(String tipo, Double valor, Date data, Long conta) {
		this.tipo = tipo;
		this.valor = valor;
		this.data = data;
		this.contaId = conta;
	
	}
}
