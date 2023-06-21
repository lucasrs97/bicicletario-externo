package com.api.bicicletario.service;

import com.api.bicicletario.dao.CartaoDAO;
import com.api.bicicletario.dao.CiclistaDAO;
import com.api.bicicletario.model.Ciclista;
import com.api.bicicletario.model.CartaoDeCredito;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CartaoDeCreditoServiceTest {

    @InjectMocks
    private CartaoDeCreditoService cartaoDeCreditoService;

    @Mock
    private CiclistaDAO ciclistaDAO;

    @Mock
    private CartaoDAO cartaoDAO;

    @Mock
    private EmailService emailService;

    @Test
    void cartaoDeCreditoInvalido_deveRetornarTrueOuFalse() {
        CartaoDeCredito cartaoDeCredito = new CartaoDeCredito();
        assertTrue(cartaoDeCreditoService.cartaoDeCreditoInvalido(cartaoDeCredito));

        cartaoDeCredito.setNomeTitular("Lucas");
        assertTrue(cartaoDeCreditoService.cartaoDeCreditoInvalido(cartaoDeCredito));

        cartaoDeCredito.setNumero("1234567890");
        assertTrue(cartaoDeCreditoService.cartaoDeCreditoInvalido(cartaoDeCredito));

        cartaoDeCredito.setValidade(new Date());
        assertTrue(cartaoDeCreditoService.cartaoDeCreditoInvalido(cartaoDeCredito));

        cartaoDeCredito.setCcv("123");
        assertFalse(cartaoDeCreditoService.cartaoDeCreditoInvalido(cartaoDeCredito));
    }

    @Test
    void alterarCartao_comDadosInvalidos_deveAlterarComSucesso() {
        CartaoDeCredito cartaoDeCredito = new CartaoDeCredito();
        Long idCiclista = 1L;

        Ciclista ciclista = new Ciclista();
        String email = "lucas@email.com";
        ciclista.setEmail(email);

        when(ciclistaDAO.recuperarCiclista(idCiclista)).thenReturn(ciclista);

        assertThrows(IllegalArgumentException.class, () -> cartaoDeCreditoService.alterar(cartaoDeCredito, idCiclista));
        verify(cartaoDAO, never()).alterarCartao(cartaoDeCredito);
        verify(emailService, never()).enviarEmail(email, "Dados do cartão alterados com sucesso.");
    }

    @Test
    void alterarCartao_comDadosValidos_deveAlterarComSucesso() {
        CartaoDeCredito cartaoDeCredito = new CartaoDeCredito();
        Long idCiclista = 1L;

        Ciclista ciclista = new Ciclista();
        String email = "lucas@email.com";
        ciclista.setEmail(email);

        cartaoDeCredito.setNomeTitular("Lucas");
        cartaoDeCredito.setNumero("1234567890");
        cartaoDeCredito.setValidade(new Date());
        cartaoDeCredito.setCcv("123");

        when(ciclistaDAO.recuperarCiclista(idCiclista)).thenReturn(ciclista);

        assertDoesNotThrow(() -> cartaoDeCreditoService.alterar(cartaoDeCredito, idCiclista));
        verify(cartaoDAO, times(1)).alterarCartao(cartaoDeCredito);
        verify(emailService, times(1)).enviarEmail(email, "Dados do cartão alterados com sucesso.");
    }

}