package com.api.bicicletario.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class PassaporteDTO {

    private String numero;
    private LocalDate validade;
    private String pais;

}
