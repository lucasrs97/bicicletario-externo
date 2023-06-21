package com.api.bicicletario.service;

import org.springframework.stereotype.Service;

@Service
public class NotificacaoService {

    public void enviarEmail(String destinatario, String assunto, String mensagem) {
        // Lógica de envio de e-mail
        System.out.println("Enviando e-mail para: " + destinatario);
        System.out.println("Assunto: " + assunto);
        System.out.println("Mensagem: " + mensagem);
        System.out.println("E-mail enviado com sucesso!");
    }

}
