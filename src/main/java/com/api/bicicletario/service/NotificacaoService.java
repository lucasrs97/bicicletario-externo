package com.api.bicicletario.service;

import org.springframework.stereotype.Service;

@Service
public class NotificacaoService {

    public String enviarEmail(String destinatario, String assunto, String mensagem) {
        return "Enviando e-mail para: " + destinatario + " Assunto: " + assunto + " Mensagem: " + mensagem + " E-mail enviado com sucesso!";
    }
}
