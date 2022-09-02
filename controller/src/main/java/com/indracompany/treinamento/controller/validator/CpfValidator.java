package com.indracompany.treinamento.controller.validator;

import java.util.InputMismatchException;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import org.springframework.stereotype.Component;

import com.indracompany.treinamento.util.CpfUtil;

@Component(value = "cpfValidator")
@FacesValidator
public class CpfValidator implements Validator {

  public static final String CPF_INVALIDO = "CPF é inválido.";

  public static boolean isValid(String cpf) {
    cpf = remove(String.valueOf(cpf));
    return CpfUtil.validaCPF(cpf);
  }

  private static String remove(String cpf) {
    cpf = cpf.replace(".", "");
    cpf = cpf.replace("-", "");
    return cpf;
  }

  

  @Override
  public void validate(final FacesContext arg0, final UIComponent arg1, Object valorTela) {
    valorTela = remove(String.valueOf(valorTela));
    if (!CpfUtil.validaCPF(String.valueOf(valorTela))) {
      final FacesMessage message = new FacesMessage();
      message.setSeverity(FacesMessage.SEVERITY_ERROR);
      message.setSummary(CpfValidator.CPF_INVALIDO);
      throw new ValidatorException(message);
    }
  }

}
