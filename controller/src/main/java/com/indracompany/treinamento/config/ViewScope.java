package com.indracompany.treinamento.config;

import java.util.Map;

import javax.faces.context.FacesContext;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.config.Scope;
import org.springframework.web.context.request.FacesRequestAttributes;

public class ViewScope implements Scope {

  public static final String VALUE = "view";

  public static final String VIEW_SCOPE_CALLBACKS = "viewScope.callbacks";

  @Override
  public synchronized Object get(final String name, final ObjectFactory<?> objectFactory) {
    Object instance = this.getViewMap().get(name);
    if (instance == null) {
      instance = objectFactory.getObject();
      this.getViewMap().put(name, instance);
    }
    return instance;
  }

  @Override
  public String getConversationId() {
    final FacesContext facesContext = FacesContext.getCurrentInstance();
    final FacesRequestAttributes facesRequestAttributes = new FacesRequestAttributes(facesContext);
    return facesRequestAttributes.getSessionId() + "-" + facesContext.getViewRoot().getViewId();
  }

  private Map<String, Object> getViewMap() {
    return FacesContext.getCurrentInstance().getViewRoot().getViewMap();
  }

  @Override
  public void registerDestructionCallback(final String name, final Runnable runnable) {
    final Map<String, Runnable> callbacks = (Map<String, Runnable>) this.getViewMap().get(ViewScope.VIEW_SCOPE_CALLBACKS);
    if (callbacks != null) {
      callbacks.put(name, runnable);
    }
  }

  @Override
  public Object remove(final String name) {
    final Object instance = this.getViewMap().remove(name);
    if (instance != null) {
      final Map<String, Runnable> callbacks = (Map<String, Runnable>) this.getViewMap().get(ViewScope.VIEW_SCOPE_CALLBACKS);
      if (callbacks != null) {
        callbacks.remove(name);
      }
    }
    return instance;
  }

  @Override
  public Object resolveContextualObject(final String name) {
    final FacesContext facesContext = FacesContext.getCurrentInstance();
    final FacesRequestAttributes facesRequestAttributes = new FacesRequestAttributes(facesContext);
    return facesRequestAttributes.resolveReference(name);
  }

}
