package com.indracompany.treinamento.exception;

import java.util.Iterator;
import java.util.List;

import javax.faces.application.ViewExpiredException;
import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerWrapper;
import javax.faces.context.FacesContext;
import javax.faces.event.ExceptionQueuedEvent;
import javax.faces.event.ExceptionQueuedEventContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import com.indracompany.treinamento.util.FacesUtil;

import lombok.extern.slf4j.Slf4j;

/**
 * @author efmendes
 *
 */
@Slf4j
public class AplicacaoExceptionHandler extends ExceptionHandlerWrapper {

  private static final String ILLEGAL_HEX_CHARACTERS = "Illegal hex characters";
  private ExceptionHandler wrapped;


  public AplicacaoExceptionHandler() {
    super();
  }

  public AplicacaoExceptionHandler(final ExceptionHandler exception) {
    this.wrapped = exception;
  }

  /**
   * Atualiza informacoes do erros ocorridos para atualizar as estatisticas das exceptions
   *
   * @param Throwable e
   */
  private void atualizarInformaErrosInesperados(final Throwable e) {
    final String msgErro = e.getLocalizedMessage();
    if (msgErro != null && !msgErro.trim().isEmpty() && !msgErro.trim().equalsIgnoreCase("null")) {
      final String pilhaErro = AplicacaoException.getPilhaErro(e);

      String infoRequest = "";
      try {
        final HttpServletRequest request = FacesUtil.obterRequest();
        infoRequest = FacesUtil.obterInformacoesDoRequest(request);

        final StringBuilder erro = new StringBuilder("[SISTEMA] Erro Inesperado").append(" - ").append(msgErro).append("\n Request: ").append(infoRequest)
            .append("\n Pilha Erro: ").append(pilhaErro);

        log.error(erro.toString());


      } catch (final Exception ex) {
        log.error("erro ao tentar obter detalhes da ocorrencia de erro", ex);
      }

    } else {
      log.error("erro ao tentar obter detalhes de erro null", e);
    }
  }

  @Override
  public ExceptionHandler getWrapped() {
    return this.wrapped;
  }

  @Override
  public void handle() {

    final Iterator<ExceptionQueuedEvent> i = this.getUnhandledExceptionQueuedEvents().iterator();
    while (i.hasNext()) {
      final ExceptionQueuedEvent event = i.next();
      final ExceptionQueuedEventContext context = (ExceptionQueuedEventContext) event.getSource();

      final Throwable t = context.getException();

      final FacesContext fc = FacesContext.getCurrentInstance();

      try {

        FacesUtil.obterFacesContext().validationFailed();

        this.tratarErro(t);

        fc.renderResponse();

      } finally {

        i.remove();
      }
    }

    this.getWrapped().handle();
  }

  private void setaMensagemERegistra(final Throwable t, final AplicacaoExceptionValue cve) {
    List<String> params = cve.getParametros();
    if (cve.isCampoView()) {
      params = cve.getParametrosLabels();
    }

    final StringBuilder mensagem =
        new StringBuilder().append(cve.getValidacao().getDescricaoMsg(params != null ? params.toArray(new String[params.size()]) : null));

    final String mensagemAux = StringUtils.isNotEmpty(cve.getValidacao().getCodigoMsgAuxiliar())
        ? cve.getValidacao().getDescricaoMsgAuxiliar(params != null ? params.toArray(new String[params.size()]) : null)
        : StringUtils.EMPTY;

    if (StringUtils.isNotEmpty(mensagemAux)) {
      mensagem.append(" - ").append(mensagemAux);
    }

    if (StringUtils.isNotEmpty(mensagem.toString())) {
      if (cve.getValidacao().getSeveridade().equals(AplicacaoExceptionValidacoes.SEVERIDADE_ALERTA)) {
        FacesUtil.registrarAviso(mensagem.toString());
      } else {
        FacesUtil.registrarErroFatal(mensagem.toString());
        log.error(t.getMessage(), t);
      }
    } else {
      FacesUtil.registrarErroFatal(cve.getValidacao().getCodigoMsg());
      log.error(t.getMessage(), t);
    }
  }

  public void tratarErro(final Throwable t) {

    final AplicacaoException aplicacaoException = AplicacaoException.getPrimeiroCustomException(t);
    if (aplicacaoException != null) {
      this.tratarErroPeloAplicacaoException(t, aplicacaoException);
    } else if (t instanceof ViewExpiredException) {

      FacesUtil.registrarErroFatal(ExceptionValidacoes.ERRO_ACESSO_SISTEMA.getCodigoMsg());
      FacesUtil.redirecionar("/index.xhtml");

    } else {

      if (t.getMessage() != null && t.getMessage().contains(ILLEGAL_HEX_CHARACTERS)) {
        log.error("Ocorreu um Erro Inesperado: " + t.getMessage(), t);
        FacesUtil.registrarErroFatal("msg.erro.caracteres.especiais");
        return;
      }

      try {
        this.atualizarInformaErrosInesperados(t);
      } catch (final Exception e) {
        log.error("erro ao tentar enviar enviar informacoes do erro", e);
      }

      FacesUtil.registrarErroFatal(ExceptionValidacoes.ERRO_ACESSO_SISTEMA.getCodigoMsg());
    }
  }

  private void tratarErroPeloAplicacaoException(final Throwable t, final AplicacaoException aplicacaoException) {
    if (CollectionUtils.isEmpty(aplicacaoException.getCustomExceptionValues())) {

      final StringBuilder mensagem = new StringBuilder().append(FacesUtil.obterTextoMessagesProperties(aplicacaoException.getMessage()));

      FacesUtil.registrarErroFatal(mensagem.toString());

      log.error(t.getMessage(), t);
    } else {
      for (final AplicacaoExceptionValue cve : aplicacaoException.getCustomExceptionValues()) {

        if (cve.getValidacao() != null) {
          this.setaMensagemERegistra(t, cve);
          continue;
        } else if (!CollectionUtils.isEmpty(cve.getDetalheErrosValidacao())) {
          FacesUtil.registrarErro(cve.getDetalheErrosValidacao().iterator().next());
        } else {
          final StringBuilder mensagem = new StringBuilder().append(FacesUtil.obterTextoMessagesProperties(cve.getDetalheErrosValidacao().iterator().next()));
          FacesUtil.registrarErroFatal(mensagem.toString());
        }
        log.error("Ausencia de parametros em {}", AplicacaoException.class.getSimpleName());
      }
    }
  }

}
