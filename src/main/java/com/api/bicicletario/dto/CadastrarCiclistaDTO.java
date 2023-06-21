package com.api.bicicletario.dto;

import com.api.bicicletario.model.CartaoDeCredito;
import com.api.bicicletario.model.Ciclista;
import lombok.Data;

@Data
public class CadastrarCiclistaDTO {
    private Ciclista ciclista;
    private CartaoDeCredito meioDePagamento;
}
