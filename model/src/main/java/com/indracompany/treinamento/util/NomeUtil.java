package com.indracompany.treinamento.util;

public class NomeUtil {

    public static boolean validaNome(String nome) {
        String regexNome = "^(([A-za-z áéíóú ÁÉÍÓÚ âêô ÂÊÔ ã])*)$";

        if (!nome.matches(regexNome)) {
            return false;
        }
        return true;
    }

}
