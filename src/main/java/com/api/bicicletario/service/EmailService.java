package com.api.bicicletario.service;

import org.springframework.stereotype.Service;

import static com.api.bicicletario.util.Constantes.ERRO_ENVIAR_EMAIL;

@Service
public class EmailService {

    public void enviarEmail(String email, String mensagem) {
        try {
            System.out.println("E-mail enviado com sucesso. " + "\n"
                    + "Destinatário: " + email + '\n'
                    + "Mensagem: " + mensagem + '\n');
        } catch (Exception e) {
            throw new IllegalArgumentException(ERRO_ENVIAR_EMAIL);
        }
    }

    public boolean emailValido(String email) {
        return email != null;
    }

}
