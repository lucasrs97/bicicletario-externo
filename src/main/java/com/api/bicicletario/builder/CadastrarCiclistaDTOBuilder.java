package com.api.bicicletario.builder;

import com.api.bicicletario.dto.CadastrarCiclistaDTO;
import com.api.bicicletario.dto.CiclistaDTO;
import com.api.bicicletario.dto.MeioPagamentoDTO;
import com.api.bicicletario.dto.PassaporteDTO;

import java.time.LocalDate;

public class CadastrarCiclistaDTOBuilder {

    public static CadastrarCiclistaDTO build() {
        CadastrarCiclistaDTO cadastro = new CadastrarCiclistaDTO();

        CiclistaDTO ciclistaDTO = new CiclistaDTO();
        ciclistaDTO.setNome("Lucas");
        ciclistaDTO.setNascimento(LocalDate.of(1997,2,22));
        ciclistaDTO.setCpf("123.456.789-10");
        ciclistaDTO.setPassaporte(new PassaporteDTO("123,", LocalDate.of(2030,10,15), "BR"));
        ciclistaDTO.setNacionalidade("B");
        ciclistaDTO.setEmail("Lucas@gmail.com");
        ciclistaDTO.setUrlFotoDocumento("api.foto/img001");

        MeioPagamentoDTO meioPagamentoDTO = new MeioPagamentoDTO();
        meioPagamentoDTO.setNumero("1234-5678-9012-3456");
        meioPagamentoDTO.setNomeTitular("Lucas");
        meioPagamentoDTO.setValidade(LocalDate.of(2030,10,15));
        meioPagamentoDTO.setCcv("123");

        cadastro.setCiclista(ciclistaDTO);
        cadastro.setMeioDePagamento(meioPagamentoDTO);

        return cadastro;
    }

}
