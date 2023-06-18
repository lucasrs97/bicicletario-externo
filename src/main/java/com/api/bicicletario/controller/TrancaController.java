package com.api.bicicletario.controller;

import com.api.bicicletario.model.NovaTranca;
import com.api.bicicletario.model.Tranca;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/tranca")
public class TrancaController {

    private final List<Tranca> trancas = new ArrayList<>();
    private int nextId = 1;

    @GetMapping
    public ResponseEntity<List<Tranca>> listarTrancas() {
        return ResponseEntity.ok(trancas);
    }

    @PostMapping
    public ResponseEntity<String> cadastrarTranca(@RequestBody NovaTranca novaTranca) {
        Tranca tranca = new Tranca();
        tranca.setId(nextId++);
        tranca.setNumero(novaTranca.getNumero());
        tranca.setLocalizacao(novaTranca.getLocalizacao());
        tranca.setAnoDeFabricacao(novaTranca.getAnoDeFabricacao());
        tranca.setModelo(novaTranca.getModelo());
        tranca.setStatus(novaTranca.getStatus());

        trancas.add(tranca);

        return ResponseEntity.ok("Tranca cadastrada com sucesso!");
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tranca> buscarTranca(@PathVariable("id") int id) {
        Tranca tranca = encontrarTranca(id);
        if (tranca == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(tranca);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> atualizarTranca(@PathVariable("id") int id, @RequestBody NovaTranca novaTranca) {
        Tranca tranca = encontrarTranca(id);
        if (tranca == null) {
            return ResponseEntity.notFound().build();
        }

        tranca.setNumero(novaTranca.getNumero());
        tranca.setLocalizacao(novaTranca.getLocalizacao());
        tranca.setAnoDeFabricacao(novaTranca.getAnoDeFabricacao());
        tranca.setModelo(novaTranca.getModelo());
        tranca.setStatus(novaTranca.getStatus());

        return ResponseEntity.ok("Tranca atualizada com sucesso!");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> excluirTranca(@PathVariable("id") int id) {
        Tranca tranca = encontrarTranca(id);
        if (tranca == null) {
            return ResponseEntity.notFound().build();
        }

        if (tranca.getBicicleta() != null) {
            return ResponseEntity.badRequest().body("A tranca não pode ser excluída enquanto estiver associada a uma bicicleta!");
        }

        trancas.remove(tranca);
        return ResponseEntity.ok("Tranca excluída com sucesso!");
    }

    private Tranca encontrarTranca(int id) {
        for (Tranca tranca : trancas) {
            if (tranca.getId() == id) {
                return tranca;
            }
        }
        return null;
    }
}
