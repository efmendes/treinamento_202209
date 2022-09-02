package com.indracompany.treinamento.util;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.introspect.VisibilityChecker;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.indracompany.treinamento.exception.AplicacaoException;
import com.indracompany.treinamento.exception.ExceptionValidacoes;

public class JSONUtil {

  public static <T> T convertJsonStringToObject(final String json, final Class<T> object) throws AplicacaoException {

    try {
      final ObjectMapper mapper = new ObjectMapper();
      mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
      mapper.setVisibilityChecker(VisibilityChecker.Std.defaultInstance().withFieldVisibility(JsonAutoDetect.Visibility.ANY));
      return mapper.readValue(json, object);
    } catch (final Exception e) {
      throw new AplicacaoException(ExceptionValidacoes.ERRO_SERIALIZAR_JSON, e);
    }
  }

  public static <T> T convertJsonStringToObject(final String json, final TypeReference<T> typeReference) throws AplicacaoException {

    try {
      final ObjectMapper mapper = new ObjectMapper();
      mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
      mapper.setVisibilityChecker(VisibilityChecker.Std.defaultInstance().withFieldVisibility(JsonAutoDetect.Visibility.ANY));
      return mapper.readValue(json, typeReference);
    } catch (final Exception e) {
      throw new AplicacaoException(ExceptionValidacoes.ERRO_SERIALIZAR_JSON, e);
    }
  }

  public static <T> T convertJsonToObject(final byte[] src, final Class<T> valueType) throws AplicacaoException {

    try {
      final ObjectMapper mapper = new ObjectMapper();
      mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
      mapper.setVisibilityChecker(VisibilityChecker.Std.defaultInstance().withFieldVisibility(JsonAutoDetect.Visibility.ANY));
      return mapper.readValue(src, valueType);
    } catch (final Exception e) {
      throw new AplicacaoException(ExceptionValidacoes.ERRO_SERIALIZAR_JSON, e);
    }
  }

  public static String convertObjectToJsonString(final Object object) throws AplicacaoException {

    try {
      final ObjectMapper mapper = new ObjectMapper();
      mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
      mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
      mapper.setVisibilityChecker(VisibilityChecker.Std.defaultInstance().withFieldVisibility(JsonAutoDetect.Visibility.ANY));
      return mapper.writeValueAsString(object);
    } catch (final Exception e) {
      throw new AplicacaoException(ExceptionValidacoes.ERRO_SERIALIZAR_JSON, e);
    }
  }

  public static String convertObjectToJsonStringWithIgnore(final Object object, final String... propertiesIgnore) throws AplicacaoException {

    try {

      final ObjectMapper mapper = new ObjectMapper();
      mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
      mapper.setVisibilityChecker(VisibilityChecker.Std.defaultInstance().withFieldVisibility(JsonAutoDetect.Visibility.ANY));
      final FilterProvider filters =
          new SimpleFilterProvider().addFilter("filter properties by name", SimpleBeanPropertyFilter.serializeAllExcept(propertiesIgnore));

      final ObjectWriter writer = mapper.writer(filters);

      return writer.writeValueAsString(object);
    } catch (final Exception e) {
      throw new AplicacaoException(ExceptionValidacoes.ERRO_SERIALIZAR_JSON, e);
    }
  }

}
