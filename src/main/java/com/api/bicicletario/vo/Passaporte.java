package com.api.bicicletario.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Passaporte {

    private String numero;
    private Date validade;
    private String pais;

}
