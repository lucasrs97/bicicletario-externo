package com.api.bicicletario.builder;

import com.api.bicicletario.dto.CadastrarCiclistaDTO;
import com.api.bicicletario.enumerator.Nacionalidade;
import com.api.bicicletario.model.CartaoDeCredito;
import com.api.bicicletario.model.Ciclista;
import com.api.bicicletario.vo.Passaporte;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

public class CadastrarCiclistaDTOBuilder {

    public static CadastrarCiclistaDTO build() {
        CadastrarCiclistaDTO cadastro = new CadastrarCiclistaDTO();

        Ciclista ciclista = new Ciclista();
        ciclista.setNome("Lucas");
        ciclista.setCpf("123.456.789-10");
        ciclista.setNacionalidade(Nacionalidade.BRASILEIRO);
        ciclista.setEmail("Lucas@gmail.com");
        ciclista.setUrlFotoDocumento("api.foto/img001");

        CartaoDeCredito cartaoDeCredito = new CartaoDeCredito(1234566789L, "Jô da Silva", "1234 3345 9012 3456", LocalDate.of(2023, 12, 31), "153");
        cartaoDeCredito.setNumero("1234-5678-9012-3456");
        cartaoDeCredito.setNomeTitular("Lucas");
        cartaoDeCredito.setCcv("123");

        String dataNascimento = "22/02/1997";
        String dataValidadePassaporte = "15/10/2030";
        String dataValidadeCartao = "01/08/2032";
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        try {
            ciclista.setNascimento(dateFormat.parse(dataNascimento));
            ciclista.setPassaporte(new Passaporte("123,", dateFormat.parse(dataValidadePassaporte), "BR"));
            cartaoDeCredito.setValidade(LocalDate.parse(dataValidadeCartao));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        cadastro.setCiclista(ciclista);
        cadastro.setMeioDePagamento(cartaoDeCredito);

        return cadastro;
    }

}
