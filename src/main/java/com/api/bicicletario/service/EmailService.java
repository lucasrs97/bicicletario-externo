package com.api.bicicletario.service;

import com.api.bicicletario.exception.ValidatorException;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    public void enviarEmail(String email, String mensagem) {
        try {
            System.out.println("E-mail enviado com sucesso. " + "\n"
                    + "Destinatário: " + email + '\n'
                    + "Mensagem: " + mensagem + '\n');
        } catch (Exception e) {
            throw new ValidatorException("Não foi possível enviar o e-mail");
        }
    }

    public boolean emailValido(String email) {
        return true;
    }

}
