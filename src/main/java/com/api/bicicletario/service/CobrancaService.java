package com.api.bicicletario.service;

import com.api.bicicletario.exception.PagamentoNaoAutorizadoException;
import com.api.bicicletario.model.Cobranca;

import org.codehaus.plexus.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;

import java.util.List;
import lombok.extern.slf4j.Slf4j;

@Service

@Slf4j
public class CobrancaService {

    @Autowired
    private NotificacaoService notificacaoService;

    private List<Cobranca> cobrancas;

    public CobrancaService() {
        cobrancas = new ArrayList<>();
    }

    public void setCobrancas(List<Cobranca> cobrancas) {
        this.cobrancas = cobrancas;
    }

    public Cobranca obterCobranca(Integer id) {
        for (Cobranca cobranca : cobrancas) {
            if (cobranca.getId().equals(id)) {
                return cobranca;
            }
        }

        return null;
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

    public void adicionarCobrancaEmFila(Cobranca cobranca) {
        this.cobrancas.add(cobranca);
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

    public boolean processarPagamento(double valor, String cartao) {
        if (StringUtils.isBlank(cartao)) {
            return false;
        }
        String resultado = "Processando pagamento...\n" + "Valor: " + valor + "\nCartão: " + cartao + "\nPagamento autorizado!";
        log.info(resultado);

        return true;
    }

    protected String criarMensagemNotificacao(Cobranca cobranca) {
        return "Caro(a) Ciclista " + cobranca.getCiclista() + ",\n\n" +
                "De acordo com nossos registros, identificamos uma cobrança em atraso para a devolução da bicicleta.\n" +
                "Data da cobrança: " + cobranca.getHoraSolicitacao() + "\n" +
                "Valor da cobrança: " + cobranca.getValor() + "\n\n" +
                "Atenciosamente,\n" +
                "Equipe do sistema de aluguel de bicicletas";
    }

    public void enviarNotificacao(Cobranca cobranca) {
        String mensagem = criarMensagemNotificacao(cobranca);
        notificacaoService.enviarEmail(cobranca.getCiclista().toString(), "Cobrança em atraso", mensagem);
    }

    public boolean validarCartao(String numeroCartao) {
        // Remove espaços em branco e caracteres não numéricos
        String numeroLimpo = numeroCartao.replaceAll("\\s+", "");

        // Verifica se o número tem apenas dígitos
        if (!numeroLimpo.matches("\\d+")) {
            return false;
        }

        int soma = 0;
        boolean alternar = false;

        // Percorre o cartão da direita para a esquerda
        for (int i = numeroLimpo.length() - 1; i >= 0; i--) {
            int digito = Character.getNumericValue(numeroLimpo.charAt(i));

            if (alternar) {
                digito *= 2;
                if (digito > 9) {
                    digito -= 9;
                }
            }

            soma += digito;
            alternar = !alternar;
        }

        // Verifica se total é divisível por 10
        return soma % 10 == 0;
    }
}
