package com.indracompany.treinamento.util;

import java.io.IOException;
import java.io.Serializable;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.el.MethodExpression;
import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.component.EditableValueHolder;
import javax.faces.component.UIColumn;
import javax.faces.component.UICommand;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.component.html.HtmlDataTable;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.view.facelets.FaceletContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.indracompany.treinamento.exception.AplicacaoException;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * @author efmendes
 *
 *         Classe utilitária que abstrai métodos para disponibilizar o acesso aos recursos do faces.
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FacesUtil {

  private static final String CHAVE_BEAN_ACIONADOR = "beanAcionador";

  private static final Logger LOGGER = LoggerFactory.getLogger(FacesUtil.class);

  public static final String FACES_REDIRECT = "faces-redirect";
  public static final String INCLUDE_VIEW_PARAMS = "includeViewParams";

  /**
   *
   * Adiciona um atributo no escopo flash.
   *
   * @param chave a chave que identifica o atributo
   * @param valor o valor do atributo
   */

  public static void adicionarAtributoFlash(final String chave, final Object valor) {

    obterFacesContext().getExternalContext().getFlash().put(chave, valor);

  }

  /**
   * Remove um atributo no escopo flash
   * 
   * @param chave a chave que identifica o atributo
   */
  public static void removerAtributoFlash(final String chave) {
    obterFacesContext().getExternalContext().getFlash().remove(chave);
  }

  /**
   * Adiciona um atributo no escopo de request.
   *
   * @param chave a chave que identifica o atributo
   * @param valor o valor do atributo
   */
  public static void adicionarAtributoRequest(final String chave, final Object valor) {

    obterRequest().setAttribute(chave, valor);

  }

  /**
   * Adiciona um atributo na sessão.
   *
   * @param chave a chave que identifica o atributo
   * @param valor o valor do atributo
   */
  public static void adicionarAtributoSessao(final String chave, final Serializable valor) {

    obterSessao().setAttribute(chave, valor);

  }

  private static void appendParametrosForm(final StringBuilder parametrosForm, final String key, final String o) {
    try {
      if (o != null && !o.isEmpty()) {
        parametrosForm.append("\n[" + key + " ->" + o + "]");
      }
    } catch (final Exception e) {
      logWarn(key);
    }
  }

  private static String extrairParametrosObject(final Map<String, Object> mapParam) {
    final StringBuilder parametrosForm = new StringBuilder();
    for (final Map.Entry<String, Object> entry : mapParam.entrySet()) {
      final String key = entry.getKey();
      try {
        final Object o = entry.getValue();
        if (key != null && o != null) {
          parametrosForm.append("\n[" + key + " ->" + new Gson().toJson(o) + "]");
        }
      } catch (final Exception e) {
        logWarn(key);
      }
    }
    return parametrosForm.toString();
  }

  private static String extrairParametrosString(final Map<String, String[]> mapParam) {
    final StringBuilder parametrosForm = new StringBuilder();
    for (final Map.Entry<String, String[]> entry : mapParam.entrySet()) {
      final String key = entry.getKey();
      try {
        final List<String> values = Arrays.asList(entry.getValue());
        if (key != null && key.contains(":") && values != null && !values.isEmpty()) {
          for (final String o : values) {
            appendParametrosForm(parametrosForm, key, o);
          }

        }
      } catch (final Exception e) {
        logWarn(key);
      }
    }
    return parametrosForm.toString();
  }

  /**
   * Invalida a sessão corrente.
   */
  public static void invalidarSessao() {

    obterSessao().invalidate();
  }

  private static void logWarn(final String key) {
    FacesUtil.LOGGER.warn("erro ao tentar obter informacoes da requisicao web {}", key);
  }

  public static void manterMensagens() {
    obterFacesContext().getExternalContext().getFlash().setKeepMessages(true);
  }

  /**
   * Obtem o valor de um atributo solicitado.
   *
   * @param idAtributo o identificador do atributo
   * @return
   */
  public static String obterAtributoComoString(final String idAtributo) {

    return obterFacesContext().getExternalContext().getRequestParameterMap().get(idAtributo);
  }

  /**
   * Obtem um atributo do componente.
   *
   * @param chave a chave do atributo.
   * @return o atributo.
   */
  public static Object obterAtributoComponente(final String chave) {
    return UIComponent.getCurrentComponent(obterFacesContext()).getAttributes().get(chave);
  }

  public static Object obterAtributoDoComponente(final String nomeAtributo) {
    return UIComponent.getCurrentComponent(obterFacesContext()).getAttributes().get(nomeAtributo);
  }

  /**
   * Obtem um atributo do escopo flash.
   *
   * @param chave a chave do atributo.
   * @return o atributo.
   */
  public static Object obterAtributoFlash(final String chave) {
    return obterFacesContext().getExternalContext().getFlash().get(chave);
  }

  /**
   * Obtem um atributo do request.
   *
   * @param chave a chave do atributo.
   * @return o atributo.
   */
  public static Object obterAtributoRequest(final String chave) {

    return obterRequest().getAttribute(chave);
  }

  /**
   * Obtem um atributo da sessão.
   *
   * @param chave a chave do atributo.
   * @return o atributo.
   */
  public static Object obterAtributoSessao(final String chave) {

    return obterSessao().getAttribute(chave);
  }

  /**
   * Retorna o nome do managed bean que executou a ação atual do sistema.
   *
   * @return o nome do managed bean que executou a ação atual do sistema
   */
  public static String obterBeanAcionador() {

    return obterAtributoRequest(FacesUtil.CHAVE_BEAN_ACIONADOR).toString();
  }

  /**
   * Obtem o caminho completo de um determinado caminho relativo da aplicação.
   *
   * @param caminho o caminho
   * @return o caminho completo
   */
  public static String obterCaminhoReal(final String caminho) {

    final ServletContext sc = (ServletContext) obterFacesContext().getExternalContext().getContext();
    return sc.getRealPath(caminho);
  }

  /**
   * Obtem as colunas de um HTMLDataTable passado como parametro
   *
   * @param table HTMLdataTable passado como parametro.
   * @return um List contendo UIColumns.
   */
  public static List<UIComponent> obterColunas(final HtmlDataTable table) {
    final List<UIComponent> columns = new ArrayList<>();

    for (int i = 0; i < table.getChildCount(); i++) {
      final UIComponent child = table.getChildren().get(i);
      if (child instanceof UIColumn && !(child.getChildren().isEmpty() || child.getChildren().get(0) instanceof EditableValueHolder)) {
        columns.add(child);
      }
    }
    return columns;
  }

  /**
   * Obtem um componente.
   *
   * @param idComponente o id do componente
   * @return o componente
   */
  public static UIComponent obterComponente(final String idComponente) {

    return obterFacesContext().getViewRoot().findComponent(idComponente);
  }

  /**
   * Obtem o faces context do bean em execução.
   *
   * @return FacesContext.
   */
  public static FacesContext obterFacesContext() {

    return FacesContext.getCurrentInstance();
  }

  /**
   * @author Eder Ferreira
   *
   *         Retorna a pagina de origem e os parametros da requisicao http
   * @param request
   * @return String
   */
  public static final String obterInformacoesDoRequest(final HttpServletRequest request) {
    final StringBuilder infoRequest = new StringBuilder("");
    try {
      infoRequest.append("\n\nPagina: " + request.getRequestURI());
      final String userAgent = request.getHeader("User-Agent");
      if (StringUtils.isNotEmpty(userAgent)) {
        infoRequest.append("\n\nAmbiente: " + userAgent);
      }
      final String login = request.getRemoteUser();
      if (StringUtils.isNotEmpty(login)) {
        infoRequest.append("\n\nLogin: " + login);
      }
      final String ip = request.getRemoteAddr();
      if (StringUtils.isNotEmpty(ip)) {
        infoRequest.append("\n\nIP: " + ip);
      }
      if (StringUtils.isNotEmpty(request.getQueryString())) {
        infoRequest.append("\n[Parametros GET: " + request.getQueryString() + "]");
      }
      final Map<String, String[]> mapParam = request.getParameterMap();

      if (mapParam != null) {
        final String parametrosForm = extrairParametrosString(mapParam);
        if (StringUtils.isNotEmpty(parametrosForm)) {
          infoRequest.append("\n\nParametros POST: " + parametrosForm);
        }
      }

      final Map<String, Object> mapParam2 = obterFacesContext().getExternalContext().getSessionMap();
      if (mapParam2 != null) {
        final String parametrosForm = extrairParametrosObject(mapParam2);
        if (StringUtils.isNotEmpty(parametrosForm)) {
          infoRequest.append("\n Session Map: " + parametrosForm);
        }
      }

    } catch (final Exception e) {
      FacesUtil.LOGGER.warn("erro ao tentar obter informacoes da requisicao web", e);
    }
    return infoRequest.toString();
  }

  /**
   * Obtem o managed bean solicitado.
   *
   * @param nomeManagedBean o nome do managed bean
   * @return o managed bean de interesse
   */
  public static Object obterManagedBean(final String nomeManagedBean) {
    return obterFacesContext().getApplication().evaluateExpressionGet(obterFacesContext(), "#{" + nomeManagedBean + "}", Object.class);
  }

  /**
   * Eder Ferreira
   *
   * Metodo que sobrescreve os valores dos parametros, na descricao de uma mensagem obtida atraves do
   * arquivo .properties pelo codigo da mensagem
   *
   * @param codMsg
   * @param values
   * @return String
   */
  public static String obterMensagemProperties(final String codMsg, final String... params) {
    final String line = obterTextoMessagesProperties(codMsg);
    return obterTextoReplaceMessagesProperties(line, params);
  }

  /**
   * Obtem o nome do contexto.
   *
   * @return o nome do contexto.
   */
  public static String obterNomeContexto() {

    return obterFacesContext().getExternalContext().getRequestContextPath();
  }

  /**
   * Obtem o nome do managed bean a partir de uma determinada JSF-EL. Ex: obtem manterReceitaBean a
   * partir de #{manterReceitaBean.carregar}
   *
   * @param expressao a JSF-EL a ser analisada
   * @return o nome do managed bean
   */
  public static String obterNomeManagedBean(final String expressao) {

    final String patternStr = "#\\{(.*)\\.";

    // Compile and use regular expression
    final Pattern pattern = Pattern.compile(patternStr);
    final Matcher matcher = pattern.matcher(expressao);
    final boolean matchFound = matcher.find();

    if (matchFound) {
      return matcher.group(1);
    }
    return expressao;
  }

  /**
   * Obtem um atributo do request.
   *
   * @param chave a chave do atributo.
   * @return o atributo.
   */
  public static Object obterParametroFacelet(final String chave) {
    final FaceletContext faceletContext = (FaceletContext) obterFacesContext().getAttributes().get(FaceletContext.FACELET_CONTEXT_KEY);
    return faceletContext.getAttribute(chave);
  }

  /**
   * Retorna um HttpServletRequest contendo a requisição http atual.
   *
   * @return um HttpServletRequest contendo a requisição http atual
   */
  public static HttpServletRequest obterRequest() {

    return (HttpServletRequest) obterFacesContext().getExternalContext().getRequest();
  }

  public static HttpServletResponse obterResponse() {
    return (HttpServletResponse) obterFacesContext().getExternalContext().getResponse();

  }

  /**
   * Obtem a sessão do usuário.
   *
   * @return a sessão.
   */
  public static HttpSession obterSessao() {

    return (HttpSession) obterFacesContext().getExternalContext().getSession(false);
  }

  /**
   * Obtem o conteúdo da chave no arquivo de mensagens. Caso não encontrar, retorna apenas o valor da
   * key.
   *
   * @param key a chave a ser pesquisada.
   * @return texto contido na key.
   */
  public static String obterTextoMessagesProperties(final String key) {

    String texto = null;

    try {
      final ResourceBundle bundle = FacesUtil.obterFacesContext().getApplication().getResourceBundle(FacesUtil.obterFacesContext(), "msg");
      texto = bundle.getString(key);
    } catch (final MissingResourceException e) {
      texto = key;
    } catch (final NullPointerException e) {
      texto = Messages.getMessage(key);
    }

    return texto;
  }

  /**
   * Obtem o conteúdo da chave no arquivo de mensagens parametizada. Caso não encontrar, retorna
   * apenas o valor da key.
   *
   * @param key a chave a ser pesquisada.
   * @param params parametros a serem incluidos no texto.
   *
   * @return texto da chave parametizado.
   */

  public static String obterTextoMessagesProperties(final String key, final Object[] params) {

    String texto = obterTextoMessagesProperties(key);

    if (params != null && params.length > 0) {
      final MessageFormat mf = new MessageFormat(texto);
      texto = mf.format(params, new StringBuffer(), null).toString();
    }

    return texto;
  }

  /**
   * @author Eder Ferreira
   *
   *         Metodo que sobrescreve os valores dos parametros, na descricao de uma mensagem
   * @param descMsg
   * @param params
   * @return String
   */
  public static String obterTextoReplaceMessagesProperties(String descMsg, final String... params) {
    if (descMsg != null && params != null) {
      final MessageFormat mf = new MessageFormat(descMsg);
      descMsg = mf.format(params, new StringBuffer(), null).toString();
    }
    return descMsg;
  }

  public static void redirecionar(final String url) {
    try {
      obterFacesContext().getExternalContext().redirect(obterNomeContexto() + url);
    } catch (final IOException e) {
      FacesUtil.LOGGER.warn("nao foi possivel redirecionar o acesso do usuario", e);
    }

  }

  public static void redirecionarSemContextPath(final String url) {
    try {
      obterFacesContext().getExternalContext().redirect(url);
    } catch (final IOException e) {
      FacesUtil.LOGGER.warn("nao foi possivel redirecionar o acesso do usuario", e);
    }

  }

  /**
   * Registra mensagem de aviso no contexto.
   *
   * @param key a chave da mensagem.
   */
  public static void registrarAviso(final String key) {

    final String texto = obterTextoMessagesProperties(key);
    registrarFacesMessage(texto, FacesMessage.SEVERITY_WARN);
  }

  /**
   * Registra mensagem de aviso no contexto.
   *
   * @param key a chave da mensagem.
   * @param param o(s) parametro(s) da mensagem.
   */
  public static void registrarAviso(final String key, final String... param) {

    final String texto = obterTextoMessagesProperties(key, param);
    registrarFacesMessage(texto, FacesMessage.SEVERITY_WARN);
  }

  /**
   * Registra o nome do managed bean que executou a ação atual do sistema.
   *
   * @param evento o evento executado no sistema
   */
  public static void registrarBeanAcionador(final ActionEvent evento) {

    final UICommand acionador = (UICommand) evento.getSource();
    final MethodExpression acao = acionador.getActionExpression();
    String nomeManagedBeanAcionador;
    if (acao == null) {
      nomeManagedBeanAcionador = null;
    } else {
      final String expressao = acao.getExpressionString();
      nomeManagedBeanAcionador = obterNomeManagedBean(expressao);
      if (expressao.equals(nomeManagedBeanAcionador)) {
        nomeManagedBeanAcionador = (String) acionador.getAttributes().get("nomeBean");
      }
    }
    adicionarAtributoRequest(FacesUtil.CHAVE_BEAN_ACIONADOR, nomeManagedBeanAcionador);
  }

  /**
   * Registra o erro de negócio no contexto.
   *
   * @param systemException a exceção a ser tratada.
   */
  public static void registrarErro(final AplicacaoException systemException) {

    registrarErro(systemException, FacesMessage.SEVERITY_ERROR);
  }

  /**
   * Registra o erro de negócio no contexto.
   *
   * @param systemException a exceção a ser tratada
   * @param severidade o nivel de severidade
   */
  private static void registrarErro(final AplicacaoException systemException, final Severity severidade) {

    final String chave = systemException.getCustomExceptionValue().getValidacao().getCodigoMsg();

    final List<String> paramsList = systemException.getCustomExceptionValue().getParametros();
    final String[] params = new String[paramsList.size()];

    int i = 0;
    for (final String param : paramsList) {
      params[i] = param;
      i = i + 1;
    }
    if (severidade.equals(FacesMessage.SEVERITY_ERROR)) {
      registrarErro(chave, params);
    } else {
      registrarErroFatal(chave, params);
    }
  }

  /**
   * Registra mensagem de erro no contexto.
   *
   * @param key a chave da mensagem.
   */
  public static void registrarErro(final String key) {

    final String texto = obterTextoMessagesProperties(key);
    registrarFacesMessage(texto, FacesMessage.SEVERITY_ERROR);
  }

  /**
   * Registra mensagem de erro no contexto.
   *
   * @param key a chave da mensagem.
   * @param param o(s) parametro(s) da mensagem.
   */
  public static void registrarErro(final String key, final String... param) {

    final String texto = obterTextoMessagesProperties(key, param);
    registrarFacesMessage(texto, FacesMessage.SEVERITY_ERROR);
  }

  /**
   * Registra o erro de negócio no contexto.
   *
   * @param systemException a exceção a ser tratada.
   */
  public static void registrarErroFatal(final AplicacaoException systemException) {

    registrarErro(systemException, FacesMessage.SEVERITY_FATAL);
  }

  /**
   * Registra mensagem de erro fatal no contexto.
   *
   * @param key a chave da mensagem.
   * @param param o(s) parametro(s) da mensagem.
   */
  public static void registrarErroFatal(final String key, final String... param) {

    final String texto = obterTextoMessagesProperties(key, param);
    registrarFacesMessage(texto, FacesMessage.SEVERITY_FATAL);
  }

  public static void registrarErroValidacao(final String id, final String mensagem) {

    final String texto = obterTextoMessagesProperties(mensagem);

    final FacesContext fc = obterFacesContext();
    final UIComponent root = fc.getViewRoot();
    final UIInput component = (UIInput) root.findComponent(id);
    component.setValid(false);
    component.setValidatorMessage(texto);
    fc.addMessage(component.getClientId(fc), new FacesMessage(FacesMessage.SEVERITY_ERROR, "", texto));
    FacesUtil.obterFacesContext().validationFailed();
  }

  /**
   * Registra a mensagem no contexto pelo tipo de severidade.
   *
   * @param texto da mensagem.
   * @param severidade da mensagem.
   */
  public static void registrarFacesMessage(final String texto, final FacesMessage.Severity severidade) {

    final FacesMessage mensagem = new FacesMessage();

    mensagem.setDetail("");
    mensagem.setSummary(texto);
    mensagem.setSeverity(severidade);

    obterFacesContext().addMessage(null, mensagem);
  }

  /**
   * Registra mensagem no contexto.
   *
   * @param key a chave da mensagem.
   */
  public static void registrarMensagem(final String key) {

    final String texto = obterTextoMessagesProperties(key);
    registrarFacesMessage(texto, FacesMessage.SEVERITY_INFO);
  }

  /**
   * Registra mensagem no contexto.
   *
   * @param key a chave da mensagem.
   * @param param o(s) parametro(s) da mensagem.
   */
  public static void registrarMensagem(final String key, final String... param) {
    final String texto = obterTextoMessagesProperties(key, param);
    registrarFacesMessage(texto, FacesMessage.SEVERITY_INFO);
  }

  /**
   * Remove um atributo da sessão.
   *
   * @param chave a chave que identifica o atributo
   */
  public static void removerAtributoSessao(final String chave) {
    obterSessao().removeAttribute(chave);
  }

  public static boolean isPostback() {
    return obterFacesContext().isPostback();
  }

  public static boolean isResponseCommitted() {
    return obterFacesContext().getExternalContext().isResponseCommitted();
  }

}
