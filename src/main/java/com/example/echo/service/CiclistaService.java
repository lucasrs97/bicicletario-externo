package com.example.echo.service;

import com.example.echo.enumerator.BrasileiroOuEstrangeiro;
import com.example.echo.enumerator.CiclistaStatus;
import com.example.echo.exception.ValidatorException;
import com.example.echo.model.Ciclista;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class CiclistaService {

    @Autowired
    private CartaoDeCreditoService cartaoDeCreditoService;

    @Autowired
    private EmailService emailService;

    public void cadastrarCiclista(Ciclista ciclista) {
        // [A1] Email já cadastrado ou inválido
        if(!emailService.emailValido(ciclista.getEmail())) {
            throw new ValidatorException("E-mail inválido.");
        }
        // [A3] Cartão reprovado
        if(!cartaoDeCreditoService.cartaoDeCreditoValido(ciclista.getCartaoDeCredito())) {
            throw new ValidatorException("Cartão de crédito recusado.");
        }
        // [A2] Dados inválidos
        List<String> errors = validarDados(ciclista);
        if(!errors.isEmpty()) {
            throw new ValidatorException(errors.toString());
        }

        ciclista.setStatus(CiclistaStatus.AGUARDANDO_CONFIRMACAO);
        try {
            emailService.enviarEmail(ciclista.getEmail(), "Clique no link abaixo para ativar seu cadastro.");
        } catch (Exception e) {
            throw new ValidatorException("Não foi possível enviar o e-mail");
        }
    }

    public void alterarCiclista(Ciclista ciclista) {
        List<String> errors = validarDados(ciclista);
        emailService.enviarEmail(ciclista.getEmail(), "Dados alterados com sucesso.");
    }

    private List<String> validarDados(Ciclista ciclista) {
        List<String> errors = new ArrayList<>();

        if (ciclista.getNome() == null) {
            errors.add("Nome do ciclista não preenchido");
        }
        if(!ciclista.getBrasileiroOuEstrangeiro().equals(BrasileiroOuEstrangeiro.BRASILEIRO)
                || !ciclista.getBrasileiroOuEstrangeiro().equals(BrasileiroOuEstrangeiro.ESTRANGEIRO)) {
            errors.add("Brasileiro ou estrangeiro não preenchido");
        }
        if(ciclista.getCpf() == null) {
            errors.add("CPF não preenchido");
        }
        if(ciclista.getPassaporte() == null) {
            errors.add("Passaporte não preenchido");
        }
        if(ciclista.getSenha() == null) {
            errors.add("Senha não preenchida");
        }
        if(ciclista.fotoEnviada() == null) {
            errors.add("Foto não enviada");
        }

        return errors;
    }

    public Ciclista recuperarCiclista(Long idCiclista) {
        if(idCiclista == null) {
            throw new ValidatorException("Dados Inválidos");
        }

        Ciclista ciclista = new Ciclista();
        ciclista.setId(1L);
        ciclista.setNome("Teste");

        return ciclista;
    }

    public Ciclista ativar(Long idCiclista) {
        Ciclista ciclista = recuperarCiclista(idCiclista);
        if(!registroPendente(ciclista.getStatus())) {
            throw new ValidatorException("Os dados não correspondem a um registro pendente");
        }

        ciclista.setStatus(CiclistaStatus.ATIVO);
        return ciclista;
    }

    private boolean registroPendente(CiclistaStatus status) {
        return true;
    }

}
