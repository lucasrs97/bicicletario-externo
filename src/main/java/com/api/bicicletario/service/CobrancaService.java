package com.api.bicicletario.service;

import com.api.bicicletario.exception.PagamentoNaoAutorizadoException;
import com.api.bicicletario.model.Cobranca;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class CobrancaService {

    private List<Cobranca> cobrancas; // Lista simulada de cobranças

    @Autowired
    private NotificacaoService notificacaoService;

    public CobrancaService() {
        cobrancas = new ArrayList<>();
    }

    public List<Cobranca> obterCobrancasAtrasadas() {
        LocalDateTime ultimaVerificacao = LocalDateTime.now().minusHours(12);
        List<Cobranca> cobrancasAtrasadas = new ArrayList<>();
        for (Cobranca cobranca : cobrancas) {
            if (cobranca.getStatus().equals("PENDENTE") && cobranca.getHoraSolicitacao().isBefore(ultimaVerificacao)) {
                cobrancasAtrasadas.add(cobranca);
            }
        }
        return cobrancasAtrasadas;
    }

    public void realizarCobranca(Cobranca cobranca) throws PagamentoNaoAutorizadoException {
        boolean pagamentoAutorizado = processarPagamento(cobranca.getValor(), cobranca.getCartao());
        if (!pagamentoAutorizado) {
            throw new PagamentoNaoAutorizadoException("Pagamento não autorizado para cobrança: " + cobranca.getId());
        }
        cobranca.setStatus("PAGA");
        cobranca.setHoraFinalizacao(LocalDateTime.now());
        // Atualizar a cobrança na lista
        for (int i = 0; i < cobrancas.size(); i++) {
            if (cobrancas.get(i).getId().equals(cobranca.getId())) {
                cobrancas.set(i, cobranca);
                break;
            }
        }
    }

    public void enviarNotificacao(Cobranca cobranca) {
        String mensagem = criarMensagemNotificacao(cobranca);
        notificacaoService.enviarEmail(cobranca.getCiclista().toString(), "Cobrança em atraso", mensagem);
    }

    public boolean processarPagamento(double valor, String cartao) {
        String resultado = "Processando pagamento...\n" + "Valor: " + valor + "\nCartão: " + cartao + "\nPagamento autorizado!";

        boolean pagamentoAutorizado = true;

        System.out.println(resultado);
        return pagamentoAutorizado;
    }


    protected String criarMensagemNotificacao(Cobranca cobranca) {
        return "Caro(a) Ciclista " + cobranca.getCiclista() + ",\n\n" +
                "De acordo com nossos registros, identificamos uma cobrança em atraso para a devolução da bicicleta.\n" +
                "Data da cobrança: " + cobranca.getHoraSolicitacao() + "\n" +
                "Valor da cobrança: " + cobranca.getValor() + "\n\n" +
                "Atenciosamente,\n" +
                "Equipe do sistema de aluguel de bicicletas";
    }

    public void setCobrancas(List<Cobranca> cobrancas) {
        this.cobrancas = cobrancas;
    }
}
