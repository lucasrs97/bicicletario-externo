package com.api.bicicletario.service;

import com.api.bicicletario.dao.CartaoDAO;
import com.api.bicicletario.dao.CiclistaDAO;
import com.api.bicicletario.model.Ciclista;
import com.api.bicicletario.model.CartaoDeCredito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartaoDeCreditoService {

    @Autowired
    private CiclistaDAO dao;

    @Autowired
    private CartaoDAO cartaoDAO;

    @Autowired
    private EmailService emailService;

    public boolean cartaoDeCreditoInvalido(CartaoDeCredito cartaoDeCredito) {
        return cartaoDeCredito.getNomeTitular() == null
                || cartaoDeCredito.getNumero() == null
                || cartaoDeCredito.getValidade() == null
                || cartaoDeCredito.getCcv() == null;
    }

    public void alterar(CartaoDeCredito cartaoDeCredito, Long idCiclista) {
        Ciclista ciclista = dao.recuperarCiclista(idCiclista);

        // [A2] Cartão reprovado
        if(cartaoDeCreditoInvalido(cartaoDeCredito)) {
            throw new IllegalArgumentException("Cartão de crédito reprovado.");
        }

        cartaoDAO.alterarCartao(cartaoDeCredito);
        emailService.enviarEmail(ciclista.getEmail(), "Dados do cartão alterados com sucesso.");
    }

}
