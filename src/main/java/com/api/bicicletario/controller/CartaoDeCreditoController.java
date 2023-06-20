package com.api.bicicletario.controller;

import com.api.bicicletario.model.CartaoDeCredito;
import com.api.bicicletario.service.CartaoDeCreditoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static com.api.bicicletario.util.Constantes.DADOS_CARTAO_ALTERADOS_SUCESSO;
import static com.api.bicicletario.util.Constantes.ERRO_ALTERAR_DADOS_CARTAO;

@RestController("/cartaoDeCredito")
public class CartaoDeCreditoController {

    @Autowired
    private CartaoDeCreditoService cartaoDeCreditoService;

    @PutMapping("/{idCiclista}")
    public ResponseEntity<?> alterarCartao(@RequestBody CartaoDeCredito cartaoDeCredito, @PathVariable Long idCiclista) {
        try {
            this.cartaoDeCreditoService.alterar(cartaoDeCredito, idCiclista);
            return new ResponseEntity<>(DADOS_CARTAO_ALTERADOS_SUCESSO, HttpStatus.OK);
        }  catch (IllegalArgumentException e) {
            return new ResponseEntity<>(ERRO_ALTERAR_DADOS_CARTAO, HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

}
