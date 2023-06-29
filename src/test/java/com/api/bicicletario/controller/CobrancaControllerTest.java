package com.api.bicicletario.controller;

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

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class CobrancaControllerTest {
    @Mock
    private CobrancaService cobrancaService;

    @InjectMocks
    private CobrancaController cobrancaController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void taxasAtrasadasPagamentoAutorizadoSucesso() throws PagamentoNaoAutorizadoException {
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
    void taxasAtrasadasPagamentoNaoAutorizadoInternalServerError() throws PagamentoNaoAutorizadoException {
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
    void taxasAtrasadasSemPagamentosAtrasadosSucesso() throws PagamentoNaoAutorizadoException {
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
    void taxasAtrasadasListaDePagamentosAtrasadosVaziaSucesso() throws PagamentoNaoAutorizadoException {
        when(cobrancaService.obterCobrancasAtrasadas()).thenReturn(new ArrayList<>());

        ResponseEntity<String> response = cobrancaController.cobrarTaxasAtrasadas();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Cobranças atrasadas processadas com sucesso.", response.getBody());
        verify(cobrancaService, times(1)).obterCobrancasAtrasadas();
        verify(cobrancaService, never()).realizarCobranca(any());
        verify(cobrancaService, never()).enviarNotificacao(any());
    }

    @Test
    public void testIncluirCobrancaFila_WithValidData_ReturnsOk() {
        Cobranca novaCobranca = new Cobranca();
        novaCobranca.setCiclista(1);
        novaCobranca.setValor(100.0);

        doNothing().when(cobrancaService).adicionarCobrancaEmFila(novaCobranca);

        ResponseEntity<String> response = cobrancaController.incluirCobrancaFila(novaCobranca);

        verify(cobrancaService, times(1)).adicionarCobrancaEmFila(novaCobranca);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Cobrança adicionada com sucesso.", response.getBody());
    }

    @Test
    public void testIncluirCobrancaFila_WithInvalidData_ReturnsUnprocessableEntity() {
        Cobranca novaCobranca = new Cobranca();

        ResponseEntity<String> response = cobrancaController.incluirCobrancaFila(novaCobranca);

        verify(cobrancaService, never()).adicionarCobrancaEmFila(novaCobranca);

        assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, response.getStatusCode());
        assertEquals("Dados inválidos", response.getBody());
    }

    @Test
    void testObterCobranca_Existente() {
        // Mock do objeto Cobranca
        Cobranca cobrancaMock = new Cobranca();
        cobrancaMock.setId(1);
        cobrancaMock.setValor(100.0);

        // Mock do serviço cobrancaService.obterCobranca()
        when(cobrancaService.obterCobranca(1)).thenReturn(cobrancaMock);

        // Chama o método do controlador
        ResponseEntity<Cobranca> response = cobrancaController.obterCobranca(1);

        // Verifica se o status da resposta é OK (200)
        assertEquals(HttpStatus.OK, response.getStatusCode());

        // Verifica se a cobranca retornada é a mesma do mock
        assertEquals(cobrancaMock, response.getBody());
    }

    @Test
    void testObterCobranca_Inexistente() {
        // Mock do serviço cobrancaService.obterCobranca()
        when(cobrancaService.obterCobranca(1)).thenReturn(null);

        // Chama o método do controlador
        ResponseEntity<Cobranca> response = cobrancaController.obterCobranca(1);

        // Verifica se o status da resposta é NOT_FOUND (404)
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}
