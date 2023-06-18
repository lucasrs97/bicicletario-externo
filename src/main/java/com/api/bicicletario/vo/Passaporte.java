package com.api.bicicletario.vo;

import lombok.AllArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
public class Passaporte {

    private String numero;
    private LocalDate validade;
    private String pais;

}
