package com.api.bicicletario.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartaoDeCredito {

    private Long id;
    private String nomeTitular;
    private String numero;
    private Date validade;
    private String ccv;

}
