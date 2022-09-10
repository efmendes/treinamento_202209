package com.indracompany.treinamento.util;

public class NomeUtil {
		
	public static boolean validaNome(final String nome) {
		String nomeRegex = "^(([a-zA-z áéíóú ÁÉÍÓÚ âêîôû ÂÊÎÔÛ ã])*)$";
		
		if (!nome.matches(nomeRegex)) {
			return false;
		} else {
			return true;
		}
	}
	
}
