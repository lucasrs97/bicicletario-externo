package com.api.bicicletario.dto;

import lombok.Data;

@Data
public class CadastrarCiclistaDTO {
    private CiclistaDTO ciclista;
    private MeioPagamentoDTO meioDePagamento;
}
