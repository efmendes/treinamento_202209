package com.indracompany.treinamento.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.springframework.stereotype.Component;

@Component(value = "cpfcnpjConverter")
@FacesConverter(value = "cpfcnpjConverter")
public class CPFCNPJConverter implements Converter {

  @Override
  public Object getAsObject(final FacesContext arg0, final UIComponent arg1, final String arg2) {
    if (arg2 == null) {
      return null;
    }
    String cpfcnpjNoPadraoDoBanco = arg2;

    cpfcnpjNoPadraoDoBanco = cpfcnpjNoPadraoDoBanco.replaceAll("\\.", "");
    cpfcnpjNoPadraoDoBanco = cpfcnpjNoPadraoDoBanco.replaceAll("/", "");
    cpfcnpjNoPadraoDoBanco = cpfcnpjNoPadraoDoBanco.replaceAll("-", "");
    return cpfcnpjNoPadraoDoBanco;
  }

  @Override
  public String getAsString(final FacesContext arg0, final UIComponent arg1, Object arg2) {
    if (arg2 == null) {
      return null;
    }
    String cpfOuCnpjComMascara;
    arg2 = arg2.toString().replaceAll("\\.", "");
    arg2 = arg2.toString().replaceAll("/", "");
    arg2 = arg2.toString().replaceAll("-", "");
    if (arg2.toString().length() == 11) {
      cpfOuCnpjComMascara = arg2.toString().substring(0, 3) + "." + arg2.toString().substring(3, 6) + "." + arg2.toString().substring(6, 9) + "-"
          + arg2.toString().substring(9, arg2.toString().length());
    } else if (arg2.toString().length() == 14) {
      cpfOuCnpjComMascara = arg2.toString().substring(0, 2) + "." + arg2.toString().substring(2, 5) + "." + arg2.toString().substring(5, 8) + "/"
          + arg2.toString().substring(8, 12) + "-" + arg2.toString().substring(12, arg2.toString().length());
    } else {
      cpfOuCnpjComMascara = arg2.toString();
    }
    return cpfOuCnpjComMascara;
  }
}
