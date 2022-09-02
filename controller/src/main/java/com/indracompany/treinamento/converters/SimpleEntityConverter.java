package com.indracompany.treinamento.converters;

import java.util.Map;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.springframework.stereotype.Component;

import com.indracompany.treinamento.model.entity.GenericEntity;

@SuppressWarnings("rawtypes")
@Component(value = "simpleEntityConverter")
@FacesConverter(value = "simpleEntityConverter")
public class SimpleEntityConverter implements Converter {

  protected void addAttribute(final UIComponent component, final GenericEntity o) {
    final String key = o.getId().toString(); // codigo da empresa como chave neste caso
    this.getAttributesFrom(component).put(key, o);
  }

  @Override
  public Object getAsObject(final FacesContext ctx, final UIComponent component, final String value) {
    if (value != null) {
      return this.getAttributesFrom(component).get(value);
    }
    return null;
  }

  @Override
  public String getAsString(final FacesContext ctx, final UIComponent component, final Object value) {
    if (value != null && !"".equals(value)) {

      final GenericEntity entity = (GenericEntity) value;

      // adiciona item como atributo do componente
      this.addAttribute(component, entity);

      final Long codigo = (Long) entity.getId();
      if (codigo != null) {
        return String.valueOf(codigo);
      }
    }

    return (String) value;
  }

  protected Map<String, Object> getAttributesFrom(final UIComponent component) {
    return component.getAttributes();
  }

}
