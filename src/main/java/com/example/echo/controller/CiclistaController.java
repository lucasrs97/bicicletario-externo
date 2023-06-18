package com.example.echo.controller;

import com.example.echo.controller.responses.ResponseEntityError;
import com.example.echo.exception.ValidatorException;
import com.example.echo.model.Ciclista;
import com.example.echo.service.CiclistaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController("/ciclista")
public class CiclistaController {

    @Autowired
    private CiclistaService ciclistaService;

    @PostMapping("/")
    public ResponseEntity<?> cadastrar(@RequestBody Ciclista ciclista) {
        try {
            this.ciclistaService.cadastrarCiclista(ciclista);
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
    public ResponseEntity<?> alterarDados(@RequestBody Ciclista ciclista) {
        try {
            this.ciclistaService.cadastrarCiclista(ciclista);
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
