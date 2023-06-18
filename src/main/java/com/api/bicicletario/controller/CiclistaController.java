package com.api.bicicletario.controller;

import com.api.bicicletario.dto.CadastrarCiclistaDTO;
import com.api.bicicletario.dto.CiclistaDTO;
import com.api.bicicletario.dto.MeioPagamentoDTO;
import com.api.bicicletario.controller.responses.ResponseEntityError;
import com.api.bicicletario.exception.ValidatorException;
import com.api.bicicletario.model.Ciclista;
import com.api.bicicletario.service.CiclistaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ciclista")
public class CiclistaController {

    @Autowired
    private CiclistaService ciclistaService;

    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> cadastrar(@RequestBody CadastrarCiclistaDTO cadastro) {
        try {
            this.ciclistaService.cadastrarCiclista(cadastro);
            return new ResponseEntity<>("Ciclista cadastrado com sucesso. Aguardando confirmação do e-mail.", HttpStatus.CREATED);
        } catch (ValidatorException e) {
            return new ResponseEntity<ResponseEntityError>(new ResponseEntityError(e.getMessage(), e.getErrors()), HttpStatus.UNPROCESSABLE_ENTITY);
        } catch (Exception e) {
            return new ResponseEntity<>("Erro ao cadastrar o ciclista.", HttpStatus.NOT_FOUND);
        }
    }


    @RequestMapping(method = RequestMethod.POST, value = "/{idCiclista}/ativar", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> confirmarEmail(@PathVariable Long idCiclista) {
        try {
            Ciclista ciclista = this.ciclistaService.ativar(idCiclista);
            return new ResponseEntity<>("E-mail confirmado com sucesso.", HttpStatus.OK);
        } catch (ValidatorException e) {
            return new ResponseEntity<ResponseEntityError>(new ResponseEntityError(e.getMessage(), e.getErrors()), HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> alterar(@RequestBody CiclistaDTO ciclista) {
        try {
            this.ciclistaService.alterarCiclista(ciclista);
            return new ResponseEntity<>("Ciclista alterado com sucesso.", HttpStatus.OK);
        } catch (ValidatorException e) {
            return new ResponseEntity<ResponseEntityError>(new ResponseEntityError(e.getMessage(), e.getErrors()), HttpStatus.UNPROCESSABLE_ENTITY);
        } catch (Exception e) {
            return new ResponseEntity<>("Erro ao alterar o ciclista.", HttpStatus.NOT_FOUND);
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
