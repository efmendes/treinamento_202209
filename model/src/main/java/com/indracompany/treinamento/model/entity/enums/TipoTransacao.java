package com.indracompany.treinamento.model.entity.enums;

public enum TipoTransacao {
	DEPOSITO(1, "DEPOSITO"),
	SAQUE(2, "SAQUE"),
	TRANSFERENCIA(3, "TRANSFERENCIA");
	
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


