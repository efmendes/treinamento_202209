package com.indracompany.treinamento.config;

import java.util.HashMap;
import java.util.Map;

import javax.faces.component.UIViewRoot;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.PostConstructViewMapEvent;
import javax.faces.event.PreDestroyViewMapEvent;
import javax.faces.event.SystemEvent;
import javax.faces.event.ViewMapListener;

public class ViewScopeCallbackRegister implements ViewMapListener {

  @Override
  public boolean isListenerForSource(final Object source) {
    return source instanceof UIViewRoot;
  }

  @Override
  public void processEvent(final SystemEvent event) throws AbortProcessingException {
    if (event instanceof PostConstructViewMapEvent) {
      final PostConstructViewMapEvent viewMapEvent = (PostConstructViewMapEvent) event;
      final UIViewRoot viewRoot = (UIViewRoot) viewMapEvent.getComponent();
      viewRoot.getViewMap().put(ViewScope.VIEW_SCOPE_CALLBACKS, new HashMap<String, Runnable>());
    } else if (event instanceof PreDestroyViewMapEvent) {
      final PreDestroyViewMapEvent viewMapEvent = (PreDestroyViewMapEvent) event;
      final UIViewRoot viewRoot = (UIViewRoot) viewMapEvent.getComponent();
      final Map<String, Runnable> callbacks = (Map<String, Runnable>) viewRoot.getViewMap().get(ViewScope.VIEW_SCOPE_CALLBACKS);
      if (callbacks != null) {
        for (final Runnable c : callbacks.values()) {
          c.run();
        }
        callbacks.clear();
      }
    }
  }
}
