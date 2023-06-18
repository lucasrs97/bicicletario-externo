package com.api.bicicletario.controller;

import com.api.bicicletario.dto.CiclistaDTO;
import com.api.bicicletario.dto.MeioPagamentoDTO;
import com.api.bicicletario.controller.responses.ResponseEntityError;
import com.api.bicicletario.exception.ValidatorException;
import com.api.bicicletario.model.Ciclista;
import com.api.bicicletario.service.CiclistaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController("/ciclista")
public class CiclistaController {

    @Autowired
    private CiclistaService ciclistaService;

    @PostMapping("/")
    public ResponseEntity<?> cadastrar(@RequestBody CiclistaDTO ciclistaDTO, MeioPagamentoDTO meioPagamentoDTO) {
        try {
            this.ciclistaService.cadastrarCiclista(ciclistaDTO, meioPagamentoDTO);
            return new ResponseEntity<>("E-mail enviado. Aguardando confirmação.", HttpStatus.CREATED);
        } catch (ValidatorException e) {
            return new ResponseEntity<ResponseEntityError>(new ResponseEntityError(e.getMessage(), e.getErrors()), HttpStatus.UNPROCESSABLE_ENTITY);
        } catch (Exception e) {
            return new ResponseEntity<>("Erro ao cadastrar ciclista.", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/{idCiclista}/ativar")
    public ResponseEntity<?> confirmarEmail(@PathVariable Long idCiclista) {
        try {
            Ciclista ciclista = this.ciclistaService.ativar(idCiclista);
            return new ResponseEntity<>("Cadastro completo!", HttpStatus.OK);
        } catch (ValidatorException e) {
            return new ResponseEntity<ResponseEntityError>(new ResponseEntityError(e.getMessage(), e.getErrors()), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/")
    public ResponseEntity<?> alterar(@RequestBody CiclistaDTO ciclistaDTO) {
        try {
            this.ciclistaService.alterarCiclista(ciclistaDTO);
            return new ResponseEntity<>("E-mail enviado. Aguardando confirmação.", HttpStatus.OK);
        } catch (ValidatorException e) {
            return new ResponseEntity<ResponseEntityError>(new ResponseEntityError(e.getMessage(), e.getErrors()), HttpStatus.UNPROCESSABLE_ENTITY);
        } catch (Exception e) {
            return new ResponseEntity<>("Erro ao cadastrar ciclista.", HttpStatus.NOT_FOUND);
        }
    }




























    @GetMapping("/")
    public ResponseEntity<?> recuperar(@PathVariable Long idCiclista) {
        try {
            Ciclista ciclista = this.ciclistaService.recuperarCiclista(idCiclista);
            return new ResponseEntity<>(ciclista, HttpStatus.OK);
        } catch (ValidatorException e) {
            return new ResponseEntity<ResponseEntityError>(new ResponseEntityError(e.getMessage(), e.getErrors()), HttpStatus.BAD_REQUEST);
        }
    }



}
