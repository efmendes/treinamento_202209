package com.indracompany.treinamento.model.service;

import com.indracompany.treinamento.exception.AplicacaoException;
import com.indracompany.treinamento.exception.ExceptionValidacoes;
import com.indracompany.treinamento.model.dto.ExtratoDTO;
import com.indracompany.treinamento.model.entity.ContaBancaria;
import com.indracompany.treinamento.model.entity.Extrato;
import com.indracompany.treinamento.model.repository.ContaBancariaRepository;
import com.indracompany.treinamento.model.repository.ExtratoRepositoy;
import com.indracompany.treinamento.util.CpfUtil;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


@Service
@RequiredArgsConstructor
public class ExtratoService extends GenericCrudService<Extrato, Long, ExtratoRepositoy> {

    public final ExtratoRepositoy extratoRepositoy;
    public final ContaBancariaRepository contaRepository;

    private final static DateFormat DF = new SimpleDateFormat ("ddMMyyyy");

    public List<ExtratoDTO> findAllByCpf(String cpf) {

        boolean cpfValido = CpfUtil.validaCPF(cpf);
        if (!cpfValido) {
            throw new AplicacaoException(ExceptionValidacoes.ERRO_CPF_INVALIDO, cpf);
        }

        List<ContaBancaria> contaBancaria = contaRepository.findByClienteCpf(cpf)
                .orElseThrow(() -> new AplicacaoException(ExceptionValidacoes.ALERTA_NENHUM_REGISTRO_ENCONTRADO, cpf));


        List<ExtratoDTO> extratoDTOList = new ArrayList<>();
        for (ContaBancaria conta : contaBancaria) {

            Optional<List<Extrato>> extratoOptional = extratoRepositoy.findByConta(conta);

            if (extratoOptional.isPresent()) {
                for (Extrato extrato : extratoOptional.get()) {

                    ExtratoDTO dto = new ExtratoDTO();
                    String date = new SimpleDateFormat("dd/MM/yyyy").format(extrato.getDate());
                    String hour = new SimpleDateFormat("HH:mm:ss").format(extrato.getDate());

                    dto.setDate(date);
                    dto.setHour(hour);
                    dto.setAgencia(conta.getAgencia());
                    dto.setConta(conta.getNumero());
                    dto.setDescription(extrato.getDescription());

                    extratoDTOList.add(dto);
                }
            }
        }

        return extratoDTOList;
    }


    public List<ExtratoDTO> findByData(String cpf, String data) {

        boolean cpfValido = CpfUtil.validaCPF(cpf);
        if (!cpfValido) {
            throw new AplicacaoException(ExceptionValidacoes.ERRO_CPF_INVALIDO, cpf);
        }

        if(StringUtils.isBlank(data) || !StringUtils.isNumeric(data)){
            throw new AplicacaoException(ExceptionValidacoes.ERRO_VALIDACAO, data);
        }

        List<ExtratoDTO> extratoDTOList = this.findAllByCpf(cpf);

        try {
            Date dateFormatted = DF.parse(data);
            String date = DF.format(dateFormatted);

            extratoDTOList.removeIf(extrato ->
                    !Objects.equals(extrato.getDate().replace("/", ""), date));


        } catch (ParseException e) {
            throw new AplicacaoException(ExceptionValidacoes.ERRO_VALIDACAO, data);
        }

        return extratoDTOList;

    }


    public List<ExtratoDTO> findByIntervalo(String cpf, String dataIni, String dataFim){

        boolean cpfValido = CpfUtil.validaCPF(cpf);
        if (!cpfValido) {
            throw new AplicacaoException(ExceptionValidacoes.ERRO_CPF_INVALIDO, cpf);
        }

        if(StringUtils.isBlank(dataIni) || !StringUtils.isNumeric(dataIni)){
            throw new AplicacaoException(ExceptionValidacoes.ERRO_VALIDACAO, dataIni);
        }
        if(StringUtils.isBlank(dataFim) || !StringUtils.isNumeric(dataFim)){
            throw new AplicacaoException(ExceptionValidacoes.ERRO_VALIDACAO, dataFim);
        }

        List<ExtratoDTO> extratoDTOList = this.findAllByCpf(cpf);
        List<ExtratoDTO> extratoDTOReturn = new ArrayList<>();

        try {
            Date dateInicial = DF.parse(dataIni);
            Date dateFinal = DF.parse(dataFim);
            String dateIni = DF.format(dateInicial);
            String dateFim = DF.format(dateFinal);

            for(ExtratoDTO extrato: extratoDTOList){
                Date dataExtrato = DF.parse(extrato.getDate().replace("/", ""));
                Date dtIni = DF.parse (dateIni);
                Date dtFim = DF.parse (dateFim);
                Calendar cal = Calendar.getInstance();
                cal.setTime(dtIni);

                for (Date dt = dtIni; dt.compareTo (dtFim) <= 0;){
                    if(dataExtrato.equals(dt)){
                        extratoDTOReturn.add(extrato);
                    }

                    cal.add (Calendar.DATE, +1);
                    dt = cal.getTime();
                }

                if(!dtIni.before(dtFim)){
                    throw new AplicacaoException(ExceptionValidacoes.ERRO_VALIDACAO, "Data final anterior ou igual a inicial!");
                }

            }

        } catch (ParseException e) {
            throw new AplicacaoException(ExceptionValidacoes.ERRO_VALIDACAO, e);
        }

        return extratoDTOReturn;

    }



}