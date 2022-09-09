package com.indracompany.treinamento.model.service;

import java.lang.reflect.Field;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.validation.ConstraintViolation;

import org.apache.commons.lang3.StringUtils;
import org.glassfish.jersey.internal.guava.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.indracompany.treinamento.exception.AplicacaoException;
import com.indracompany.treinamento.exception.AplicacaoExceptionValue;
import com.indracompany.treinamento.exception.ExceptionValidacoes;
import com.indracompany.treinamento.exception.ValidacaoCampos;
import com.indracompany.treinamento.model.entity.GenericEntity;
import com.indracompany.treinamento.model.repository.GenericCrudRepository;

import lombok.extern.slf4j.Slf4j;


/**
 * @author efmendes
 *
 * @param <T>
 */
@Slf4j
public abstract class GenericCrudService<T extends GenericEntity<I>, I, R extends GenericCrudRepository<T, I>> {


  @Autowired
  protected R repository;

  private void ajustar(final T entidade) {
    if (entidade == null) {
      return;
    }

    try {
      final Field[] campos = entidade.getClass().getDeclaredFields();

      for (final Field f : campos) {
        f.setAccessible(true);
        final Object object = f.get(entidade);
        if (object instanceof CharSequence) {
          object.toString().trim();
        }
      }

    } catch (final Exception e) {
      GenericCrudService.log.error(e.getMessage(), e);
    }
  }

  public T buscar(final I id) throws AplicacaoException {
    final Optional<T> retorno = this.repository.findById(id);
    if (retorno != null && retorno.isPresent()) {
      return retorno.get();
    }
    return null;
  }

  public Long contar() {
    return this.repository.count();
  }

  public boolean existe(final I id) {
    return !Objects.isNull(id) && this.repository.existsById(id);
  }

  public List<T> listar() {
    return Lists.newArrayList(this.repository.findAll());
  }

  @Transactional(rollbackFor = Throwable.class)
  public void remover(final I id) throws AplicacaoException {
    try {
      this.repository.deleteById(id);
    } catch (final Exception e) {
      throw new AplicacaoException(ExceptionValidacoes.ERRO_EXCLUSAO_GENERICO, e);
    }
  }

  @Transactional(rollbackFor = Throwable.class)
  public void remover(final T entidade) throws AplicacaoException {
    try {
      this.repository.delete(entidade);
    } catch (final Exception e) {
      throw new AplicacaoException(ExceptionValidacoes.ERRO_EXCLUSAO_GENERICO, e);
    }
  }

  @Transactional(rollbackFor = Throwable.class)
  public void removerTodos() {
    this.repository.deleteAll();
  }

  @Transactional(rollbackFor = Throwable.class)
  public T salvar(T entidade) throws AplicacaoException {
    entidade.setPersisted(this.existe(entidade.getId()));
    this.ajustar(entidade);
    this.validar(entidade);
    entidade = this.repository.saveAndFlush(entidade);
    return entidade;

  }


  private void validar(final T entidade) {
    final List<AplicacaoExceptionValue> customExceptionValues = new LinkedList<>();

    for (final ConstraintViolation<GenericEntity<I>> error : entidade.validationsConstraintsFails()) {

      final AplicacaoExceptionValue customExceptionValue =
          new AplicacaoExceptionValue(ValidacaoCampos.newInstance(StringUtils.substringBetween(error.getMessageTemplate(), "{", "}"), error.getMessage()), true,
              error.getPropertyPath().toString(), error.getInvalidValue() != null ? error.getInvalidValue().toString() : null);

      customExceptionValues.add(customExceptionValue);
    }

    if (!customExceptionValues.isEmpty()) {
      throw new AplicacaoException(ExceptionValidacoes.ERRO_VALIDACAO, customExceptionValues);
    }

  }
  
  public R getRepository() {
      return repository;
  }



}
