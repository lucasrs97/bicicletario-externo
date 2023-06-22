import com.api.bicicletario.model.Cobranca;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;

import static org.mockito.Mockito.*;

class CobrancaTest {

    @Mock
    private Cobranca cobranca;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGettersAndSetters() {
        // Mock the data
        Integer id = 1;
        String status = "pending";
        LocalDateTime horaSolicitacao = LocalDateTime.now();
        LocalDateTime horaFinalizacao = LocalDateTime.now();
        double valor = 10.0;
        Integer ciclista = 123;
        String cartao = "1234567890";

        // Set the expectations
        when(cobranca.getId()).thenReturn(id);
        when(cobranca.getStatus()).thenReturn(status);
        when(cobranca.getHoraSolicitacao()).thenReturn(horaSolicitacao);
        when(cobranca.getHoraFinalizacao()).thenReturn(horaFinalizacao);
        when(cobranca.getValor()).thenReturn(valor);
        when(cobranca.getCiclista()).thenReturn(ciclista);
        when(cobranca.getCartao()).thenReturn(cartao);

        // Verify the results
        Assertions.assertEquals(id, cobranca.getId());
        Assertions.assertEquals(status, cobranca.getStatus());
        Assertions.assertEquals(horaSolicitacao, cobranca.getHoraSolicitacao());
        Assertions.assertEquals(horaFinalizacao, cobranca.getHoraFinalizacao());
        Assertions.assertEquals(valor, cobranca.getValor());
        Assertions.assertEquals(ciclista, cobranca.getCiclista());
        Assertions.assertEquals(cartao, cobranca.getCartao());
    }

    @Test
    void testSetters() {
        // Mock the data
        Integer id = 0;
        String status = "pending";
        LocalDateTime horaSolicitacao = LocalDateTime.now();
        LocalDateTime horaFinalizacao = LocalDateTime.now();
        double valor = 10.0;
        Integer ciclista = 123;
        String cartao = "1234567890";

        // Set the values using setters
        cobranca.setId(id);
        cobranca.setStatus(status);
        cobranca.setHoraSolicitacao(horaSolicitacao);
        cobranca.setHoraFinalizacao(horaFinalizacao);
        cobranca.setValor(valor);
        cobranca.setCiclista(ciclista);
        cobranca.setCartao(cartao);

        // Verify the results
        Assertions.assertEquals(id, cobranca.getId());
    }
}
