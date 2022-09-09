package com.indracompany.treinamento.exception;

import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerFactory;

/**
 * @author efmendes
 *
 */
public class AplicacaoExceptionHandlerFactory extends ExceptionHandlerFactory {

  private ExceptionHandlerFactory parent;

  public AplicacaoExceptionHandlerFactory(final ExceptionHandlerFactory parent) {
    this.parent = parent;
  }

  @Override
  public ExceptionHandler getExceptionHandler() {

    return new AplicacaoExceptionHandler(this.parent.getExceptionHandler());
  }

}
