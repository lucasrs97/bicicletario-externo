package com.api.bicicletario.controller;

import com.api.bicicletario.model.CartaoDeCredito;
import com.api.bicicletario.service.CartaoDeCreditoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static com.api.bicicletario.util.Constantes.ERRO_ALTERAR_DADOS_CARTAO;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CartaoDeCreditoControllerTest {

    @MockBean
    private CartaoDeCreditoService cartaoDeCreditoService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void alterarCartao_comDadosValidos_DeveRetornarStatusOk() throws Exception {
        CartaoDeCredito cartaoDeCredito = new CartaoDeCredito();
        Long idCiclista = 1L;

        doNothing().when(cartaoDeCreditoService).alterar(any(CartaoDeCredito.class), any(Long.class));

        mockMvc.perform(MockMvcRequestBuilders.put("/{idCiclista}", idCiclista)
            .contentType(MediaType.APPLICATION_JSON)
            .content(obterStringJson(cartaoDeCredito)))
            .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void alterarCartao_comDadosInvalidos_deveRetornarStatusUnprocessableEntity() throws Exception {
        CartaoDeCredito cartaoDeCredito = new CartaoDeCredito();
        Long idCiclista = 1L;

        doThrow(new IllegalArgumentException(ERRO_ALTERAR_DADOS_CARTAO)).when(cartaoDeCreditoService).alterar(any(CartaoDeCredito.class), any(Long.class));

        mockMvc.perform(MockMvcRequestBuilders.put("/{idCiclista}", idCiclista)
            .contentType(MediaType.APPLICATION_JSON)
            .content(obterStringJson(cartaoDeCredito)))
            .andExpect(MockMvcResultMatchers.content().string(ERRO_ALTERAR_DADOS_CARTAO))
            .andExpect(MockMvcResultMatchers.status().isUnprocessableEntity());
    }

    private static String obterStringJson(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
