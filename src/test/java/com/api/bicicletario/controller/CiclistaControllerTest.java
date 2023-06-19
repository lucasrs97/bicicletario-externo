package com.api.bicicletario.controller;

import com.api.bicicletario.builder.CadastrarCiclistaDTOBuilder;
import com.api.bicicletario.dto.CadastrarCiclistaDTO;
import com.api.bicicletario.exception.ValidatorException;
import com.api.bicicletario.service.CiclistaService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CiclistaControllerTest {

    @MockBean
    private CiclistaService ciclistaService;

    private MockMvc mockMvc;

    @Test
    void cadastrarCiclista_comDadosValidos_deveRetornarCreated() throws Exception {
        CadastrarCiclistaDTO cadastro = CadastrarCiclistaDTOBuilder.build();

        mockMvc.perform(MockMvcRequestBuilders.post("/ciclistas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(obterStringJson(cadastro)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().string("Ciclista cadastrado com sucesso. Aguardando confirmação do e-mail."))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void cadastrarCiclista_comDadosInvalidos_deveRetornarUnprocessableEntity() throws Exception {
        CadastrarCiclistaDTO cadastro = CadastrarCiclistaDTOBuilder.build();
        Mockito.doThrow(new ValidatorException("Erro ao cadastrar o ciclista.", null)).when(ciclistaService).cadastrarCiclista(Mockito.any());

        mockMvc.perform(MockMvcRequestBuilders.post("/ciclistas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(obterStringJson(cadastro)))
                .andExpect(MockMvcResultMatchers.status().isUnprocessableEntity())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void cadastrarCiclista_WithException_ReturnsNotFound() throws Exception {
        CadastrarCiclistaDTO cadastro = CadastrarCiclistaDTOBuilder.build();
        Mockito.doThrow(new Exception("Erro ao cadastrar o ciclista.")).when(ciclistaService).cadastrarCiclista(Mockito.any());

        mockMvc.perform(MockMvcRequestBuilders.post("/ciclistas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(obterStringJson(cadastro)))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.content().string("Erro ao cadastrar o ciclista."))
                .andDo(MockMvcResultHandlers.print());
    }

    private static String obterStringJson(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
