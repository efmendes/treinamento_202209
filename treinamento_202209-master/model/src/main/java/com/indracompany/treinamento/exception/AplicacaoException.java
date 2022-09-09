package com.indracompany.treinamento.exception;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.LinkedList;
import java.util.List;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen")
public class AplicacaoException extends RuntimeException {

  private static final long serialVersionUID = 1L;
  private static final String MSG_DEFAULT = "Erro de Validação!";

  /**
   * Retorna a pilha de erros da exceção.
   *
   * @param exception
   * @return Uma String com a pilha de erros.
   */
  public static final String getPilhaErro(final Throwable t) {
    final StringWriter writer = new StringWriter();
    final PrintWriter printWriter = new PrintWriter(writer);
    t.printStackTrace(printWriter);
    return writer.toString();

  }


  /**
   * Verifica se o Throwable passado por parametro contém uma exceção do tipo
   * {@link AplicacaoException}, caso contrário retorna null.
   *
   * Obs: Este método retorna o PRIMEIRO {@link AplicacaoException} encontrado na stack (pilha), ou
   * seja, o ÚLTIMO {@link AplicacaoException} capturado (catch) durante o lançamento do primeiro
   * throws.
   *
   * @author Eder
   *
   * @param throwable A exceção a ser analisada.
   * @return Uma exceção de negócio ou null caso a exceção passada como parâmetro não contenha uma
   *         instância de {@link AplicacaoException}.
   */
  public static final AplicacaoException getPrimeiroCustomException(final Throwable throwable) {
    AplicacaoException retorno = null;
    if (throwable != null) {
      if (throwable instanceof AplicacaoException) {
        retorno = (AplicacaoException) throwable;
      } else {
        retorno = getPrimeiroCustomException(throwable.getCause());
      }
    }
    return retorno;
  }

  /**
   * Verifica se o Throwable passado por parametro contém uma exceção do tipo
   * {@link AplicacaoException}, caso contrário retorna null.
   *
   * Obs: Este método retorna o ÚLTIMO {@link AplicacaoException} encontrado na stack (pilha), ou
   * seja, o PRIMEIRO {@link AplicacaoException} capturado (catch) no lançamento do primeiro throws,
   * que provavelmente será a origem do erro. Este método é util para identificar se um
   * {@link AplicacaoException} contém outro {@link AplicacaoException} encapsulado.
   *
   *
   * @param throwable A exceção a ser analisada.
   * @return Uma exceção de negócio ou null caso a exceção passada como parâmetro não contenha uma
   *         instância de {@link AplicacaoException}.
   */
  public static final AplicacaoException getUltimoCustomException(final Throwable throwable) {

    AplicacaoException retorno = null;

    if (throwable instanceof AplicacaoException) {
      retorno = (AplicacaoException) throwable;
      if (throwable.getCause() instanceof AplicacaoException) {
        retorno = getUltimoCustomException(throwable.getCause());
      }
    }
    return retorno;
  }

  private transient List<AplicacaoExceptionValue> customExceptionValues = new LinkedList<>();

  public AplicacaoException(final AplicacaoExceptionValidacoes validacao) {
    super(validacao.getCodigoMsg());
    this.customExceptionValues.add(new AplicacaoExceptionValue(validacao));
  }

  public AplicacaoException(final AplicacaoExceptionValidacoes validacao, final List<AplicacaoExceptionValue> customExceptionValues) {
    this(validacao);

    if (customExceptionValues != null) {
      this.customExceptionValues.addAll(customExceptionValues);
    }
  }

  public AplicacaoException(final AplicacaoExceptionValidacoes validacao, final String... params) {
    super(validacao.getCodigoMsg());
    this.customExceptionValues.add(new AplicacaoExceptionValue(validacao, params));
  }

  public AplicacaoException(final AplicacaoExceptionValidacoes validacao, final Throwable throwable, final String... params) {
    super(validacao.getCodigoMsg(), throwable);
    this.customExceptionValues.add(new AplicacaoExceptionValue(validacao, params));
  }

  public AplicacaoException(final List<String> detalheErrosValidacao) {
    super(AplicacaoException.MSG_DEFAULT);
    this.customExceptionValues.add(new AplicacaoExceptionValue(detalheErrosValidacao));
  }

  public AplicacaoException(final String erroMessagem) {
    super(erroMessagem);
  }

  public AplicacaoException(final String erroMessagem, final Throwable cause) {
    super(erroMessagem, cause);
  }

  public AplicacaoExceptionValue getCustomExceptionValue() {
    return this.customExceptionValues.iterator().next();
  }

  public List<AplicacaoExceptionValue> getCustomExceptionValues() {
    return this.customExceptionValues;
  }

  public void setCustomExceptionValues(final List<AplicacaoExceptionValue> customExceptionValues) {
    this.customExceptionValues = customExceptionValues;
  }

}
