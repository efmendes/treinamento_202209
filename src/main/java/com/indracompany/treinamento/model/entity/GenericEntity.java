package com.indracompany.treinamento.model.entity;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Objects;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.extern.slf4j.Slf4j;


/**
 * @author efmendes
 *
 */
@Slf4j
public class GenericEntity<I> implements Persistable<I>, Serializable {


  /**
   *
   */
  private static final long serialVersionUID = 8197809300936646012L;

  /**
   * @author efmendes Obtem o valor do atributo mapeado com a anotação @Id ou @EmbeddedId
   * @param entidade
   * @param fields
   * @return Field
   */
  private static Boolean checkIdField(final Field field) {
    return !Objects.isNull(field.getAnnotation(Id.class));
  }

  @Transient
  private Boolean persisted = Boolean.TRUE;


  @Override
  public boolean equals(final Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (!(obj instanceof GenericEntity)) {
      return false;
    }
    final GenericEntity<?> other = (GenericEntity<?>) obj;
    if (other.getId() == null || Number.class.isInstance(other.getId()) && ((Number) other.getId()).longValue() == 0
        || String.class.isInstance(other.getId()) && ((String) other.getId()).isEmpty()) {
      return false;
    } else if (this.getId() == null) {
      if (other.getId() != null) {
        return false;
      }
    } else if (!this.getId().equals(other.getId())) {
      return false;
    }
    return true;
  }


  /**
   * Objetivo: Dada uma entidade, obter o valor contido no atributo marcado como ID (@Id).
   *
   * Funcionamento: Através reflection obtém a entidade marcada com a annotation e o seu respectivo
   * valor
   *
   * @param entidade
   * @return Serializable
   */
  @SuppressWarnings("unchecked")
  @Override
  public I getId() {
    I retorno = null;
    try {
      retorno = (I) this.getIdField().get(Long.class);
    } catch (final Exception e) {
      GenericEntity.log.error("erro ao obter getId da entidade ", e);
    }
    return retorno;
  }

  @JsonIgnore
  public Field getIdField() {
    Field retorno = null;
    Class<?> actualClass = this.getClass();

    try {
      do {

        for (final Field fieldSequenceId : actualClass.getDeclaredFields()) {
          fieldSequenceId.setAccessible(true);
          if (checkIdField(fieldSequenceId)) {
            retorno = fieldSequenceId;
            actualClass = actualClass.getSuperclass();
          }
        }
      } while (Objects.isNull(retorno) && !Object.class.equals(actualClass));
    } catch (final Exception e) {
      GenericEntity.log.error("erro ao obter getIdField da entidade ", e);
    }
    return retorno;
  }

  @JsonIgnore
  public Boolean getPersisted() {
    return this.persisted;
  }


  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + (this.getId() == null ? 0 : this.getId().hashCode());
    return result;
  }

  @JsonIgnore
  @Override
  public boolean isNew() {
    return !this.persisted;
  }

  public void setPersisted(final Boolean persisted) {
    this.persisted = persisted;
  }


  public Set<ConstraintViolation<GenericEntity<I>>> validationsConstraintsFails() {
    final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    final Validator validator = factory.getValidator();
    return validator.validate(this);
  }


}
