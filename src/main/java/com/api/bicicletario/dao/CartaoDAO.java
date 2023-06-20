package com.api.bicicletario.dao;

import com.api.bicicletario.model.CartaoDeCredito;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
public class CartaoDAO {

    public void alterarCartao(CartaoDeCredito cartaoDeCredito) {
        log.info("Alterando o cartão de crédito: " + cartaoDeCredito.toString());
    }

}
