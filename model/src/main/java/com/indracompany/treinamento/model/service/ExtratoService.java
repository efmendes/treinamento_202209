package com.indracompany.treinamento.model.service;

import com.indracompany.treinamento.exception.AplicacaoException;
import com.indracompany.treinamento.exception.ExceptionValidacoes;
import com.indracompany.treinamento.model.entity.Extrato;
import com.indracompany.treinamento.model.repository.ExtratoRepository;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;



@Service
public class ExtratoService extends GenericCrudService<Extrato,Long, ExtratoRepository>{

    @Autowired
    private  ExtratoRepository extratorepository;

    public List<Extrato> pesquisarExtrato(String numero, String agencia){
        if(!StringUtils.isBlank(numero) || !StringUtils.isBlank(agencia) ){
            List<Extrato> extratos = extratorepository.findByExtrato(numero, agencia);
            if(extratos.isEmpty()){
                throw new AplicacaoException(ExceptionValidacoes.ALERTA_NENHUM_REGISTRO_ENCONTRADO);
            }
            return extratos;
        }else{
            throw  new AplicacaoException(ExceptionValidacoes.ERRO_CONTA_INVALIDA);
        }

    }

   

    public List<Extrato> pesquisarExtratoPorIntervaloDeData(String numero, String agencia, String dataInicio, String dataFim){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate dataFormatadaInicio = LocalDate.parse(dataInicio, formatter);
        LocalDate dataFormatadaFim = LocalDate.parse(dataFim, formatter);
        if(!StringUtils.isBlank(numero) || !StringUtils.isBlank(agencia) ||!StringUtils.isBlank(dataFim) || !StringUtils.isBlank(dataInicio)){
            if(dataFormatadaFim.isBefore(dataFormatadaInicio) || dataFormatadaInicio.isAfter(dataFormatadaFim)){
                throw new AplicacaoException(ExceptionValidacoes.ERRO_INTERVALO_DATA_INVALIDA);
            }
            List<Extrato> extratoPorIntervalo = extratorepository.findByExtratoPorIntervalo(numero,agencia,dataFormatadaInicio,dataFormatadaFim);
            if(extratoPorIntervalo.isEmpty()){
                throw new AplicacaoException(ExceptionValidacoes.ALERTA_NENHUM_REGISTRO_ENCONTRADO);
            }
            return extratoPorIntervalo;
        }else{
            throw  new AplicacaoException(ExceptionValidacoes.ERRO_DATA_INVALIDA);
        }

    }


}