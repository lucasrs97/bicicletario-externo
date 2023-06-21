package com.api.bicicletario.controller;
import com.api.bicicletario.model.Tranca;
import com.api.bicicletario.service.TrancaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/trancas")
public class TrancaController {
    private final TrancaService trancaService;

    public TrancaController(TrancaService trancaService) {
        this.trancaService = trancaService;
    }

    @GetMapping
    public List<Tranca> getTrancas() {
        return trancaService.getTrancas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tranca> getTrancaById(@PathVariable Long id) {
        Tranca tranca = trancaService.getTrancaById(Math.toIntExact(id));
        if (tranca != null) {
            return ResponseEntity.ok(tranca);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Tranca> createTranca(@RequestBody Tranca tranca) {
        Tranca createdTranca = trancaService.createTranca(tranca);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTranca);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Tranca> updateTranca(@PathVariable Long id, @RequestBody Tranca tranca) {
        tranca.setId(Math.toIntExact(id));
        Tranca updatedTranca = trancaService.updateTranca(tranca);
        if (updatedTranca != null) {
            return ResponseEntity.ok(updatedTranca);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTranca(@PathVariable Long id) {
        trancaService.deleteTranca(Math.toIntExact(id));
        return ResponseEntity.noContent().build();
    }
}
