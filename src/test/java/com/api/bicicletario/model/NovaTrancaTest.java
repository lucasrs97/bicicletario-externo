package com.api.bicicletario.model;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class NovaTrancaTest {

    @Mock
    private NovaTranca novaTranca;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetNumero() {
        when(novaTranca.getNumero()).thenReturn(123);
        int numero = novaTranca.getNumero();
        assertEquals(123, numero);
    }

    @Test
    public void testGetLocalizacao() {
        when(novaTranca.getLocalizacao()).thenReturn("Localização 2");
        String localizacao = novaTranca.getLocalizacao();
        assertEquals("Localização 2", localizacao);
    }

    @Test
    public void testGetAnoDeFabricacao() {
        when(novaTranca.getAnoDeFabricacao()).thenReturn("2022");
        String anoDeFabricacao = novaTranca.getAnoDeFabricacao();
        assertEquals("2022", anoDeFabricacao);
    }

    @Test
    public void testGetModelo() {
        when(novaTranca.getModelo()).thenReturn("ABC123");
        String modelo = novaTranca.getModelo();
        assertEquals("ABC123", modelo);
    }

    @Test
    public void testGetStatus() {
        when(novaTranca.getStatus()).thenReturn(TrancaStatus.LIVRE);
        TrancaStatus status = TrancaStatus.valueOf(String.valueOf(novaTranca.getStatus()));
        assertEquals(TrancaStatus.LIVRE, status);
    }

    @Test
    public void testSetNumero() {
        novaTranca.setNumero(456);
        verify(novaTranca).setNumero(456);
    }

    @Test
    public void testSetLocalizacao() {
        novaTranca.setLocalizacao("Localização 1");
        verify(novaTranca).setLocalizacao("Localização 1");
    }


    @Test
    public void testSetAnoDeFabricacao() {
        novaTranca.setAnoDeFabricacao("2023");
        verify(novaTranca).setAnoDeFabricacao("2023");
    }

    @Test
    public void testSetModelo() {
        novaTranca.setModelo("XYZ789");
        verify(novaTranca).setModelo("XYZ789");
    }

    @Test
    public void testSetStatus() {
        TrancaStatus novoStatus = TrancaStatus.OCUPADA;
        novaTranca.setStatus(novoStatus);
        verify(novaTranca).setStatus(novoStatus);
    }

    @Test
    public void testConstrutorNovaTranca() {
        // Dados de entrada do construtor
        Integer numero = 1;
        String localizacao = "Sala";
        String anoDeFabricacao = "2022";
        String modelo = "ModeloA";
        TrancaStatus status = TrancaStatus.OCUPADA;

        // Criação da instância usando o construtor
        NovaTranca tranca = new NovaTranca(numero, localizacao, anoDeFabricacao, modelo, status);

        // Verificações dos atributos da instância criada
        assertEquals(numero, tranca.getNumero());
        assertEquals(localizacao, tranca.getLocalizacao());
        assertEquals(anoDeFabricacao, tranca.getAnoDeFabricacao());
        assertEquals(modelo, tranca.getModelo());
        assertEquals(status, tranca.getStatus());
    }

    @Test
    public void testGetStatus3() {
        // Dados de entrada do construtor
        Integer numero = 1;
        String localizacao = "Sala";
        String anoDeFabricacao = "2022";
        String modelo = "ModeloA";
        TrancaStatus status = TrancaStatus.OCUPADA;

        // Criação da instância usando o construtor
        NovaTranca tranca = new NovaTranca(numero, localizacao, anoDeFabricacao, modelo, status);

        // Verificação do método getStatus()
        assertEquals(status, tranca.getStatus());
    }

    @Test
    public void testSetStatus3() {
        // Dados de entrada do construtor
        Integer numero = 1;
        String localizacao = "Sala";
        String anoDeFabricacao = "2022";
        String modelo = "ModeloA";
        TrancaStatus status = TrancaStatus.OCUPADA;

        NovaTranca tranca = new NovaTranca(numero, localizacao, anoDeFabricacao, modelo, status);

        TrancaStatus novoStatus = TrancaStatus.OCUPADA;

        tranca.setStatus(novoStatus);

        assertEquals(novoStatus, tranca.getStatus());
    }


}
