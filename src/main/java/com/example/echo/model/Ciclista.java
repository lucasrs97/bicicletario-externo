package com.example.echo.model;

import com.example.echo.enumerator.BrasileiroOuEstrangeiro;
import com.example.echo.enumerator.CiclistaStatus;
import com.example.echo.vo.CartaoDeCredito;
import com.example.echo.vo.Passaporte;

public class Ciclista {
    private Long id;
    private String nome;
    private BrasileiroOuEstrangeiro brasileiroOuEstrangeiro;
    private String cpf;
    private Passaporte passaporte;

    private String email;

    private String senha;
    private CartaoDeCredito cartaoDeCredito;
    private String fotoEnviada;

    private CiclistaStatus status;

    public CiclistaStatus getStatus() {
        return status;
    }

    public void setStatus(CiclistaStatus status) {
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public BrasileiroOuEstrangeiro getBrasileiroOuEstrangeiro() {
        return brasileiroOuEstrangeiro;
    }

    public void setBrasileiroOuEstrangeiro(BrasileiroOuEstrangeiro brasileiroOuEstrangeiro) {
        this.brasileiroOuEstrangeiro = brasileiroOuEstrangeiro;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Passaporte getPassaporte() {
        return passaporte;
    }

    public void setPassaporte(Passaporte passaporte) {
        this.passaporte = passaporte;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public CartaoDeCredito getCartaoDeCredito() {
        return cartaoDeCredito;
    }

    public void setCartaoDeCredito(CartaoDeCredito cartaoDeCredito) {
        this.cartaoDeCredito = cartaoDeCredito;
    }

    public String fotoEnviada() {
        return fotoEnviada;
    }

    public void setFotoEnviada(String fotoEnviada) {
        this.fotoEnviada = fotoEnviada;
    }
}
