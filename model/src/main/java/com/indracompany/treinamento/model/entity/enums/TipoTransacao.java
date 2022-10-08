package com.indracompany.treinamento.model.entity.enums;

public enum TipoTransacao {

	TRANSFERENCIA(1,"TRANSFERENCIA"),
	DEPOSITO(2, "DEPOSITO"),
	SAQUE(3, "SAQUE");
	
	private int codigo;
	private String descricao;
	
	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	private TipoTransacao(int codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}
	
public static TipoTransacao toEnum(Integer cod) {
		
		if (cod == null) {
			return null;
		}
		
		for (TipoTransacao x : TipoTransacao.values()) {
			if (cod.equals(x.getCodigo())) {
				return x;
			}
		}
		
		throw new IllegalArgumentException("Id inválido: " + cod);
	}
}
