package com.api.bicicletario.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CiclistaTest {

    @Test
    void testa_construtor_e_gets() {
        Long id = 1L;
        CiclistaStatus status = CiclistaStatus.ATIVO;
        String nome = "Lucas";
        Date nascimento = new Date();
        String cpf = "1234567890";
        Passaporte passaporte = new Passaporte("12345", new Date(), "BR");
        Nacionalidade nacionalidade = Nacionalidade.BRASILEIRO;
        String email = "lucas@email.com";
        String urlFotoDocumento = "https://urlfoto.com/document.jpg";

        Ciclista ciclista = new Ciclista(id, status, nome, nascimento, cpf, passaporte, nacionalidade, email, urlFotoDocumento);

        assertEquals(id, ciclista.getId());
        assertEquals(status, ciclista.getStatus());
        assertEquals(nome, ciclista.getNome());
        assertEquals(nascimento, ciclista.getNascimento());
        assertEquals(cpf, ciclista.getCpf());
        assertEquals(passaporte, ciclista.getPassaporte());
        assertEquals(nacionalidade, ciclista.getNacionalidade());
        assertEquals(email, ciclista.getEmail());
        assertEquals(urlFotoDocumento, ciclista.getUrlFotoDocumento());
    }

    @Test
    void testa_sets() {
        Ciclista ciclista = new Ciclista();

        Long id = 2L;
        ciclista.setId(id);
        assertEquals(id, ciclista.getId());

        CiclistaStatus status = CiclistaStatus.ATIVO;
        ciclista.setStatus(status);
        assertEquals(status, ciclista.getStatus());

        String nome = "Lucas";
        ciclista.setNome(nome);
        assertEquals(nome, ciclista.getNome());

        Date nascimento = new Date();
        ciclista.setNascimento(nascimento);
        assertEquals(nascimento, ciclista.getNascimento());

        String cpf = "1234567890";
        ciclista.setCpf(cpf);
        assertEquals(cpf, ciclista.getCpf());

        Passaporte passaporte = new Passaporte("1234567890", new Date(), "BR");
        ciclista.setPassaporte(passaporte);
        assertEquals(passaporte, ciclista.getPassaporte());

        Nacionalidade nacionalidade = Nacionalidade.BRASILEIRO;
        ciclista.setNacionalidade(nacionalidade);
        assertEquals(nacionalidade, ciclista.getNacionalidade());

        String email = "lucas@email.com";
        ciclista.setEmail(email);
        assertEquals(email, ciclista.getEmail());

        String urlFotoDocumento = "https://urlfoto.com/document.jpg";
        ciclista.setUrlFotoDocumento(urlFotoDocumento);
        assertEquals(urlFotoDocumento, ciclista.getUrlFotoDocumento());
    }

}
