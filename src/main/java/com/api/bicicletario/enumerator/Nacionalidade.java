package com.api.bicicletario.enumerator;

public enum Nacionalidade {

    BRASILEIRO("B", "Brasileiro"),
    ESTRANGEIRO("E", "Estrangeiro");

    private String codigo;
    private String descrição;

    Nacionalidade(String codigo, String descrição) {
        this.codigo = codigo;
        this.descrição = descrição;
    }

}
