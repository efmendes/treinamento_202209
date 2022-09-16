package com.indracompany.treinamento.model.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.indracompany.treinamento.exception.AplicacaoException;
import com.indracompany.treinamento.exception.ExceptionValidacoes;
import com.indracompany.treinamento.model.dto.ClienteDTO;
import com.indracompany.treinamento.model.dto.ContaClienteDTO;
import com.indracompany.treinamento.model.dto.OperacaoContaDTO;
import com.indracompany.treinamento.model.entity.Cliente;
import com.indracompany.treinamento.model.entity.ContaBancaria;
import com.indracompany.treinamento.model.entity.OperacaoConta;
import com.indracompany.treinamento.model.repository.OperacaoContaRepository;

@Service
public class OperacaoContaService extends GenericCrudService<OperacaoConta, Long, OperacaoContaRepository> {

	@Autowired
	private OperacaoContaRepository operacaoContaRepository;

	public List<OperacaoContaDTO> listaExtratoDoCliente(Date dataInicio, Date dataFim, String agencia, String numero) {
		List<OperacaoConta> extrato = operacaoContaRepository
				.findByDataTransacaoBetweenAndContaBancariaAgenciaAndContaBancariaNumero(dataInicio, dataFim, agencia,
						numero)
				.orElseThrow(() -> new AplicacaoException(ExceptionValidacoes.ALERTA_NENHUM_REGISTRO_ENCONTRADO));
		List<OperacaoContaDTO> listaDTO = new ArrayList<>();
		for (OperacaoConta operacaoConta : extrato) {
			OperacaoContaDTO operacaoContaDTO = new OperacaoContaDTO();
			BeanUtils.copyProperties(operacaoConta, operacaoContaDTO);
			String data = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(operacaoConta.getDataTransacao());
			operacaoContaDTO.setDataTransacao(data);
			listaDTO.add(operacaoContaDTO);

		}

		return listaDTO;

	}

	public List<OperacaoContaDTO> listaTodosExtratos(Date dataInicio, Date dataFim) {
		List<OperacaoConta> extrato = operacaoContaRepository.findByDatas(dataInicio, dataFim)
				.orElseThrow(() -> new AplicacaoException(ExceptionValidacoes.ALERTA_NENHUM_REGISTRO_ENCONTRADO));
		List<OperacaoContaDTO> listaDTO = new ArrayList<>();

		for (OperacaoConta operacaoConta : extrato) {
			OperacaoContaDTO operacaoContaDTO = new OperacaoContaDTO();
			BeanUtils.copyProperties(operacaoConta, operacaoContaDTO);
			String data = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(operacaoConta.getDataTransacao());
			operacaoContaDTO.setDataTransacao(data);
			listaDTO.add(operacaoContaDTO);

		}

		return listaDTO;

	}

}
