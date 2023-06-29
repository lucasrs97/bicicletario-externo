package com.api.bicicletario.service;

import com.api.bicicletario.service.NotificacaoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NotificacaoServiceTest {

    @InjectMocks
    private NotificacaoService notificacaoService;

    @BeforeEach
    public void setUp() {
        notificacaoService = new NotificacaoService();
    }

    @Test
    public void testEnviarEmail() {
        String destinatario = "exemplo@teste.com";
        String assunto = "Assunto do e-mail";
        String mensagem = "Corpo da mensagem";

        String retorno = notificacaoService.enviarEmail(destinatario, assunto, mensagem);

        String retornoEsperado = "Enviando e-mail para: " + destinatario + " Assunto: " + assunto + " Mensagem: " + mensagem + " E-mail enviado com sucesso!";
        assertEquals(retornoEsperado, retorno);
    }
}