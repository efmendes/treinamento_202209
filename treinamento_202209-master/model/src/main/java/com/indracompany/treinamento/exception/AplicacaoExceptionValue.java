package com.indracompany.treinamento.exception;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import com.indracompany.treinamento.util.FacesUtil;


/**
 * @author efmendes
 *
 */
public class AplicacaoExceptionValue {

  private AplicacaoExceptionValidacoes validacao;

  private List<String> parametros = new ArrayList<>();

  private List<String> detalheErrosValidacao = new LinkedList<>();

  private boolean campoView;

  public AplicacaoExceptionValue(final AplicacaoExceptionValidacoes validacao) {
    this.validacao = validacao;
  }

  public AplicacaoExceptionValue(final AplicacaoExceptionValidacoes validacao, final boolean campoView, final String... params) {
    this.validacao = validacao;
    this.campoView = campoView;
    this.parametros = Arrays.asList(params);
  }

  public AplicacaoExceptionValue(final AplicacaoExceptionValidacoes validacao, final String... params) {
    this.validacao = validacao;
    this.parametros = Arrays.asList(params);
  }

  public AplicacaoExceptionValue(final List<String> detalheErrosValidacao) {
    this.detalheErrosValidacao.addAll(detalheErrosValidacao);
  }

  public List<String> getDetalheErrosValidacao() {
    return this.detalheErrosValidacao;
  }

  public List<String> getParametros() {
    return this.parametros;
  }

  public List<String> getParametrosLabels() {
    final List<String> parametrosLabels = new LinkedList<>();
    if (this.campoView) {
      if (this.parametros != null) {
        for (final String param : this.parametros) {
          if (param != null) {
            final String[] campos = param.split("\\|");
            String label = "";
            for (final String c : campos) {
              if (!label.isEmpty()) {
                label += ", ";
              }
              final String message = FacesUtil.obterTextoMessagesProperties("label." + c);
              if (message != null) {
                label += message;
              } else {
                label = c;
              }

            }
            parametrosLabels.add(label);
          }
        }
      }
    }
    return parametrosLabels;
  }

  public AplicacaoExceptionValidacoes getValidacao() {
    return this.validacao;
  }

  public boolean isCampoView() {
    return this.campoView;
  }

  public void setCampoView(final boolean campoView) {
    this.campoView = campoView;
  }

  public void setDetalheErrosValidacao(final List<String> detalheErrosValidacao) {
    this.detalheErrosValidacao = detalheErrosValidacao;
  }

  public void setParametros(final List<String> parametros) {
    this.parametros = parametros;
  }

  public void setValidacao(final AplicacaoExceptionValidacoes validacao) {
    this.validacao = validacao;
  }

}
