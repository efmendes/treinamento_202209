package com.indracompany.treinamento.model.service;

import com.indracompany.treinamento.exception.AplicacaoException;
import com.indracompany.treinamento.exception.ExceptionValidacoes;
import com.indracompany.treinamento.model.dto.ExtratoDTO;
import com.indracompany.treinamento.model.entity.ContaBancaria;
import com.indracompany.treinamento.model.entity.Extrato;
import com.indracompany.treinamento.model.repository.ExtratoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service
public class ExtratoService extends GenericCrudService<Extrato, Long, ExtratoRepository>{

    @Autowired
    private ExtratoRepository extratoRepository;

    @Autowired
    private ContaBancariaService contaBancariaService;

    public void gerarExtrato(String numeroAgencia, String numeroConta, String tipoOperacao, double valor) {

        ContaBancaria conta = contaBancariaService.carregarConta(numeroAgencia, numeroConta);

        Extrato extrato = new Extrato();

        LocalDateTime dataOperacao = LocalDateTime.now();

        extrato.setContaBancaria(conta);
        extrato.setTipoOperacao(tipoOperacao);
        extrato.setValor(valor);
        extrato.setDataOperacao(dataOperacao);

        extratoRepository.save(extrato);

    }

    public List<ExtratoDTO> buscarExtratoPeriodo(String agenciaNumero, String contaNumero, String dataInicio, String dataFim) {

        ContaBancaria contaBancaria = contaBancariaService.carregarConta(agenciaNumero, contaNumero);

        List<Extrato> movimentacoesConta = extratoRepository.findByContaBancaria(contaBancaria);

        DateTimeFormatter formatoData = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss", Locale.forLanguageTag("pt-BR"));

        LocalDateTime dataInicial = LocalDateTime.parse(dataInicio + " 00:00:00", formatoData);
        LocalDateTime dataFinal = LocalDateTime.parse(dataFim + " 23:59:59", formatoData);

        if (movimentacoesConta  == null) {
            throw new AplicacaoException(ExceptionValidacoes.ALERTA_NENHUM_REGISTRO_ENCONTRADO);
        }

        List<ExtratoDTO> operacoesNoPeriodo = new ArrayList<>();

        for(Extrato extrato: movimentacoesConta ) {
            if(extrato.getDataOperacao().isAfter(dataInicial) && extrato.getDataOperacao().isBefore(dataFinal)) {
                ExtratoDTO extratoDTO = new ExtratoDTO();
                BeanUtils.copyProperties(extrato, extratoDTO);
                operacoesNoPeriodo.add(extratoDTO);
            }
        }

        return operacoesNoPeriodo;

    }
}
