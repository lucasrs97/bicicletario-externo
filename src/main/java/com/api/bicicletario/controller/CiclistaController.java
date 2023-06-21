package com.api.bicicletario.controller;

import com.api.bicicletario.dto.CadastrarCiclistaDTO;
import com.api.bicicletario.model.Ciclista;
import com.api.bicicletario.service.CiclistaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.api.bicicletario.util.Constantes.CICLISTA_ALTERADO_SUCESSO;
import static com.api.bicicletario.util.Constantes.CICLISTA_CADASTRADO_SUCESSO;
import static com.api.bicicletario.util.Constantes.EMAIL_CONFIRMADO_SUCESSO;
import static com.api.bicicletario.util.Constantes.ERRO_ALTERAR_CICLISTA;
import static com.api.bicicletario.util.Constantes.ERRO_ATIVAR_CICLISATA;
import static com.api.bicicletario.util.Constantes.ERRO_CADASTRAR_CICLISTA;
import static com.api.bicicletario.util.Constantes.ERRO_RECUPERAR_CICLISTA;

@RestController
@RequestMapping("/ciclista")
public class CiclistaController {

    @Autowired
    private CiclistaService ciclistaService;

    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> cadastrar(@RequestBody CadastrarCiclistaDTO cadastro) {
        try {
            this.ciclistaService.cadastrarCiclista(cadastro);
            return new ResponseEntity<>(CICLISTA_CADASTRADO_SUCESSO, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(ERRO_CADASTRAR_CICLISTA, HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @RequestMapping(method = RequestMethod.POST, value = "/{idCiclista}/ativar", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> confirmarEmail(@PathVariable Long idCiclista) {
        try {
            this.ciclistaService.ativarCiclista(idCiclista);
            return new ResponseEntity<>(EMAIL_CONFIRMADO_SUCESSO, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(ERRO_ATIVAR_CICLISATA, HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @RequestMapping(method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> alterar(@RequestBody Ciclista ciclista) {
        try {
            this.ciclistaService.alterarCiclista(ciclista);
            return new ResponseEntity<>(CICLISTA_ALTERADO_SUCESSO, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(ERRO_ALTERAR_CICLISTA, HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{idCiclista}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> recuperar(@PathVariable Long idCiclista) {
        try {
            Ciclista ciclista = this.ciclistaService.recuperarCiclista(idCiclista);
            return new ResponseEntity<>(ciclista, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(ERRO_RECUPERAR_CICLISTA, HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }



}
