package com.indracompany.treinamento.util;

import java.util.InputMismatchException;

public class CpfUtil {

	
	public static boolean validaCPF(final String cpf) {
	    if (cpf.equals("00000000000") || cpf.equals("11111111111") || cpf.equals("22222222222") || cpf.equals("33333333333") || cpf.equals("44444444444")
	        || cpf.equals("55555555555") || cpf.equals("66666666666") || cpf.equals("77777777777") || cpf.equals("88888888888") || cpf.equals("99999999999")
	        || cpf.length() != 11 || cpf.length() == 14) {
	      return false;
	    }
	    char dig10;
	    char dig11;
	    int sm = 0;
	    int i = 0;
	    int r;
	    int num;
	    int peso = 10;
	    try {
	      for (; i < 9; i++) {
	        num = cpf.charAt(i) - 48;
	        sm = sm + num * peso;
	        peso = peso - 1;
	      }
	      r = 11 - sm % 11;
	      if (r == 10 || r == 11) {
	        dig10 = '0';
	      } else {
	        dig10 = (char) (r + 48);
	      }
	      sm = 0;
	      peso = 11;
	      for (i = 0; i < 10; i++) {
	        num = cpf.charAt(i) - 48;
	        sm = sm + num * peso;
	        peso = peso - 1;
	      }
	      r = 11 - sm % 11;
	      if (r == 10 || r == 11) {
	        dig11 = '0';
	      } else {
	        dig11 = (char) (r + 48);
	      }

	      return dig10 == cpf.charAt(9) && dig11 == cpf.charAt(10);
	    } catch (final InputMismatchException erro) {
	      return false;
	    }
	  }
}
