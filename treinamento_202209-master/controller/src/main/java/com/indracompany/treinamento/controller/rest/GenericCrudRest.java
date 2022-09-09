package com.indracompany.treinamento.controller.rest;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.indracompany.treinamento.exception.AplicacaoException;
import com.indracompany.treinamento.model.entity.GenericEntity;
import com.indracompany.treinamento.model.service.GenericCrudService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * @author efmendes
 *
 * @param <T>
 * @param <S>
 * @param <I>
 */
@Slf4j
@RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public abstract class GenericCrudRest<T extends GenericEntity<I>, I, S extends GenericCrudService<T, I, ?>> {

  private static final long serialVersionUID = -3853594377194808570L;

  @Autowired
  protected GenericCrudService<T, I, ?> service;

  @Getter
  @Setter
  private transient T entity;

  @Getter
  @Setter
  private transient List<T> list;

  @ApiOperation(value = "Atualiza uma entidade existente.", nickname = "alterar", notes = "")
  @ApiResponses(value = {@ApiResponse(code = 400, message = "ID inválido"), @ApiResponse(code = 404, message = "Entidade não encontrada"),
      @ApiResponse(code = 405, message = "Validation exception")})
  @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
  public @ResponseBody ResponseEntity<T> alterar(@ApiParam(value = "Objeto entida a ser atualizada.", required = true) @Valid final @RequestBody T entity,
      final @PathVariable I id) throws AplicacaoException {

    if (GenericCrudRest.log.isDebugEnabled()) {

      GenericCrudRest.log.debug("Realizando a chamada do controller: " + this.getClass().getName() + ".alterar( " + entity.getClass().getName()
          + " , " + id + " ). Realizando a chamada do service: " + this.service.getClass().getName() + ".salvar( " + entity.getClass().getName() + " )");
    }

    if (((Comparable<I>) id).compareTo(entity.getId()) != 0) {

      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    this.service.salvar(entity);

    if (GenericCrudRest.log.isDebugEnabled()) {

      GenericCrudRest.log
          .debug("Chamada do controller: " + this.getClass().getName() + ".alterar( " + entity.getClass().getName() + " ) realizada com sucesso.");
    }

    return new ResponseEntity<>(entity, HttpStatus.OK);
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = {"application/json"})
  public @ResponseBody ResponseEntity<T> buscar(final @PathVariable I id) throws AplicacaoException {

	 	  
    if (GenericCrudRest.log.isDebugEnabled()) {

      GenericCrudRest.log.debug("Realizando a chamada do controller: " + this.getClass().getName() + ".buscar( " + id
          + " ). Realizando a chamada do service: " + this.service.getClass().getName() + ".obterUm( " + id + " )");
    }

    final T entity = this.service.buscar(id);

    if (GenericCrudRest.log.isDebugEnabled()) {

      GenericCrudRest.log.debug("Chamada do controller: " + this.getClass().getName() + ".buscar( " + id + " ) realizada com sucesso.");
    }

    return entity == null ? new ResponseEntity<>(HttpStatus.NOT_FOUND) : new ResponseEntity<>(entity, HttpStatus.OK);
  }


  @RequestMapping(value = "/", method = RequestMethod.GET)
  public @ResponseBody ResponseEntity<List<T>> listar() throws AplicacaoException {

	  
	  GenericCrudRest.log.debug("Realizando a chamada do controller: " + this.getClass().getName() + ".listar(). Realizando a chamada do service: "
	          + this.service.getClass().getName() + ".obterTodos()");
    if (GenericCrudRest.log.isDebugEnabled()) {

      GenericCrudRest.log.debug("Realizando a chamada do controller: " + this.getClass().getName() + ".listar(). Realizando a chamada do service: "
          + this.service.getClass().getName() + ".obterTodos()");
    }

    final List<T> result = this.service.listar();

    if (GenericCrudRest.log.isDebugEnabled()) {

      GenericCrudRest.log.debug("Chamada do controller: " + this.getClass().getName() + ".listar() realizada com sucesso.");
    }


    return new ResponseEntity<>(result, HttpStatus.OK);
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
  public ResponseEntity<Void> remover(final @PathVariable I id) throws AplicacaoException {

    if (GenericCrudRest.log.isDebugEnabled()) {

      GenericCrudRest.log.debug("Realizando a chamada do controller: " + this.getClass().getName() + ".remover( " + id
          + " ). Realizando a chamada do service: " + this.service.getClass().getName() + ".remover( " + id + " )");
    }

    this.service.remover(id);

    if (GenericCrudRest.log.isDebugEnabled()) {

      GenericCrudRest.log.debug("Chamada do controller: " + this.getClass().getName() + ".remover( " + id + " ) realizada com sucesso.");
    }

    return ResponseEntity.ok().build();
  }

  @SuppressWarnings("unchecked")
  @PostConstruct
  public void resetar() {
    try {
      this.entity = ((Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0]).newInstance();
      this.list = this.service.listar();
    } catch (final Exception e) {
      GenericCrudRest.log.error("erro ao resetar() " + this.getClass().getName(), e);
    }
  }

  @RequestMapping(value = "/", method = RequestMethod.POST)
  public @ResponseBody ResponseEntity<T> salvar(@ApiParam(value = "Objeto entidade a ser cadastrada.", required = true) @Valid final @RequestBody T entity)
      throws AplicacaoException {

    if (GenericCrudRest.log.isDebugEnabled()) {

      GenericCrudRest.log.debug("Realizando a chamada do controller: " + this.getClass().getName() + ".salvar( " + entity.getClass().getName()
          + " ). Realizando a chamada do service: " + this.service.getClass().getName() + ".salvar( " + entity.getClass().getName() + " )");
    }

    final T newEntity = this.service.salvar(entity);

    if (GenericCrudRest.log.isDebugEnabled()) {

      GenericCrudRest.log
          .debug("Chamada do controller: " + this.getClass().getName() + ".salvar( " + entity.getClass().getName() + " ) realizada com sucesso.");
    }

    return new ResponseEntity<>(newEntity, HttpStatus.OK);
  }


  public S getService() 
  {
	  return (S) this.service;
  }
}
