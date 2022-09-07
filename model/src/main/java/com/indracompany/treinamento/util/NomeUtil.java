package com.indracompany.treinamento.util;

public class NomeUtil {
    public static boolean validaNome(final String nome) {

        //caso o nome não se enquandre ao regex irá retornar a exception
        if (!nome.matches("^(([a-zA-Z áéíóú ÁÉÍÓÚ âêîôû ÂÊÎÔÛ ã])*)$")) {
            return false;
        } else {
            return true;
        }

    }
}
