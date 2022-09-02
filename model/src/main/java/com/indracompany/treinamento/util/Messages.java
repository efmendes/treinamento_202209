package com.indracompany.treinamento.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.text.MessageFormat;
import java.util.Enumeration;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

import javax.faces.context.FacesContext;

import org.apache.commons.lang3.StringUtils;

public class Messages extends ResourceBundle {

  protected static class UTF8Control extends Control {
    @Override
    public ResourceBundle newBundle(final String baseName, final Locale locale, final String format, final ClassLoader loader, final boolean reload)
        throws IllegalAccessException, InstantiationException, IOException {

      final String bundleName = this.toBundleName(baseName, locale);
      final String resourceName = this.toResourceName(bundleName, Messages.BUNDLE_EXTENSION);
      ResourceBundle bundle = null;
      InputStream stream = null;
      if (reload) {
        final URL url = loader.getResource(resourceName);
        if (url != null) {
          final URLConnection connection = url.openConnection();
          if (connection != null) {
            connection.setUseCaches(false);
            stream = connection.getInputStream();
          }
        }
      } else {
        stream = loader.getResourceAsStream(resourceName);
      }

      if (stream != null) {
        try {
          bundle = new PropertyResourceBundle(new InputStreamReader(stream, StandardCharsets.UTF_8));
        } finally {
          stream.close();
        }
      }
      return bundle;
    }
  }

  protected static final String BUNDLE_NAME = "messages";
  protected static final String BUNDLE_EXTENSION = "properties";
  protected static final String CHARSET = "UTF-8";
  protected static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(BUNDLE_NAME);

  protected static final Control UTF8_CONTROL = new UTF8Control();

  public Messages() {
    this.setParent(ResourceBundle.getBundle(Messages.BUNDLE_NAME, FacesContext.getCurrentInstance().getViewRoot().getLocale(), Messages.UTF8_CONTROL));
  }

  @Override
  public Enumeration<String> getKeys() {
    return this.parent.getKeys();
  }

  @Override
  protected Object handleGetObject(final String key) {
    return this.parent.getObject(key);
  }


  public static boolean hasMessage(String key) {
    try {
      return RESOURCE_BUNDLE.getObject(key) != null;
    } catch (MissingResourceException mre) {
      return false;
    }
  }

  /**
   * Recupera uma mensagem do arquivo de properties.
   * @param key A chave da propriedade.
   * @return O valor da propriedade.
   */
  public static String getMessage(String key) {
    return getMessage(RESOURCE_BUNDLE, key);
  }

  /**
   * @author Eder Ferreira
   * Metodo que retorna a descricao de uma mensagem informada no arquivo .properties
   * @param bundle
   * @param codMsg
   * @param params
   * @return String
   */
  public static String getMessage(ResourceBundle bnd, String codMsg, String... params) {
    String retorno = null;
    if (bnd != null && codMsg != null) {
      boolean existeMsg = bnd.containsKey(codMsg);
      while (existeMsg) {
        retorno = bnd.getString(codMsg);
        if (StringUtils.isNotEmpty(retorno)) {
          if (retorno.startsWith("${") && retorno.trim().endsWith("}")) {
            codMsg = retorno.replaceFirst("\\$", "").replaceFirst("\\{", "").replaceFirst("\\}", "").trim();
            existeMsg = bnd.containsKey(codMsg);
          } else {
            existeMsg = false;
          }
        } else {
          existeMsg = false;
        }
      }
    }
    return getTextMessageReplace(retorno, params);
  }

  /**
   * Metodo que sobrescreve os valores dos parametros, na descricao de uma mensagem
   * @param descMsg
   * @param params
   * @return String
   */
  public static String getTextMessageReplace(String descMsg, String... params) {
    if (descMsg != null && params != null) {
      MessageFormat mf = new MessageFormat(descMsg);
      descMsg = mf.format(params, new StringBuffer(), null).toString();
    }
    return descMsg;
  }

  /**
   * Metodo que sobrescreve os valores dos parametros, na descricao de uma mensagem obtida atraves do
   * arquivo .properties pelo codigo da mensagem
   * 
   * @param codMsg
   * @param values
   * @return String
   */
  public static String getMessageReplace(String codMsg, String... params) {
    String line = RESOURCE_BUNDLE.getString(codMsg);
    return getTextMessageReplace(line, params);
  }


}
