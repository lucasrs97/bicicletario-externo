package com.api.bicicletario.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class MeioPagamentoDTO {

    private String nomeTitular;
    private String numero;
    private LocalDate validade;
    private String ccv;

}
