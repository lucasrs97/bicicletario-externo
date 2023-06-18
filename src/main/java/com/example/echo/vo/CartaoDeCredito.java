package com.example.echo.vo;

import java.time.LocalDateTime;

public class CartaoDeCredito {

    private String numero;
    private String nome;
    private LocalDateTime validade;
    private int codigoSeguranca;

    public String getNumero() {
        return numero;
    }

    public String getNome() {
        return nome;
    }

    public LocalDateTime getValidade() {
        return validade;
    }

    public int getCodigoSeguranca() {
        return codigoSeguranca;
    }
}
