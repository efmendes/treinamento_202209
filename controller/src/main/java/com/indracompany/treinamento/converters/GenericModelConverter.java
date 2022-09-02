package com.indracompany.treinamento.converters;

import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;
import java.util.WeakHashMap;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.springframework.stereotype.Component;

@Component(value = "genericModelConverter")
@FacesConverter(value = "genericModelConverter")
public class GenericModelConverter implements Converter {

  private static Map<Object, String> models = new WeakHashMap<Object, String>();

  @Override
  public String getAsString(FacesContext context, UIComponent component, Object entity) {
    synchronized (models) {
      if (!models.containsKey(entity)) {
        String uuid = UUID.randomUUID().toString();
        models.put(entity, uuid);
        return uuid;
      } else {
        return models.get(entity);
      }
    }
  }

  @Override
  public Object getAsObject(FacesContext context, UIComponent component, String uuid) {
    for (Entry<Object, String> entry : models.entrySet()) {
      if (entry.getValue().equals(uuid)) {
        return entry.getKey();
      }
    }
    return null;
  }

}
