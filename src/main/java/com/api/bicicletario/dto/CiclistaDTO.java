package com.api.bicicletario.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class CiclistaDTO {

    private String nome;
    private LocalDate nascimento;
    private String cpf;
    private PassaporteDTO passaporte;
    private String nacionalidade;
    private String email;
    private String urlFotoDocumento;
    private String senha;

}
