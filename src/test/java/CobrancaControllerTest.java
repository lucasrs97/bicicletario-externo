import com.api.bicicletario.controller.CobrancaController;
import com.api.bicicletario.exception.PagamentoNaoAutorizadoException;
import com.api.bicicletario.model.Cobranca;
import com.api.bicicletario.service.CobrancaService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class CobrancaControllerTest {
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    String data = "31/12/2023";

    @Mock
    private CobrancaService cobrancaService;

    @InjectMocks
    private CobrancaController cobrancaController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void cobrarTaxasAtrasadasPagamentoAutorizadoSucesso() throws PagamentoNaoAutorizadoException {
        Cobranca cobranca1 = new Cobranca(1, "Aguardando pagamento", LocalDateTime.now(), LocalDateTime.now().plusHours(1),50.0, 1, "1234566789");
        Cobranca cobranca2 = new Cobranca(2, "Aguardando pagamento", LocalDateTime.now(), LocalDateTime.now().plusHours(1),50.0, 2, "1234566789");
        List<Cobranca> cobrancasAtrasadas = new ArrayList<>();
        cobrancasAtrasadas.add(cobranca1);
        cobrancasAtrasadas.add(cobranca2);

        when(cobrancaService.obterCobrancasAtrasadas()).thenReturn(cobrancasAtrasadas);

        doNothing().when(cobrancaService).realizarCobranca(cobranca1);
        doNothing().when(cobrancaService).realizarCobranca(cobranca2);
        doNothing().when(cobrancaService).enviarNotificacao(cobranca1);
        doNothing().when(cobrancaService).enviarNotificacao(cobranca2);

        ResponseEntity<String> response = cobrancaController.cobrarTaxasAtrasadas();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Cobranças atrasadas processadas com sucesso.", response.getBody());
        verify(cobrancaService, times(1)).obterCobrancasAtrasadas();
        verify(cobrancaService, times(1)).realizarCobranca(cobranca1);
        verify(cobrancaService, times(1)).realizarCobranca(cobranca2);
        verify(cobrancaService, times(1)).enviarNotificacao(cobranca1);
        verify(cobrancaService, times(1)).enviarNotificacao(cobranca2);
    }

    @Test
    void cobrarTaxasAtrasadasPagamentoNaoAutorizadoInternalServerError() throws PagamentoNaoAutorizadoException {
        Cobranca cobranca1 = new Cobranca(1, "Aguardando pagamento", LocalDateTime.now(), LocalDateTime.now().plusHours(1),50.0, 3, "1234566789");
        List<Cobranca> cobrancasAtrasadas = new ArrayList<>();
        cobrancasAtrasadas.add(cobranca1);

        when(cobrancaService.obterCobrancasAtrasadas()).thenReturn(cobrancasAtrasadas);
        doThrow(new PagamentoNaoAutorizadoException("Erro. Pagamento não autorizado")).when(cobrancaService).realizarCobranca(cobranca1);

        ResponseEntity<String> response = cobrancaController.cobrarTaxasAtrasadas();

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("Erro ao processar pagamento.", response.getBody());
        verify(cobrancaService, times(1)).obterCobrancasAtrasadas();
        verify(cobrancaService, times(1)).realizarCobranca(cobranca1);
        verify(cobrancaService, never()).enviarNotificacao(cobranca1);
    }

    @Test
    void cobrarTaxasAtrasadasPagamentoNaoAutorizadoInternalServerError2() throws PagamentoNaoAutorizadoException {
        Cobranca cobranca1 = new Cobranca(1, "Aguardando pagamento", LocalDateTime.now(), LocalDateTime.now().plusHours(1),50.0, 3, "1234566789");

        List<Cobranca> cobrancasAtrasadas = new ArrayList<>();
        cobrancasAtrasadas.add(cobranca1);

        when(cobrancaService.obterCobrancasAtrasadas()).thenReturn(cobrancasAtrasadas);
        doThrow(new PagamentoNaoAutorizadoException("Erro. Pagamento não autorizado")).when(cobrancaService).realizarCobranca(cobranca1);

        ResponseEntity<String> response = cobrancaController.cobrarTaxasAtrasadas();

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("Erro ao processar pagamento.", response.getBody());
        verify(cobrancaService, times(1)).obterCobrancasAtrasadas();
        verify(cobrancaService, times(1)).realizarCobranca(cobranca1);
        verify(cobrancaService, never()).enviarNotificacao(cobranca1);
    }

    @Test
    void cobrarTaxasAtrasadasSemPagamentosAtrasadosSucesso() throws PagamentoNaoAutorizadoException {
        List<Cobranca> cobrancasAtrasadas = new ArrayList<>();

        when(cobrancaService.obterCobrancasAtrasadas()).thenReturn(cobrancasAtrasadas);

        ResponseEntity<String> response = cobrancaController.cobrarTaxasAtrasadas();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Cobranças atrasadas processadas com sucesso.", response.getBody());
        verify(cobrancaService, times(1)).obterCobrancasAtrasadas();
        verify(cobrancaService, never()).realizarCobranca(any());
        verify(cobrancaService, never()).enviarNotificacao(any());
    }

    @Test
    void cobrarTaxasAtrasadasSemPagamentosAtrasadosSucesso2() throws PagamentoNaoAutorizadoException {
        List<Cobranca> cobrancasAtrasadas = new ArrayList<>();

        when(cobrancaService.obterCobrancasAtrasadas()).thenReturn(cobrancasAtrasadas);

        ResponseEntity<String> response = cobrancaController.cobrarTaxasAtrasadas();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Cobranças atrasadas processadas com sucesso.", response.getBody());
        verify(cobrancaService, times(1)).obterCobrancasAtrasadas();
        verify(cobrancaService, never()).realizarCobranca(any());
        verify(cobrancaService, never()).enviarNotificacao(any());
    }

    @Test
    void cobrarTaxasAtrasadasPagamentoNaoAutorizadoDeveInternalServerError3() throws PagamentoNaoAutorizadoException {
        Cobranca cobranca1 = new Cobranca(1, "Aguardando pagamento", LocalDateTime.now(), LocalDateTime.now().plusHours(1),50.0, 3, "1234566789");

        List<Cobranca> cobrancasAtrasadas = new ArrayList<>();
        cobrancasAtrasadas.add(cobranca1);

        when(cobrancaService.obterCobrancasAtrasadas()).thenReturn(cobrancasAtrasadas);
        doThrow(new PagamentoNaoAutorizadoException("Erro. Pagamento não autorizado")).when(cobrancaService).realizarCobranca(cobranca1);

        ResponseEntity<String> response = cobrancaController.cobrarTaxasAtrasadas();

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("Erro ao processar pagamento.", response.getBody());
        verify(cobrancaService, times(1)).obterCobrancasAtrasadas();
        verify(cobrancaService, times(1)).realizarCobranca(cobranca1);
        verify(cobrancaService, never()).enviarNotificacao(cobranca1);
    }

    @Test
    void cobrarTaxasAtrasadasListaDePagamentosAtrasadosVaziaSucesso() throws PagamentoNaoAutorizadoException {
        when(cobrancaService.obterCobrancasAtrasadas()).thenReturn(new ArrayList<>());

        ResponseEntity<String> response = cobrancaController.cobrarTaxasAtrasadas();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Cobranças atrasadas processadas com sucesso.", response.getBody());
        verify(cobrancaService, times(1)).obterCobrancasAtrasadas();
        verify(cobrancaService, never()).realizarCobranca(any());
        verify(cobrancaService, never()).enviarNotificacao(any());
    }

    @Test
    void cobrarTaxasAtrasadasSemPagamentosAtrasadosSucesso4() throws PagamentoNaoAutorizadoException {
        List<Cobranca> cobrancasAtrasadas = new ArrayList<>();

        when(cobrancaService.obterCobrancasAtrasadas()).thenReturn(cobrancasAtrasadas);

        ResponseEntity<String> response = cobrancaController.cobrarTaxasAtrasadas();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Cobranças atrasadas processadas com sucesso.", response.getBody());
        verify(cobrancaService, times(1)).obterCobrancasAtrasadas();
        verify(cobrancaService, never()).realizarCobranca(any());
        verify(cobrancaService, never()).enviarNotificacao(any());
    }
}
