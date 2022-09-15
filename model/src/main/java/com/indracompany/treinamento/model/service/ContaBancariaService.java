package com.indracompany.treinamento.model.service;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.indracompany.treinamento.model.entity.Extrato;
import com.indracompany.treinamento.model.repository.ExtratoRepositoy;
import com.indracompany.treinamento.util.ExtratoDescription;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.indracompany.treinamento.exception.AplicacaoException;
import com.indracompany.treinamento.exception.ExceptionValidacoes;
import com.indracompany.treinamento.model.dto.ContaClienteDTO;
import com.indracompany.treinamento.model.dto.DepositoDTO;
import com.indracompany.treinamento.model.dto.SaqueDTO;
import com.indracompany.treinamento.model.dto.TransferenciaBancariaDTO;
import com.indracompany.treinamento.model.entity.ContaBancaria;
import com.indracompany.treinamento.model.repository.ContaBancariaRepository;
import com.indracompany.treinamento.util.CpfUtil;

@Service
@RequiredArgsConstructor
public class ContaBancariaService extends GenericCrudService<ContaBancaria, Long, ContaBancariaRepository>{


    private final ExtratoRepositoy extratoRepository;

    private final ContaBancariaRepository contaBancariaRepository;



    public List<ContaClienteDTO> listarContasDoCliente(String cpf){

        boolean cpfValido = CpfUtil.validaCPF(cpf);
        if (!cpfValido) {
            throw new AplicacaoException(ExceptionValidacoes.ERRO_CPF_INVALIDO, cpf);
        }

        List<ContaBancaria> contasBancarias = contaBancariaRepository.findByClienteCpf(cpf)
                .orElseThrow(() -> new AplicacaoException(ExceptionValidacoes.ALERTA_NENHUM_REGISTRO_ENCONTRADO));

        List<ContaClienteDTO> listaRetornoDTO = new ArrayList<>();

        for (ContaBancaria conta : contasBancarias) {
            ContaClienteDTO dto = new ContaClienteDTO();
            BeanUtils.copyProperties(conta, dto);
            dto.setNomeCliente(conta.getCliente().getNome());
            listaRetornoDTO.add(dto);
        }

        return listaRetornoDTO;
    }

    public void depositar(DepositoDTO dto, Boolean trasnferencia) {
        ContaBancaria contaBancaria = this.carregarConta(dto.getAgencia(), dto.getNumeroConta());
        contaBancaria.setSaldo(contaBancaria.getSaldo() + dto.getValor());

        if(!trasnferencia){
            this.criarExtrato(dto.getAgencia(),
                    dto.getNumeroConta(),
                    dto.getValor(),
                    ExtratoDescription.DEPOSITO.getDescription());
        }


        super.salvar(contaBancaria);
    }

    public void sacar(SaqueDTO dto, Boolean transferencia) {
        ContaBancaria contaBancaria = this.carregarConta(dto.getAgencia(), dto.getNumeroConta());
        if (contaBancaria.getSaldo() < dto.getValor()) {
            throw new AplicacaoException(ExceptionValidacoes.ERRO_SALDO_INEXISTENTE);
        }
        contaBancaria.setSaldo(contaBancaria.getSaldo() - dto.getValor());

        if(!transferencia){
            this.criarExtrato(dto.getAgencia(),
                    dto.getNumeroConta(),
                    dto.getValor(),
                    ExtratoDescription.SAQUE.getDescription());
        }


        super.salvar(contaBancaria);
    }

    @Transactional(rollbackFor = Exception.class)
    public void transferir(TransferenciaBancariaDTO transferenciaDto) {

        SaqueDTO saqueDto = new SaqueDTO();
        saqueDto.setAgencia(transferenciaDto.getAgenciaOrigem());
        saqueDto.setNumeroConta(transferenciaDto.getNumeroContaOrigem());
        saqueDto.setValor(transferenciaDto.getValor());

        this.sacar(saqueDto, true);

        this.criarExtrato(saqueDto.getAgencia(),
                saqueDto.getNumeroConta(),
                transferenciaDto.getValor(),
                ExtratoDescription.TRANSFERENCIAORIGEM.getDescription());


        DepositoDTO depositoDto = new DepositoDTO();
        depositoDto.setAgencia(transferenciaDto.getAgenciaDestino());
        depositoDto.setNumeroConta(transferenciaDto.getNumeroContaDestino());
        depositoDto.setValor(transferenciaDto.getValor());

        this.depositar(depositoDto, true);

        this.criarExtrato(depositoDto.getAgencia(),
                depositoDto.getNumeroConta(),
                transferenciaDto.getValor(),
                ExtratoDescription.TRANSFERENCIADESTINO.getDescription());

    }

    public ContaBancaria carregarConta(String agencia, String numero) {
        ContaBancaria conta = contaBancariaRepository.findByAgenciaAndNumero(agencia, numero)
                .orElseThrow(() -> new AplicacaoException(ExceptionValidacoes.ERRO_CONTA_INVALIDA));

        return conta;
    }

    public void criarExtrato(String agencia, String numero, Double valor, String description){
        ContaBancaria contaBancaria = carregarConta(agencia, numero);
        Extrato extrato = new Extrato();
        DecimalFormat df = new DecimalFormat("#.00");
        StringBuilder sb = new StringBuilder();

        sb.append(description);
        sb.append(df.format(valor));
        sb.append(" reais.");

        extrato.setDate(new Date());
        extrato.setDescription(sb.toString());
        extrato.setConta(contaBancaria);
        extratoRepository.save(extrato);
    }
}