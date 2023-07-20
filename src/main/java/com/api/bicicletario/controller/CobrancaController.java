package com.api.bicicletario.controller;

import com.api.bicicletario.exception.PagamentoNaoAutorizadoException;
import com.api.bicicletario.model.Cobranca;
import com.api.bicicletario.service.CobrancaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/filaCobranca")
    public ResponseEntity<String> incluirCobrancaFila(Cobranca novaCobranca) {
        if (novaCobranca.getCiclista() == null || novaCobranca.getValor() == null) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body("Dados inválidos");
        }

        cobrancaService.adicionarCobrancaEmFila(novaCobranca);

        return ResponseEntity.ok("Cobrança adicionada com sucesso.");
    }

    @GetMapping("/{idCobranca}")
    public ResponseEntity<Cobranca> obterCobranca(@PathVariable("idCobranca") Integer idCobranca) {
        Cobranca cobranca = cobrancaService.obterCobranca(idCobranca);

        if (cobranca != null) {
            return ResponseEntity.ok(cobranca);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping("/validaCartaoDeCredito")
    public ResponseEntity<String> validarCartaoDeCredito(@RequestBody String cartao) {
        boolean cartaoValido =  cobrancaService.validarCartao(cartao);

        if (cartaoValido) {
            return ResponseEntity.ok("Cartão validado com sucesso");
        } else {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body("Dados do cartão inválidos");
        }
    }
}
