package com.api.bicicletario.service;

import com.api.bicicletario.dao.CartaoDAO;
import com.api.bicicletario.dao.CiclistaDAO;
import com.api.bicicletario.dto.CadastrarCiclistaDTO;
import com.api.bicicletario.enumerator.CiclistaStatus;
import com.api.bicicletario.dto.CiclistaDTO;
import com.api.bicicletario.dto.MeioPagamentoDTO;
import com.api.bicicletario.exception.ValidatorException;
import com.api.bicicletario.model.Ciclista;
import com.api.bicicletario.model.CartaoDeCredito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CiclistaService {

    @Autowired
    private CartaoDeCreditoService cartaoDeCreditoService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private CiclistaDAO dao;

    @Autowired
    private CartaoDAO cartaoDAO;

    public void cadastrarCiclista(CadastrarCiclistaDTO cadastrarCiclistaDTO) {
        Ciclista ciclista = new Ciclista(cadastrarCiclistaDTO.getCiclista());
        CartaoDeCredito cartaoDeCredito = new CartaoDeCredito(cadastrarCiclistaDTO.getMeioDePagamento());

        // [A1] Email já cadastrado ou inválido
        if(!emailService.emailValido(ciclista.getEmail())) {
            throw new ValidatorException("E-mail inválido.");
        }
        // [A3] Cartão reprovado
        if(!cartaoDeCreditoService.cartaoDeCreditoValido(cartaoDeCredito)) {
            throw new ValidatorException("Cartão de crédito recusado.");
        }
        // [A2] Dados inválidos
        List<String> errors = validarDados(ciclista);
        if(!errors.isEmpty()) {
            throw new ValidatorException("Erro ao cadastrar o ciclista.", errors);
        }

        ciclista.setStatus(CiclistaStatus.AGUARDANDO_CONFIRMACAO);

        dao.salvarCiclista(ciclista);
        emailService.enviarEmail(ciclista.getEmail(), "Clique no link abaixo para ativar seu cadastro.");
    }

    private List<String> validarDados(Ciclista ciclista) {
        List<String> errors = new ArrayList<>();

        if (ciclista.getNome() == null) {
            errors.add("Nome do ciclista não preenchido.");
        }
        if(ciclista.getNascimento() == null) {
            errors.add("Data de nascimento não preenchida.");
        }
        if(ciclista.getCpf() == null) {
            errors.add("CPF não preenchido.");
        }
        if(ciclista.getPassaporte() == null) {
            errors.add("Passaporte não preenchido.");
        }
        if(ciclista.getNacionalidade () == null) {
            errors.add("Nacionalidade não preenchida.");
        }
        if(ciclista.getEmail() == null) {
            errors.add("E-mail não preenchido.");
        }
        if(ciclista.getUrlFotoDocumento() == null) {
            errors.add("Foto não enviada.");
        }

        return errors;
    }

    public void alterarCiclista(CiclistaDTO ciclistaDTO) {
        Ciclista ciclista = new Ciclista(ciclistaDTO);

        dao.alterarCiclista(ciclista);
        emailService.enviarEmail(ciclista.getEmail(), "Dados alterados com sucesso.");
    }

    public Ciclista recuperarCiclista(Long idCiclista) {
        if(idCiclista == null) {
            throw new ValidatorException("Dados Inválidos");
        }

        return dao.recuperarCiclista(idCiclista);
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
