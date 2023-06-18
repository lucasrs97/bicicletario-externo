package com.example.echo.service;

public class EmailService {

    public boolean enviarEmail(String email, String mensagem) {
        return email != null && mensagem != null;
    }

    public boolean emailValido(String email) {
        return true;
    }

}
