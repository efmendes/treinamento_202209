package com.indracompany.treinamento.controller.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import org.springframework.stereotype.Component;

@Component(value = "cnpjValidator")
@FacesValidator
public class CnpjValidator implements Validator {

  public static final String CNPJ_INVALIDO = "CNPJ: Erro de validação: o valor é inválido.";

  public static boolean isValid(String cnpj) {
    cnpj = remove(String.valueOf(cnpj));
    return validaCNPJ(cnpj);
  }

  private static String remove(String CNPJ) {
    CNPJ = CNPJ.replace(".", "");
    CNPJ = CNPJ.replace("/", "");
    CNPJ = CNPJ.replace("-", "");
    return CNPJ;
  }

  private static boolean validaCNPJ(final String CNPJ) {
    if (CNPJ.equals("00000000000000") || CNPJ.equals("11111111111111") || CNPJ.equals("22222222222222") || CNPJ.equals("33333333333333")
        || CNPJ.equals("44444444444444") || CNPJ.equals("55555555555555") || CNPJ.equals("66666666666666") || CNPJ.equals("77777777777777")
        || CNPJ.equals("88888888888888") || CNPJ.equals("99999999999999") || CNPJ.length() != 14) {
      return false;
    } else {
      try {
        Long.parseLong(CNPJ);
      } catch (final NumberFormatException e) {
        return false;
      }
      int soma = 0;
      String cnpjCalc = CNPJ.substring(0, 12);

      final char chrCnpj[] = CNPJ.toCharArray();
      for (int i = 0; i < 4; i++) {
        if (chrCnpj[i] - 48 >= 0 && chrCnpj[i] - 48 <= 9) {
          soma += (chrCnpj[i] - 48) * (6 - (i + 1));
        }
      }

      for (int i = 0; i < 8; i++) {
        if (chrCnpj[i + 4] - 48 >= 0 && chrCnpj[i + 4] - 48 <= 9) {
          soma += (chrCnpj[i + 4] - 48) * (10 - (i + 1));
        }
      }

      int dig = 11 - soma % 11;
      cnpjCalc = new StringBuilder(String.valueOf(cnpjCalc)).append(dig != 10 && dig != 11 ? Integer.toString(dig) : "0").toString();
      soma = 0;
      for (int i = 0; i < 5; i++) {
        if (chrCnpj[i] - 48 >= 0 && chrCnpj[i] - 48 <= 9) {
          soma += (chrCnpj[i] - 48) * (7 - (i + 1));
        }
      }

      for (int i = 0; i < 8; i++) {
        if (chrCnpj[i + 5] - 48 >= 0 && chrCnpj[i + 5] - 48 <= 9) {
          soma += (chrCnpj[i + 5] - 48) * (10 - (i + 1));
        }
      }

      dig = 11 - soma % 11;
      cnpjCalc = new StringBuilder(String.valueOf(cnpjCalc)).append(dig != 10 && dig != 11 ? Integer.toString(dig) : "0").toString();
      return CNPJ.equals(cnpjCalc);
    }
  }

  @Override
  public void validate(final FacesContext arg0, final UIComponent arg1, Object valorTela) {
    valorTela = remove(String.valueOf(valorTela));
    if (!validaCNPJ(String.valueOf(valorTela))) {
      final FacesMessage message = new FacesMessage();
      message.setSeverity(FacesMessage.SEVERITY_ERROR);
      message.setSummary(CnpjValidator.CNPJ_INVALIDO);
      throw new ValidatorException(message);
    }
  }

}
