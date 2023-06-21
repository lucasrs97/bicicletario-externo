package com.api.bicicletario.controller;

import com.api.bicicletario.exception.PagamentoNaoAutorizadoException;
import com.api.bicicletario.model.Cobranca;
import com.api.bicicletario.service.CobrancaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/cobranca")
public class CobrancaController {

    @Autowired
    private CobrancaService cobrancaService;

    @PostMapping("/atrasadas")
    public ResponseEntity<String> cobrarTaxasAtrasadas() {
        // Obtém a lista de cobranças em atraso
        List<Cobranca> cobrancasAtrasadas = cobrancaService.obterCobrancasAtrasadas();

        // Realiza a cobrança para cada ciclista em atraso
        for (Cobranca cobranca : cobrancasAtrasadas) {
            try {
                cobrancaService.realizarCobranca(cobranca);
                cobrancaService.enviarNotificacao(cobranca);
            } catch (PagamentoNaoAutorizadoException e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao processar pagamento.");
            }
        }

        return ResponseEntity.ok("Cobranças atrasadas processadas com sucesso.");
    }
}
