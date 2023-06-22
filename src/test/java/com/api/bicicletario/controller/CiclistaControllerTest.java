package com.api.bicicletario.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static com.api.bicicletario.util.Constantes.CICLISTA_ALTERADO_SUCESSO;
import static com.api.bicicletario.util.Constantes.CICLISTA_CADASTRADO_SUCESSO;
import static com.api.bicicletario.util.Constantes.EMAIL_CONFIRMADO_SUCESSO;
import static com.api.bicicletario.util.Constantes.ERRO_ALTERAR_CICLISTA;
import static com.api.bicicletario.util.Constantes.ERRO_ATIVAR_CICLISATA;
import static com.api.bicicletario.util.Constantes.ERRO_CADASTRAR_CICLISTA;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CiclistaControllerTest {

    @MockBean
    private CiclistaService ciclistaService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void cadastrarCiclista_comDadosValidos_deveRetornarCreated() throws Exception {
        CadastrarCiclistaDTO cadastro = CadastrarCiclistaDTOBuilder.build();

        mockMvc.perform(MockMvcRequestBuilders.post("/ciclista")
            .contentType(MediaType.APPLICATION_JSON)
            .content(obterStringJson(cadastro)))
            .andExpect(MockMvcResultMatchers.status().isCreated())
            .andExpect(MockMvcResultMatchers.content().string(CICLISTA_CADASTRADO_SUCESSO))
            .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void cadastrarCiclista_comDadosInvalidos_deveRetornarUnprocessableEntity() throws Exception {
        CadastrarCiclistaDTO cadastro = CadastrarCiclistaDTOBuilder.build();
        Mockito.doThrow(new IllegalArgumentException(ERRO_CADASTRAR_CICLISTA, null)).when(ciclistaService).cadastrarCiclista(Mockito.any());

        mockMvc.perform(MockMvcRequestBuilders.post("/ciclista")
            .contentType(MediaType.APPLICATION_JSON)
            .content(obterStringJson(cadastro)))
            .andExpect(MockMvcResultMatchers.status().isUnprocessableEntity())
            .andExpect(MockMvcResultMatchers.content().string(ERRO_CADASTRAR_CICLISTA))
            .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void confirmarEmail_comDadosValidos_deveRetornarOK() throws Exception {
        long idCiclista = 1L;

        mockMvc.perform(MockMvcRequestBuilders.post("/ciclista/{idCiclista}/ativar", idCiclista)
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.content().string(EMAIL_CONFIRMADO_SUCESSO))
            .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void confirmarEmail_comDadosInvalidos_deveRetornarUnprocessableEntity() throws Exception {
        long idCiclista = 1L;
        Mockito.doThrow(new IllegalArgumentException(ERRO_ATIVAR_CICLISATA, null)).when(ciclistaService).ativarCiclista(idCiclista);

        mockMvc.perform(MockMvcRequestBuilders.post("/ciclista/{idCiclista}/ativar", idCiclista)
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isUnprocessableEntity())
            .andExpect(MockMvcResultMatchers.content().string(ERRO_ATIVAR_CICLISATA));
    }

    @Test
    void alterarCiclista_comDadosValidos_deveRetornarOK() throws Exception {
        Ciclista ciclista = CadastrarCiclistaDTOBuilder.build().getCiclista();

        mockMvc.perform(MockMvcRequestBuilders.put("/ciclista")
            .contentType(MediaType.APPLICATION_JSON)
            .content(obterStringJson(ciclista)))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.content().string(CICLISTA_ALTERADO_SUCESSO))
            .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void alterarCiclista_comDadosInvalidos_deveRetornarUnprocessableEntity() throws Exception {
        Ciclista ciclista = CadastrarCiclistaDTOBuilder.build().getCiclista();
        Mockito.doThrow(new IllegalArgumentException(ERRO_ALTERAR_CICLISTA, null)).when(ciclistaService).alterarCiclista(Mockito.any());

        mockMvc.perform(MockMvcRequestBuilders.put("/ciclista")
            .contentType(MediaType.APPLICATION_JSON)
            .content(obterStringJson(ciclista)))
            .andExpect(MockMvcResultMatchers.status().isUnprocessableEntity())
            .andExpect(MockMvcResultMatchers.content().string(ERRO_ALTERAR_CICLISTA))
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
