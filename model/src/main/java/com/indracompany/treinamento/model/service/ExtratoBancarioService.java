package com.indracompany.treinamento.model.service;

import com.indracompany.treinamento.exception.AplicacaoException;
import com.indracompany.treinamento.exception.ExceptionValidacoes;
import com.indracompany.treinamento.model.entity.ExtratoBancario;
import com.indracompany.treinamento.model.repository.ExtratoBancarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ExtratoBancarioService extends GenericCrudService<ExtratoBancario,Long, ExtratoBancarioRepository>{

    @Autowired
    private  ExtratoBancarioRepository pesquisarExtrato;

    public List<ExtratoBancario> pesquisarExtratoBancario(String numero, String agencia){
        List<ExtratoBancario> extratos = pesquisarExtrato.findByExtrato(numero, agencia);

        if(extratos.isEmpty()){
            throw new AplicacaoException(ExceptionValidacoes.ALERTA_NENHUM_REGISTRO_ENCONTRADO);
        }
        return extratos;
    }


}
