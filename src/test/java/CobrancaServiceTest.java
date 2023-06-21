import com.api.bicicletario.exception.PagamentoNaoAutorizadoException;
import com.api.bicicletario.model.CartaoDeCredito;
import com.api.bicicletario.model.Cobranca;
import com.api.bicicletario.service.CobrancaService;
import com.api.bicicletario.service.NotificacaoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class CobrancaServiceTest {
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    String data = "31/12/2023";

    @Mock
    private NotificacaoService notificacaoService;

    @InjectMocks
    private CobrancaService cobrancaService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testObterCobrancasAtrasadas() throws ParseException {
        // Criação de cobranças simuladas
        Cobranca cobrancaAtrasada = null;
        cobrancaAtrasada = new Cobranca(1, "Aguardando pagamento", LocalDateTime.now(), LocalDateTime.now().plusHours(1),50.0, 3, new CartaoDeCredito(1234566789L, "Jô da Silva", "1234 3345 9012 3456", dateFormat.parse(data), "153"));
        cobrancaAtrasada.setStatus("PENDENTE");
        cobrancaAtrasada.setHoraSolicitacao(LocalDateTime.now().minusHours(13));

        Cobranca cobrancaNaoAtrasada = null;
        cobrancaNaoAtrasada = new Cobranca(2, "Aguardando pagamento", LocalDateTime.now(), LocalDateTime.now().plusHours(1),50.0, 2, new CartaoDeCredito(123454564L, "Joana da Silva", "5678 9012 3456 1234", dateFormat.parse(data), "312"));
        cobrancaNaoAtrasada.setStatus("PENDENTE");
        cobrancaNaoAtrasada.setHoraSolicitacao(LocalDateTime.now().minusHours(2));

        // Adição das cobranças à lista simulada
        List<Cobranca> cobrancas = new ArrayList<>();
        cobrancas.add(cobrancaAtrasada);
        cobrancas.add(cobrancaNaoAtrasada);
        cobrancaService.setCobrancas(cobrancas);

        // Chamada do método a ser testado
        List<Cobranca> cobrancasAtrasadas = cobrancaService.obterCobrancasAtrasadas();

        // Verificação do resultado
        assertEquals(1, cobrancasAtrasadas.size());
        assertEquals(cobrancaAtrasada, cobrancasAtrasadas.get(0));
    }

    @Test
    public void testRealizarCobranca_PagamentoAutorizado() throws PagamentoNaoAutorizadoException, ParseException {
        Cobranca cobranca = null;
        cobranca = new Cobranca(1, "Aguardando pagamento", LocalDateTime.now(), LocalDateTime.now().plusHours(1),50.0, 3, new CartaoDeCredito(1234566789L, "Jô da Silva", "1234 3345 9012 3456", dateFormat.parse(data), "153"));
        cobranca.setValor(10.0);
        cobranca.setCartao(new CartaoDeCredito(1234566789L, "Jô da Silva", "1234 3345 9012 3456", dateFormat.parse(data), "153"));

        List<Cobranca> cobrancas = new ArrayList<>();
        cobrancas.add(cobranca);
        cobrancaService.setCobrancas(cobrancas);

        boolean resultado = cobrancaService.processarPagamento(cobranca.getValor(), cobranca.getCartao());

        assertTrue(resultado);
        assertEquals("Aguardando pagamento", cobranca.getStatus());
        assertNotNull(cobranca.getHoraFinalizacao());
        verify(notificacaoService, times(1)).enviarEmail(anyString(), anyString(), anyString());
    }

    @Test
    public void testRealizarCobranca_PagamentoNaoAutorizado() throws ParseException {
        Cobranca cobranca = new Cobranca(1, "Aguardando pagamento", LocalDateTime.now(), LocalDateTime.now().plusHours(1),50.0, 3, new CartaoDeCredito(1234566789L, "Jô da Silva", "1234 3345 9012 3456", dateFormat.parse(data), "153"));
        cobranca.setValor(10.0);

        List<Cobranca> cobrancas = new ArrayList<>();
        cobrancas.add(cobranca);
        cobrancaService.setCobrancas(cobrancas);

        assertThrows(PagamentoNaoAutorizadoException.class, () -> {
            cobrancaService.processarPagamento(20.0, cobranca.getCartao());
        });

        assertEquals("PENDENTE", cobranca.getStatus());
        assertNull(cobranca.getHoraFinalizacao());
        verify(notificacaoService, never()).enviarEmail(anyString(), anyString(), anyString());
    }

    @Test
    public void testEnviarNotificacao() throws ParseException {
        Cobranca cobranca = new Cobranca(1, "Aguardando pagamento", LocalDateTime.now(), LocalDateTime.now().plusHours(1),50.0, 3, new CartaoDeCredito(1234566789L, "Jô da Silva", "1234 3345 9012 3456", dateFormat.parse(data), "153"));
        cobranca.setCiclista(1);
        cobranca.setHoraSolicitacao(LocalDateTime.now());
        cobranca.setValor(50.0);

        cobrancaService.enviarNotificacao(cobranca);

        verify(notificacaoService, times(1)).enviarEmail(anyString(), anyString(), anyString());
    }

    @Test
    public void testProcessarPagamento() {
        double valor = 100.0;
        String cartao = "1234567890";

        assertTrue(cobrancaService.processarPagamento(valor, cartao));
    }
}
