package com.indracompany.treinamento.exception;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class ExceptionVO implements Serializable {

  private static final long serialVersionUID = 1L;

  private String codigo;
  private String tipo = "erro";
  private Set<String> detalhes;
  private Integer httpStatus;


  public ExceptionVO() {
    this.detalhes = new HashSet<>();
  }

  public ExceptionVO(final String codigo, Set<String> detalhes) {
    super();
    this.codigo = codigo;

    if (detalhes == null) {
      detalhes = new HashSet<>();
    }

    this.detalhes = detalhes;
  }

  public ExceptionVO(final String error, final String detalhe) {
    super();
    this.codigo = error;

    final String[] partes = detalhe.split(System.getProperty("line.separator"));
    this.detalhes = new HashSet<>();
    if (partes.length >= 0) {


      for (final String s : partes) {
        this.detalhes.add(s);
      }

    } else {
      this.detalhes.add(detalhe);
    }

  }

  public void addDetalhe(final String detalhe) {
    this.detalhes.add(detalhe);
  }

  public void addDetalhes(final Set<String> detalhes) {
    this.detalhes.addAll(detalhes);
  }

  public String getCodigo() {
    return this.codigo;
  }

  public Set<String> getDetalhes() {
    return Collections.unmodifiableSet(this.detalhes);
  }

  public Integer getHttpStatus() {
    return this.httpStatus;
  }

  public String getTipo() {
    return this.tipo;
  }

  public void setCodigo(final String error) {
    this.codigo = error;
  }

  public void setHttpStatus(final Integer httpStatus) {
    this.httpStatus = httpStatus;
  }

  public void setTipo(final String alerta) {
    this.tipo = alerta;
  }


}
