package com.indracompany.treinamento.model.service;

import com.indracompany.treinamento.model.dto.ExtratoDTO;
import com.indracompany.treinamento.model.entity.ContaBancaria;
import com.indracompany.treinamento.model.entity.Extrato;
import com.indracompany.treinamento.model.repository.ContaBancariaRepository;
import com.indracompany.treinamento.model.repository.ExtratoRepositoy;
import com.indracompany.treinamento.util.ExtratoDescription;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@SpringBootTest
class ExtratoServiceTest {

    @InjectMocks
    public ExtratoService service;

    @Mock
    public ExtratoRepositoy extratoRepositoy;
    @Mock
    public ContaBancariaRepository contaRepository;

    private final Integer INDEX = 0;
    private final String AGENCIA = "6545";
    private final String CONTA = "6545-7";
    private final String DESCRIPTION = ExtratoDescription.SAQUE.getDescription();
    private final  String CPF = "68280212078";
    private final Date DATESET = new Date();
    private final String DATE = new SimpleDateFormat("dd/MM/yyyy").format(DATESET);
    private final String HOUR = new SimpleDateFormat("HH:mm:ss").format(DATESET);

    private Extrato extrato;
    private Optional<List<ContaBancaria>> contaOptionalList;
    private Optional<List<Extrato>> optionalExtratoList;
    private String data1;
    private String data2;

    @BeforeEach
    void setUp() {
        LocalDate DATEFULL = LocalDate.now();
        ContaBancaria conta = new ContaBancaria();
        Date convert = Date.from(DATEFULL.plusDays(1).atStartOfDay(ZoneId.systemDefault()).toInstant());

        extrato = new Extrato();
        data1 = new SimpleDateFormat("ddMMyyyy").format(DATESET);
        data2 = new SimpleDateFormat("ddMMyyyy").format(convert);;

        conta.setAgencia(AGENCIA);
        conta.setNumero(CONTA);

        extrato.setId(1L);
        extrato.setConta(conta);
        extrato.setDescription(DESCRIPTION);
        extrato.setDate(DATESET);

        List<Extrato> list = List.of(extrato);
        List<ContaBancaria> contaList = List.of(conta);
        contaOptionalList = Optional.of(contaList);
        optionalExtratoList = Optional.of(list);
    }

    @Test
    void shouldReturnAListWhenACpfIsPassed() {
        given(contaRepository.findByClienteCpf(any(String.class))).willReturn(contaOptionalList);
        given(extratoRepositoy.findByConta(any(ContaBancaria.class))).willReturn(optionalExtratoList);

        ExtratoDTO test = service.findAllByCpf(CPF).get(INDEX);

        assertThat(test).isNotNull();
        assertThat(test.getClass()).isEqualTo(ExtratoDTO.class);

        assertThat(test.getDescription()).isEqualTo(DESCRIPTION);
        assertThat(test.getAgencia()).isEqualTo(AGENCIA);
        assertThat(test.getConta()).isEqualTo(CONTA);
        assertThat(test.getHour()).isEqualTo(HOUR);
        assertThat(test.getDate()).isEqualTo(DATE);
    }

    @Test
    void shouldReturnAListWhenACpfAndADateIsPassed() {
        given(contaRepository.findByClienteCpf(any(String.class))).willReturn(contaOptionalList);
        given(extratoRepositoy.findByConta(any(ContaBancaria.class))).willReturn(optionalExtratoList);

        ExtratoDTO test = service.findByData(CPF, data1).get(INDEX);

        assertThat(test).isNotNull();
        assertThat(test.getClass()).isEqualTo(ExtratoDTO.class);

        assertThat(test.getDescription()).isEqualTo(DESCRIPTION);
        assertThat(test.getAgencia()).isEqualTo(AGENCIA);
        assertThat(test.getConta()).isEqualTo(CONTA);
        assertThat(test.getHour()).isEqualTo(HOUR);
        assertThat(test.getDate()).isEqualTo(DATE);
    }

    @Test
    void shouldReturnAListWhenACpfAndDatesArePassed() {
        given(contaRepository.findByClienteCpf(any(String.class))).willReturn(contaOptionalList);
        given(extratoRepositoy.findByConta(any(ContaBancaria.class))).willReturn(optionalExtratoList);

        ExtratoDTO test = service.findByIntervalo(CPF, data1, data2).get(INDEX);

        assertThat(test).isNotNull();
        assertThat(test.getClass()).isEqualTo(ExtratoDTO.class);

        assertThat(test.getDescription()).isEqualTo(DESCRIPTION);
        assertThat(test.getAgencia()).isEqualTo(AGENCIA);
        assertThat(test.getConta()).isEqualTo(CONTA);
        assertThat(test.getHour()).isEqualTo(HOUR);
        assertThat(test.getDate()).isEqualTo(DATE);
    }
}