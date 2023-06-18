package com.api.bicicletario.model;

import com.api.bicicletario.dto.MeioPagamentoDTO;

import java.time.LocalDate;

public class CartaoDeCredito {

    private Long id;
    private String nomeTitular;
    private String numero;
    private LocalDate validade;
    private String ccv;

    public CartaoDeCredito(MeioPagamentoDTO meioPagamentoDTO) {
        this.nomeTitular = meioPagamentoDTO.getNomeTitular();
        this.numero = meioPagamentoDTO.getNumero();
        this.validade = meioPagamentoDTO.getValidade();
        this.ccv = meioPagamentoDTO.getCcv();
    }

}
