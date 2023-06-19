package com.api.bicicletario.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class PassaporteDTO {

    private String numero;
    private LocalDate validade;
    private String pais;

}
