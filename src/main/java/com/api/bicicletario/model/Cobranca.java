package com.api.bicicletario.model;

import java.time.LocalDateTime;

public class Cobranca {
    private Integer id;
    private String status;
    private LocalDateTime horaSolicitacao;
    private LocalDateTime horaFinalizacao;
    private Double valor;
    private Integer ciclista;
    private String cartao;

    public Cobranca(Integer id, String status, LocalDateTime horaSolicitacao, LocalDateTime horaFinalizacao, double valor, Integer ciclista, String cartao) {
        this.id = id;
        this.status = status;
        this.horaSolicitacao = horaSolicitacao;
        this.horaFinalizacao = horaFinalizacao;
        this.valor = valor;
        this.ciclista = ciclista;
        this.cartao = cartao;
    }

    public Cobranca() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getHoraSolicitacao() {
        return horaSolicitacao;
    }

    public void setHoraSolicitacao(LocalDateTime horaSolicitacao) {
        this.horaSolicitacao = horaSolicitacao;
    }

    public LocalDateTime getHoraFinalizacao() {
        return horaFinalizacao;
    }

    public void setHoraFinalizacao(LocalDateTime horaFinalizacao) {
        this.horaFinalizacao = horaFinalizacao;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public Integer getCiclista() {
        return ciclista;
    }

    public void setCiclista(Integer ciclista) {
        this.ciclista = ciclista;
    }

    public String getCartao() {
        return cartao;
    }

    public void setCartao(String cartao) {
        this.cartao = cartao;
    }
}
