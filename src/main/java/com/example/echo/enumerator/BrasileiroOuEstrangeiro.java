package com.example.echo.enumerator;

public enum BrasileiroOuEstrangeiro {

    BRASILEIRO("B", "Brasileiro"),
    ESTRANGEIRO("E", "Estrangeiro");

    private String codigo;
    private String descrição;

    BrasileiroOuEstrangeiro(String codigo, String descrição) {
        this.codigo = codigo;
        this.descrição = descrição;
    }

}
