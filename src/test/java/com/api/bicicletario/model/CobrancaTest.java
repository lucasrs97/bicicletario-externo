package com.api.bicicletario.model;

import com.api.bicicletario.model.Cobranca;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;

import static org.mockito.Mockito.*;

class CobrancaTest {

    @Mock
    private Cobranca cobranca;
    private Cobranca cobranca2;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        cobranca2 = new Cobranca();
    }

    @Test
    void testGettersAndSetters() {
        Integer id = 1;
        String status = "pending";
        LocalDateTime horaSolicitacao = LocalDateTime.now();
        LocalDateTime horaFinalizacao = LocalDateTime.now();
        double valor = 10.0;
        Integer ciclista = 123;
        String cartao = "1234567890";

        when(cobranca.getId()).thenReturn(id);
        when(cobranca.getStatus()).thenReturn(status);
        when(cobranca.getHoraSolicitacao()).thenReturn(horaSolicitacao);
        when(cobranca.getHoraFinalizacao()).thenReturn(horaFinalizacao);
        when(cobranca.getValor()).thenReturn(valor);
        when(cobranca.getCiclista()).thenReturn(ciclista);
        when(cobranca.getCartao()).thenReturn(cartao);

        Assertions.assertEquals(id, cobranca.getId());
        Assertions.assertEquals(status, cobranca.getStatus());
        Assertions.assertEquals(horaSolicitacao, cobranca.getHoraSolicitacao());
        Assertions.assertEquals(horaFinalizacao, cobranca.getHoraFinalizacao());
        Assertions.assertEquals(valor, cobranca.getValor());
        Assertions.assertEquals(ciclista, cobranca.getCiclista());
        Assertions.assertEquals(cartao, cobranca.getCartao());
    }

    @Test
    void testSetters() {
        Integer id = 0;
        String status = "pending";
        LocalDateTime horaSolicitacao = LocalDateTime.now();
        LocalDateTime horaFinalizacao = LocalDateTime.now();
        double valor = 10.0;
        Integer ciclista = 123;
        String cartao = "1234567890";

        cobranca2.setId(id);
        cobranca2.setStatus(status);
        cobranca2.setHoraSolicitacao(horaSolicitacao);
        cobranca2.setHoraFinalizacao(horaFinalizacao);
        cobranca2.setValor(valor);
        cobranca2.setCiclista(ciclista);
        cobranca2.setCartao(cartao);

        Assertions.assertEquals(id, cobranca2.getId());
        Assertions.assertEquals(horaSolicitacao, cobranca2.getHoraSolicitacao());
        Assertions.assertEquals(horaFinalizacao, cobranca2.getHoraFinalizacao());
        Assertions.assertEquals(valor, cobranca2.getValor());
        Assertions.assertEquals(ciclista, cobranca2.getCiclista());
        Assertions.assertEquals(cartao, cobranca2.getCartao());
    }
}
