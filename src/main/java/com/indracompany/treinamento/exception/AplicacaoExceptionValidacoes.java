package com.indracompany.treinamento.exception;

/**
 * @author efmendes
 *
 */
public interface AplicacaoExceptionValidacoes {

  public static final Integer SEVERIDADE_ERRO = 1;

  public static final Integer SEVERIDADE_ALERTA = 2;

  /**
   * Código que referência a Mensagem Principal encontrada no arquivo {@link messages.properties}
   *
   * @return String
   */
  public String getCodigoMsg();

  /**
   * Código que referência a Mensagem Auxiliar encontrada no arquivo {@link messages.properties}.
   *
   * @return String
   */
  public String getCodigoMsgAuxiliar();

  /**
   * O texto que representa a mensagem do codigo do arquivo {@link messages.properties}.
   *
   * @return String
   */
  public String getDescricaoMsg(String... params);

  /**
   * O texto que representa a mensagem Auxiliar do codigo do arquivo {@link messages.properties}.
   *
   * @return String
   */
  public String getDescricaoMsgAuxiliar(String... params);

  /**
   * Severidade dita a forma que será formatado o texto no sistema web.
   *
   * Caso não seja passado, por default a severidade é SEVERIDADE_ERRO (1).
   *
   * @return int
   */
  public Integer getSeveridade();



}
