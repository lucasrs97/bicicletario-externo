package com.api.bicicletario.model;

import lombok.Data;

import java.util.Date;

@Data
public class CartaoDeCredito {

    private Long id;
    private String nomeTitular;
    private String numero;
    private Date validade;
    private String ccv;

}
