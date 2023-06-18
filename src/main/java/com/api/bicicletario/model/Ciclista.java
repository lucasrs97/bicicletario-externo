package com.api.bicicletario.model;

import com.api.bicicletario.enumerator.CiclistaStatus;
import com.api.bicicletario.enumerator.Nacionalidade;
import com.api.bicicletario.dto.CiclistaDTO;
import com.api.bicicletario.vo.Passaporte;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class Ciclista {
    private Long id;
    private CiclistaStatus status;
    private String nome;
    private LocalDate nascimento;
    private String cpf;
    private Passaporte passaporte;
    private Nacionalidade nacionalidade;
    private String email;
    private String urlFotoDocumento;

    public Ciclista(CiclistaDTO ciclistaDTO) {
        this.nome = ciclistaDTO.getNome();
        this.nascimento = ciclistaDTO.getNascimento();
        this.cpf = ciclistaDTO.getCpf();

        this.passaporte = new Passaporte(ciclistaDTO.getPassaporte().getNumero(),
                ciclistaDTO.getPassaporte().getValidade(),
                ciclistaDTO.getPassaporte().getPais());

        if(ciclistaDTO.getNacionalidade().equals("B")) {
            this.nacionalidade = Nacionalidade.BRASILEIRO;
        } else if(ciclistaDTO.getNacionalidade().equals("E")) {
            this.nacionalidade = Nacionalidade.ESTRANGEIRO;
        }

        this.email = ciclistaDTO.getEmail();
        this.urlFotoDocumento = ciclistaDTO.getUrlFotoDocumento();
    }

}
