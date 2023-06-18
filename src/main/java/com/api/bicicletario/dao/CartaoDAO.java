package com.api.bicicletario.dao;

import com.api.bicicletario.model.CartaoDeCredito;
import org.springframework.stereotype.Repository;

@Repository
public class CartaoDAO {

    public void salvarCartao(CartaoDeCredito cartaoDeCredito) {
        System.out.println("Salvando o cartão de crédito: " + cartaoDeCredito.toString());
    }
    public void alterarCartao(CartaoDeCredito cartaoDeCredito) {
        System.out.println("Alterando o cartão de crédito: " + cartaoDeCredito.toString());
    }

}
