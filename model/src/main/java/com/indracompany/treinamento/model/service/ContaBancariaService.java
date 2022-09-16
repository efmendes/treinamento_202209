package com.indracompany.treinamento.model.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.indracompany.treinamento.exception.AplicacaoException;
import com.indracompany.treinamento.exception.ExceptionValidacoes;
import com.indracompany.treinamento.model.dto.ConsultaExtratoDTO;
import com.indracompany.treinamento.model.dto.ContaClienteDTO;
import com.indracompany.treinamento.model.dto.DepositoDTO;
import com.indracompany.treinamento.model.dto.ExtratoDTO;
import com.indracompany.treinamento.model.dto.SaqueDTO;
import com.indracompany.treinamento.model.dto.TransferenciaBancariaDTO;
import com.indracompany.treinamento.model.entity.ContaBancaria;
import com.indracompany.treinamento.model.entity.Transacao;
import com.indracompany.treinamento.model.repository.ContaBancariaRepository;
import com.indracompany.treinamento.util.CpfUtil;

@Service
public class ContaBancariaService extends GenericCrudService<ContaBancaria, Long, ContaBancariaRepository>{
	
	@Autowired
	private ClienteService clienteService;
	
	@Autowired
	private ContaBancariaRepository contaBancariaRepository;
	
	public List<ContaClienteDTO> listarContasDoCliente(String cpf){
		
		boolean cpfValido = CpfUtil.validaCPF(cpf);
		if (!cpfValido) {
			throw new AplicacaoException(ExceptionValidacoes.ERRO_CPF_INVALIDO, cpf);
		}
		
		List<ContaBancaria> contasBancarias = contaBancariaRepository.findByClienteCpf(cpf);
		
		if (contasBancarias== null || contasBancarias.isEmpty()) {
			throw new AplicacaoException(ExceptionValidacoes.ALERTA_NENHUM_REGISTRO_ENCONTRADO);
		}
		
		List<ContaClienteDTO> listaRetornoDTO = new ArrayList<>();
		
		for (ContaBancaria conta : contasBancarias) {
			ContaClienteDTO dto = new ContaClienteDTO();
			BeanUtils.copyProperties(conta, dto);
			dto.setNomeCliente(conta.getCliente().getNome());
			listaRetornoDTO.add(dto);
		}
		
		return listaRetornoDTO;
	}
	
	public void depositar(DepositoDTO dto) {
		ContaBancaria contaBancaria = this.carregarConta(dto.getAgencia(), dto.getNumeroConta());
		contaBancaria.setSaldo(contaBancaria.getSaldo() + dto.getValor());
		Transacao transacao = new Transacao("Deposito", dto.getValor(), new Date(), contaBancaria.getId());
		contaBancaria.getTransacoes().add(transacao);
		super.salvar(contaBancaria);
	}
	
	public void sacar(SaqueDTO dto) {
		ContaBancaria contaBancaria = this.carregarConta(dto.getAgencia(), dto.getNumeroConta());
		if (contaBancaria.getSaldo() < dto.getValor()) {
			throw new AplicacaoException(ExceptionValidacoes.ERRO_SALDO_INEXISTENTE);
		}
		contaBancaria.setSaldo(contaBancaria.getSaldo() - dto.getValor());
		Transacao transacao = new Transacao("Saque", dto.getValor(), new Date(), contaBancaria.getId());
		contaBancaria.getTransacoes().add(transacao);
		super.salvar(contaBancaria);
	}
	
	@Transactional(rollbackFor = Exception.class)
	public void transferir(TransferenciaBancariaDTO transferenciaDto) {
		
		SaqueDTO saqueDto = new SaqueDTO();
		saqueDto.setAgencia(transferenciaDto.getAgenciaOrigem());
		saqueDto.setNumeroConta(transferenciaDto.getNumeroContaOrigem());
		saqueDto.setValor(transferenciaDto.getValor());

		this.sacar(saqueDto);
		
		DepositoDTO depositoDto = new DepositoDTO();
		depositoDto.setAgencia(transferenciaDto.getAgenciaDestino());
		depositoDto.setNumeroConta(transferenciaDto.getNumeroContaDestino());
		depositoDto.setValor(transferenciaDto.getValor());
		
		this.depositar(depositoDto);
		ContaBancaria contaOrigem = carregarConta(transferenciaDto.getAgenciaOrigem(), transferenciaDto.getNumeroContaOrigem());
		Transacao transacao = new Transacao("Transferencia-Debito", transferenciaDto.getValor(), new Date(), contaOrigem.getId());
		contaOrigem.getTransacoes().add(transacao);
		ContaBancaria contaDestino = carregarConta(transferenciaDto.getAgenciaDestino(), transferenciaDto.getNumeroContaDestino());
		Transacao transacao2 = new Transacao("Transferencia-Credito", transferenciaDto.getValor(), new Date(), contaDestino.getId());
		contaDestino.getTransacoes().add(transacao2);
		
		super.salvar(contaDestino);
		super.salvar(contaOrigem);
	}
	
	public ContaBancaria carregarConta(String agencia, String numero) {
		ContaBancaria conta = contaBancariaRepository.findByAgenciaAndNumero(agencia, numero);
		
		if (conta == null) {
			throw new AplicacaoException(ExceptionValidacoes.ERRO_CONTA_INVALIDA);
		}
		
		return conta;
	}
	 
	public List<ExtratoDTO> consultarExtrato(ConsultaExtratoDTO consultaExtratoDTO) {
		ContaBancaria conta = carregarConta(consultaExtratoDTO.getAgencia(), consultaExtratoDTO.getNumero());
		List<Transacao> transacoes = contaBancariaRepository.findByDate(
			converterStringToData(consultaExtratoDTO.getDataInicio() + " 00:00:00"),
				converterStringToData(consultaExtratoDTO.getDataFim() + " 23:59:59"), conta.getId());
		return criarExtrato(transacoes);
	}

	private Date converterStringToData(String data) {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Date date = null;
		try {
			date = formatter.parse(data);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
    }
	
	private List<ExtratoDTO> criarExtrato(List<Transacao> transacoes){
		List<ExtratoDTO> extratos = new ArrayList<>();
		for(Transacao transacao : transacoes) {
			ExtratoDTO extrato = new ExtratoDTO(transacao.getTipo(), transacao.getValor(), transacao.getData());
			extratos.add(extrato);
		}
		return extratos;
	}

	
	
}
