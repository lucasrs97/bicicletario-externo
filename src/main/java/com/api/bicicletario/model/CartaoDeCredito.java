package com.api.bicicletario.model;

import java.time.LocalDate;

public class CartaoDeCredito {

    private Long id;
    private String nomeTitular;
    private String numero;
    private LocalDate validade;
    private String ccv;

    public CartaoDeCredito(Long id, String nomeTitular, String numero, LocalDate validade, String ccv) {
        this.id = id;
        this.nomeTitular = nomeTitular;
        this.numero = numero;
        this.validade = validade;
        this.ccv = ccv;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeTitular() {
        return nomeTitular;
    }

    public void setNomeTitular(String nomeTitular) {
        this.nomeTitular = nomeTitular;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public LocalDate getValidade() {
        return validade;
    }

    public void setValidade(LocalDate validade) {
        this.validade = validade;
    }

    public String getCcv() {
        return ccv;
    }

    public void setCcv(String ccv) {
        this.ccv = ccv;
    }
}
