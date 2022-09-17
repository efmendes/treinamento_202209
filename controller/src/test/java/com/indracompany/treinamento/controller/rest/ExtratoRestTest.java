package com.indracompany.treinamento.controller.rest;

import com.indracompany.treinamento.model.dto.ExtratoDTO;
import com.indracompany.treinamento.model.entity.ContaBancaria;
import com.indracompany.treinamento.model.service.ExtratoService;
import com.indracompany.treinamento.util.ExtratoDescription;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@SpringBootTest
class ExtratoRestTest {

    @InjectMocks
    public ExtratoRest extratoRest;

    @Mock
    public ExtratoService extratoService;

    private final Integer INDEX = 0;
    private final String AGENCIA = "6545";
    private final String CONTA = "6545-7";
    private final String DESCRIPTION = ExtratoDescription.SAQUE.getDescription();
    private final  String CPF = "68280212078";
    private final Date DATESET = new Date();
    private final String DATE = new SimpleDateFormat("dd/MM/yyyy").format(DATESET);
    private final String HOUR = new SimpleDateFormat("HH:mm:ss").format(DATESET);

    private ExtratoDTO extrato;
    private String data1;
    private String data2;
    List<ExtratoDTO> list;

    @BeforeEach
    void setUp() {
        LocalDate DATEFULL = LocalDate.now();
        ContaBancaria conta = new ContaBancaria();
        Date convert = Date.from(DATEFULL.plusDays(1).atStartOfDay(ZoneId.systemDefault()).toInstant());

        extrato = new ExtratoDTO();
        data1 = new SimpleDateFormat("ddMMyyyy").format(DATESET);
        data2 = new SimpleDateFormat("ddMMyyyy").format(convert);

        conta.setAgencia(AGENCIA);
        conta.setNumero(CONTA);

        extrato.setDate(DATE);
        extrato.setHour(HOUR);
        extrato.setAgencia(AGENCIA);
        extrato.setConta(CONTA);
        extrato.setDescription(DESCRIPTION);


        list = List.of(extrato);
    }

    @Test
    void shouldReturnAListWhenACpfIsPassed() {
        given(extratoService.findAllByCpf(any(String.class))).willReturn(list);

        ResponseEntity<List<ExtratoDTO>> test = extratoRest.findAllByCpf(CPF);
        ExtratoDTO testBody = test.getBody().get(INDEX);

        assertThat(test).isNotNull();
        assertThat(test.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(test.getClass()).isEqualTo(ResponseEntity.class);
        assertThat(test.hasBody()).isEqualTo(true);

        assertThat(testBody).isNotNull();
        assertThat(testBody.getClass()).isEqualTo(ExtratoDTO.class);
        assertThat(testBody.getDate()).isEqualTo(DATE);
        assertThat(testBody.getHour()).isEqualTo(HOUR);
        assertThat(testBody.getAgencia()).isEqualTo(AGENCIA);
        assertThat(testBody.getConta()).isEqualTo(CONTA);
        assertThat(testBody.getDescription()).isEqualTo(DESCRIPTION);

    }

    @Test
    void shouldReturnAListWhenACpfAndADateIsPassed() {
        given(extratoService.findByData(any(String.class), any(String.class))).willReturn(list);

        ResponseEntity<List<ExtratoDTO>> test = extratoRest.findAllByDate(CPF, DATE);
        ExtratoDTO testBody = test.getBody().get(INDEX);

        assertThat(test).isNotNull();
        assertThat(test.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(test.getClass()).isEqualTo(ResponseEntity.class);
        assertThat(test.hasBody()).isEqualTo(true);

        assertThat(testBody).isNotNull();
        assertThat(testBody.getClass()).isEqualTo(ExtratoDTO.class);
        assertThat(testBody.getDate()).isEqualTo(DATE);
        assertThat(testBody.getHour()).isEqualTo(HOUR);
        assertThat(testBody.getAgencia()).isEqualTo(AGENCIA);
        assertThat(testBody.getConta()).isEqualTo(CONTA);
        assertThat(testBody.getDescription()).isEqualTo(DESCRIPTION);
    }

    @Test
    void shouldReturnAListWhenACpfAndDatesArePassed() {
        given(extratoService.findByIntervalo(any(String.class),any(String.class),any(String.class))).willReturn(list);

        ResponseEntity<List<ExtratoDTO>> test = extratoRest.findAllByIntervalo(CPF, data1, data2);
        ExtratoDTO testBody = test.getBody().get(INDEX);

        assertThat(test).isNotNull();
        assertThat(test.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(test.getClass()).isEqualTo(ResponseEntity.class);
        assertThat(test.hasBody()).isEqualTo(true);

        assertThat(testBody).isNotNull();
        assertThat(testBody.getClass()).isEqualTo(ExtratoDTO.class);
        assertThat(testBody.getDate()).isEqualTo(DATE);
        assertThat(testBody.getHour()).isEqualTo(HOUR);
        assertThat(testBody.getAgencia()).isEqualTo(AGENCIA);
        assertThat(testBody.getConta()).isEqualTo(CONTA);
        assertThat(testBody.getDescription()).isEqualTo(DESCRIPTION);
    }
}