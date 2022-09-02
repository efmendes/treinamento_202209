package com.indracompany.treinamento.exception;

import org.springframework.util.Assert;

import com.indracompany.treinamento.util.FacesUtil;


/**
 * @author efmendes
 *
 */
public final class ValidacaoCampos implements AplicacaoExceptionValidacoes {

  public static ValidacaoCampos newInstance(final String codigoMsg, final String descricao) {
    Assert.notNull(descricao, "A decricao da mensagem é obrigatorio");
    return new ValidacaoCampos(codigoMsg, descricao);
  }

  private String codigoMsg;

  private String descricao;

  private ValidacaoCampos(final String codigoMsg, final String descricao) {
    this.codigoMsg = codigoMsg;
    this.descricao = descricao;
  }

  @Override
  public String getCodigoMsg() {
    if (this.codigoMsg != null && !this.codigoMsg.isEmpty()) {
      return this.codigoMsg;
    } else {
      return this.descricao;
    }
  }

  @Override
  public String getCodigoMsgAuxiliar() {
    return null;
  }

  public String getDescricao() {
    return this.descricao;
  }

  /**
   *
   * Obtém a descrição da Mensagem deste Enum.
   *
   * @return int
   */
  @Override
  public String getDescricaoMsg(final String... params) {
    return FacesUtil.obterTextoReplaceMessagesProperties(this.descricao, params);
  }

  @Override
  public String getDescricaoMsgAuxiliar(final String... params) {
    return null;
  }

  @Override
  public Integer getSeveridade() {
    return AplicacaoExceptionValidacoes.SEVERIDADE_ERRO;
  }

  public void setCodigoMsg(final String codigoMsg) {
    this.codigoMsg = codigoMsg;
  }

  public void setDescricao(final String descricao) {
    this.descricao = descricao;
  }

}
