package com.indracompany.treinamento.util;

public class NomeUtil {
	
	public static boolean validaNome(final String nome) {
		
		if(nome.isEmpty() || nome.length() > 50 ) {
			return false;
		}
		
		return true;
	}
	 
}
