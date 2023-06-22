package com.api.bicicletario.controller;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;



class TrancaControllerTest {
    @Mock
    private TrancaService trancaService;

    @InjectMocks
    private TrancaController trancaController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetTrancas() {
        List<Tranca> trancas = new ArrayList<>();
        trancas.add(new Tranca(1, "Bicicleta 1", 1, "Localização 1", "2022", "Modelo 1", TrancaStatus.LIVRE));
        when(trancaService.getTrancas()).thenReturn(trancas);

        List<Tranca> result = trancaController.getTrancas();

        assertEquals(trancas, result);
        verify(trancaService, times(1)).getTrancas();
    }

    @Test
    void testGetTrancaById_ExistingId() {
        long trancaId = 1;
        Tranca tranca = new Tranca(1, "Bicicleta 1", 1, "Localização 1", "2022", "Modelo 1", TrancaStatus.LIVRE);
        when(trancaService.getTrancaById(1)).thenReturn(tranca);

        ResponseEntity<Tranca> response = trancaController.getTrancaById(trancaId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(tranca, response.getBody());
        verify(trancaService, times(1)).getTrancaById(1);
    }

    @Test
    void testGetTrancaById_NonExistingId() {
        long trancaId = 2;
        when(trancaService.getTrancaById(2)).thenReturn(null);

        ResponseEntity<Tranca> response = trancaController.getTrancaById(trancaId);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(trancaService, times(1)).getTrancaById(2);
    }

    @Test
    void testCreateTranca() {
        Tranca tranca = new Tranca(1, "Bicicleta 1", 1, "Localização 1", "2022", "Modelo 1", TrancaStatus.LIVRE);
        when(trancaService.createTranca(tranca)).thenReturn(tranca);

        ResponseEntity<Tranca> response = trancaController.createTranca(tranca);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(tranca, response.getBody());
        verify(trancaService, times(1)).createTranca(tranca);
    }

    @Test
    void testUpdateTranca_ExistingId() {
        long trancaId = 1;
        Tranca tranca = new Tranca(1, "Bicicleta 1", 1, "Localização 1", "2022", "Modelo 1", TrancaStatus.LIVRE);
        when(trancaService.updateTranca(tranca)).thenReturn(tranca);

        ResponseEntity<Tranca> response = trancaController.updateTranca(trancaId, tranca);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(tranca, response.getBody());
        verify(trancaService, times(1)).updateTranca(tranca);
    }

    @Test
    void testUpdateTranca_NonExistingId() {
        long trancaId = 2;
        Tranca tranca = new Tranca(2, "Bicicleta 2", 2, "Localização 2", "2021", "Modelo 2", TrancaStatus.OCUPADA);
        when(trancaService.updateTranca(tranca)).thenReturn(null);

        ResponseEntity<Tranca> response = trancaController.updateTranca(trancaId, tranca);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(trancaService, times(1)).updateTranca(tranca);
    }

    @Test
    void testDeleteTranca_ExistingId() {
        long trancaId = 1;

        ResponseEntity<Void> response = trancaController.deleteTranca(trancaId);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(trancaService, times(1)).deleteTranca(1);
    }

    @Test
    void testDeleteTranca_NonExistingId() {
        long trancaId = 2;
        doNothing().when(trancaService).deleteTranca(2);

        ResponseEntity<Void> response = trancaController.deleteTranca(trancaId);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(trancaService, times(1)).deleteTranca(2);
    }
    @Test
    void testGetTrancas_EmptyList() {
        List<Tranca> trancas = new ArrayList<>();
        when(trancaService.getTrancas()).thenReturn(trancas);

        List<Tranca> result = trancaController.getTrancas();

        assertEquals(trancas, result);
        verify(trancaService, times(1)).getTrancas();
    }

    @Test
    void testUpdateTranca_NonExistingId2() {
        long trancaId = 2;
        Tranca tranca = new Tranca(2, "Bicicleta 2", 2, "Localização 2", "2021", "Modelo 2", TrancaStatus.OCUPADA);
        when(trancaService.updateTranca(tranca)).thenReturn(null);

        ResponseEntity<Tranca> response = trancaController.updateTranca(trancaId, tranca);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
        verify(trancaService, times(1)).updateTranca(tranca);
    }

    @Test
    void testDeleteTranca_ExistingId2() {
        long trancaId = 1;

        ResponseEntity<Void> response = trancaController.deleteTranca(trancaId);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(trancaService, times(1)).deleteTranca(1);
    }

    @Test
    void testDeleteTranca_NonExistingId2() {
        long trancaId = 2;
        doNothing().when(trancaService).deleteTranca(2);

        ResponseEntity<Void> response = trancaController.deleteTranca(trancaId);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(trancaService, times(1)).deleteTranca(2);
    }

}
