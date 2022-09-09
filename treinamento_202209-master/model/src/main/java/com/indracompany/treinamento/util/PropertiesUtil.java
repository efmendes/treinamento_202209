package com.indracompany.treinamento.util;

import java.io.IOException;
import java.io.Serializable;
import java.util.Enumeration;
import java.util.MissingResourceException;
import java.util.Properties;

@SuppressWarnings({"rawtypes"})
public final class PropertiesUtil implements Serializable {
  private static final long serialVersionUID = -1963038057821355756L;
  private static PropertiesUtil propertiesUtil = null;

  public static PropertiesUtil getInstance(final String caminhoArquivo) throws IOException {
    if (PropertiesUtil.propertiesUtil == null || PropertiesUtil.propertiesUtil != null && !PropertiesUtil.propertiesUtil.pathArquivo.equals(caminhoArquivo)) {
      PropertiesUtil.propertiesUtil = new PropertiesUtil(caminhoArquivo);
    }

    return PropertiesUtil.propertiesUtil;
  }

  private Properties properties;

  private String pathArquivo;

  private PropertiesUtil(final String pathArquivo) throws IOException {
    this.properties = new Properties();
    this.pathArquivo = pathArquivo;
    this.properties.load(this.getClass().getResourceAsStream(this.pathArquivo));
  }

  public synchronized String getProperty(final String chave) throws MissingResourceException {
    final String retorno = this.properties.getProperty(chave);

    if (retorno == null) {
      String chavesExistentes = "";
      final Enumeration enumeration = this.properties.keys();

      while (enumeration.hasMoreElements()) {
        final Object obj = enumeration.nextElement();
        if (obj != null) {
          chavesExistentes = chavesExistentes.concat(obj.toString() + " => " + this.properties.getProperty(obj.toString()) + "\n");
        }
      }

      throw new MissingResourceException(" ** Chave " + chave + " não localizada em " + this.pathArquivo
          + "! **\n==================\nChaves existentes:\n==================\n" + chavesExistentes, this.properties.getClass().getName(), chave);
    }

    return retorno;
  }

  public String getProperty(final String chave, final String value) {
    return this.properties.getProperty(chave, value);
  }

  public void setProperty(final String chave, final String value) {
    this.properties.setProperty(chave, value);
  }
}
