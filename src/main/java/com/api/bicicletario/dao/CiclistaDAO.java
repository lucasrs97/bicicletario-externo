package com.api.bicicletario.dao;

import com.api.bicicletario.enumerator.CiclistaStatus;
import com.api.bicicletario.enumerator.Nacionalidade;
import com.api.bicicletario.model.Ciclista;
import com.api.bicicletario.vo.Passaporte;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public class CiclistaDAO {

    public void salvarCiclista(Ciclista ciclista) {
        System.out.println("Salvando o ciclista: " + ciclista.toString());
    }

    public void alterarCartao(Ciclista ciclista) {
        System.out.println("Alterando o ciclista: " + ciclista.toString());
    }

    public Ciclista recuperarCiclista(Long id) {
        Ciclista ciclista = new Ciclista();
        ciclista.setId(id);
        ciclista.setStatus(CiclistaStatus.ATIVO);
        ciclista.setNome("Lucas");
        ciclista.setNascimento(LocalDate.of(1997,2,22));
        ciclista.setCpf("123.456.789-10");
        ciclista.setPassaporte(new Passaporte("123,", LocalDate.of(2030,10,15), "BR"));
        ciclista.setNacionalidade(Nacionalidade.BRASILEIRO);
        ciclista.setEmail("Lucas@gmail.com");
        ciclista.setUrlFotoDocumento("api.foto/img001");

        return ciclista;
    }
}
