package com.api.bicicletario.controller;

import com.api.bicicletario.dto.MeioPagamentoDTO;
import com.api.bicicletario.exception.ValidatorException;
import com.api.bicicletario.service.CartaoDeCreditoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/cartaoDeCredito")
public class CartaoDeCreditoController {

    @Autowired
    private CartaoDeCreditoService cartaoDeCreditoService;

    @PutMapping("/{idCiclista}")
    public ResponseEntity<?> alterar(@RequestBody MeioPagamentoDTO meioPagamentoDTO, @PathVariable String idCiclista) {
        try {
            this.cartaoDeCreditoService.alterar(meioPagamentoDTO, Long.valueOf(idCiclista));
            return new ResponseEntity<>("Dados do cartão: " + meioPagamentoDTO.getNumero() + " alterados com sucesso", HttpStatus.OK);
        } catch (ValidatorException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

}
