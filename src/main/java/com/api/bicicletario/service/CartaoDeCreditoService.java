package com.api.bicicletario.service;

import com.api.bicicletario.dao.CartaoDAO;
import com.api.bicicletario.dao.CiclistaDAO;
import com.api.bicicletario.exception.ValidatorException;
import com.api.bicicletario.model.Ciclista;
import com.api.bicicletario.model.CartaoDeCredito;
import com.api.bicicletario.dto.MeioPagamentoDTO;
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

    public boolean cartaoDeCreditoValido(CartaoDeCredito cartaoDeCredito) {
        return true;
    }

    public void alterar(MeioPagamentoDTO meioPagamentoDTO, Long idCiclista) {
        Ciclista ciclista = dao.recuperarCiclista(idCiclista);
        CartaoDeCredito cartaoDeCredito = new CartaoDeCredito(meioPagamentoDTO);

        // [A2] Cartão reprovado
        if(!cartaoDeCreditoValido(cartaoDeCredito)) {
            throw new ValidatorException("Cartão de crédito reprovado.");
        }

        cartaoDAO.alterarCartao(cartaoDeCredito);
        emailService.enviarEmail(ciclista.getEmail(), "Dados do cartão alterados com sucesso.");
    }

}
