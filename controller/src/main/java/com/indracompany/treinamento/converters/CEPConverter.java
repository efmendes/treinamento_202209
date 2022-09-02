package com.indracompany.treinamento.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.springframework.stereotype.Component;

@Component(value = "cepConverter")
@FacesConverter(value = "cepConverter")
public class CEPConverter implements Converter {

  @Override
  public Object getAsObject(final FacesContext arg0, final UIComponent arg1, final String arg2) {
    if (arg2 == null) {
      return null;
    }
    String cepNoPadraoDoBanco = arg2;
    cepNoPadraoDoBanco = cepNoPadraoDoBanco.replaceAll("-", "");
    return cepNoPadraoDoBanco;
  }

  @Override
  public String getAsString(final FacesContext arg0, final UIComponent arg1, final Object arg2) {
    if (arg2 == null) {
      return null;
    }
    String cepComMascara;
    if (arg2.toString().length() == 8) {
      cepComMascara = arg2.toString().substring(0, 5) + "-" + arg2.toString().substring(5, arg2.toString().length());
    } else {
      cepComMascara = arg2.toString();
    }

    return cepComMascara;
  }
}
