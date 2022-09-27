package com.indracompany.treinamento.model.service;

import com.indracompany.treinamento.exception.AplicacaoException;
import com.indracompany.treinamento.exception.ExceptionValidacoes;
import com.indracompany.treinamento.model.dto.ExtratoBancarioDTO;
import com.indracompany.treinamento.model.entity.ExtratoBancario;
import com.indracompany.treinamento.model.repository.ExtratoBancarioRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;



@Service
public class ExtratoBancarioService extends GenericCrudService<ExtratoBancario,Long, ExtratoBancarioRepository>{

    @Autowired
    private  ExtratoBancarioRepository pesquisarExtrato;

    public List<ExtratoBancarioDTO> pesquisarExtratoBancario(String numero, String agencia){
        if(!StringUtils.isBlank(numero) || !StringUtils.isBlank(agencia) ){
            List<ExtratoBancario> extratos = pesquisarExtrato.findByExtrato(numero, agencia);

            if(extratos.isEmpty()){
                throw new AplicacaoException(ExceptionValidacoes.ALERTA_NENHUM_REGISTRO_ENCONTRADO);
            }

            List<ExtratoBancarioDTO> extratoRetornoDTO = new ArrayList<>();

            for(ExtratoBancario ext : extratos){
                ExtratoBancarioDTO dto = new ExtratoBancarioDTO();
                BeanUtils.copyProperties(ext,dto);
                dto.setAgencia(ext.getConta().getAgencia());
                dto.setNumeroConta(ext.getConta().getNumero());
                extratoRetornoDTO.add(dto);

            }
            return extratoRetornoDTO;
        }else{
            throw  new AplicacaoException(ExceptionValidacoes.ERRO_CONTA_INVALIDA);
        }

    }

    public List<ExtratoBancarioDTO> pesquisarExtratoPorData(String numero, String agencia, String data){

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate dataFormatada = LocalDate.parse(data, formatter);
        if(!StringUtils.isBlank(numero) || !StringUtils.isBlank(agencia)){

            List<ExtratoBancario> extratoPorData = pesquisarExtrato.findByExtratoPorDataEspecifica(numero,agencia,dataFormatada);

            if(extratoPorData.isEmpty()){
                throw new AplicacaoException(ExceptionValidacoes.ALERTA_NENHUM_REGISTRO_ENCONTRADO);
            }
            List<ExtratoBancarioDTO> extratoRetornoDTO = new ArrayList<>();

            for(ExtratoBancario ext : extratoPorData){
                ExtratoBancarioDTO dto = new ExtratoBancarioDTO();
                BeanUtils.copyProperties(ext,dto);
                dto.setAgencia(ext.getConta().getAgencia());
                dto.setNumeroConta(ext.getConta().getNumero());
                extratoRetornoDTO.add(dto);

            }
            return extratoRetornoDTO;
        }else{
            throw  new AplicacaoException(ExceptionValidacoes.ERRO_DATA_INVALIDA);
        }
    }

    public List<ExtratoBancarioDTO> pesquisarExtratoPorIntervaloDeData(String numero, String agencia, String dataInicio, String dataFim){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate dataFormatadaInicio = LocalDate.parse(dataInicio, formatter);
        LocalDate dataFormatadaFim = LocalDate.parse(dataFim, formatter);

        if(!StringUtils.isBlank(numero) || !StringUtils.isBlank(agencia) ||!StringUtils.isBlank(dataFim) || !StringUtils.isBlank(dataInicio)){
            if(dataFormatadaFim.isBefore(dataFormatadaInicio) || dataFormatadaInicio.isAfter(dataFormatadaFim)){
                throw new AplicacaoException(ExceptionValidacoes.ERRO_INTERVALO_DATA_INVALIDA);
            }

            List<ExtratoBancario> extratoPorIntervalo = pesquisarExtrato.findByExtratoPorIntervalo(numero,agencia,dataFormatadaInicio,dataFormatadaFim);

            if(extratoPorIntervalo.isEmpty()){
                throw new AplicacaoException(ExceptionValidacoes.ALERTA_NENHUM_REGISTRO_ENCONTRADO);
            }

            List<ExtratoBancarioDTO> extratoRetornoDTO = new ArrayList<>();

            for(ExtratoBancario ext : extratoPorIntervalo){
                ExtratoBancarioDTO dto = new ExtratoBancarioDTO();
                BeanUtils.copyProperties(ext,dto);
                System.out.println(dto);
                dto.setAgencia(ext.getConta().getAgencia());
                dto.setNumeroConta(ext.getConta().getNumero());
                extratoRetornoDTO.add(dto);

            }
            return extratoRetornoDTO;

        }else{
            throw  new AplicacaoException(ExceptionValidacoes.ERRO_DATA_INVALIDA);
        }

    }


}
