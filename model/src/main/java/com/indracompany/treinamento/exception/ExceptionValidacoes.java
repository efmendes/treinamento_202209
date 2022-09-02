package com.indracompany.treinamento.exception;

import com.indracompany.treinamento.util.FacesUtil;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author efmendes
 *
 */

@AllArgsConstructor
public enum ExceptionValidacoes implements AplicacaoExceptionValidacoes {

  // Mensagens de Erro
  ERRO_ACESSO_SISTEMA("msg.app.erro.acesso.sistema", null, AplicacaoExceptionValidacoes.SEVERIDADE_ERRO),
  ERRO_CAMPO_OBRIGATORIO("msg.app.erro.campo.obrigatorio", null, AplicacaoExceptionValidacoes.SEVERIDADE_ERRO),
  ERRO_EXCLUSAO_GENERICO("msg.app.erro.exclusao.generico", null, AplicacaoExceptionValidacoes.SEVERIDADE_ERRO),
  ERRO_OBJETO_NAO_ENCONTRADO("msg.app.erro.objeto.nao.encontrado", null, AplicacaoExceptionValidacoes.SEVERIDADE_ERRO),
  ERRO_VALIDACAO("msg.app.erro.validacao", null, AplicacaoExceptionValidacoes.SEVERIDADE_ERRO),
  ERRO_SERIALIZAR_JSON("msg.app.erro.serializar.json", null, AplicacaoExceptionValidacoes.SEVERIDADE_ERRO),
  ERRO_ACESSO_NEGADO_JIRA("msg.erro.acesso.negado.jira", null, AplicacaoExceptionValidacoes.SEVERIDADE_ERRO),
  ERRO_LOGIN_SENHA_INVALIDO("msg.erro.login.senha.invalido", null, AplicacaoExceptionValidacoes.SEVERIDADE_ERRO),
  ERRO_CPF_INVALIDO("msg.erro.cpf.invalido", null, AplicacaoExceptionValidacoes.SEVERIDADE_ERRO),
  ERRO_CONTA_INVALIDA("msg.erro.conta.invalida", null, AplicacaoExceptionValidacoes.SEVERIDADE_ERRO),
  ERRO_SALDO_INEXISTENTE("msg.erro.saldo.inexistente", null, AplicacaoExceptionValidacoes.SEVERIDADE_ERRO),
  ERRO_CONTA_DUPLICADA("msg.erro.conta.duplicada", null, AplicacaoExceptionValidacoes.SEVERIDADE_ERRO),
  
  

  // Mensagens Alterta
  ALERTA_NENHUM_REGISTRO_ENCONTRADO("msg.app.alerta.nenhum.registro.encontrado", null, AplicacaoExceptionValidacoes.SEVERIDADE_ALERTA),;

  @Getter
  @Setter
  private String codigoMsg;

  @Getter
  @Setter
  private String codigoMsgAuxiliar;
  @Getter
  @Setter
  private Integer severidade = AplicacaoExceptionValidacoes.SEVERIDADE_ERRO;

  public static ExceptionValidacoes carregarPorCodigoMsg(final String codigo) {
    for (final ExceptionValidacoes co : ExceptionValidacoes.values()) {
      if (codigo.equals(co.getCodigoMsg())) {
        return co;
      }
    }
    return null;
  }

  @Override
  public String getDescricaoMsg(final String... params) {
    return FacesUtil.obterTextoMessagesProperties(getCodigoMsg(), params);
  }

  @Override
  public String getDescricaoMsgAuxiliar(final String... params) {
    return FacesUtil.obterTextoMessagesProperties(getCodigoMsgAuxiliar(), params);
  }

}
