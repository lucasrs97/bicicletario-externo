import com.api.bicicletario.enumerator.TrancaStatus;
import com.api.bicicletario.model.Tranca;
import com.api.bicicletario.service.TrancaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class TrancaServiceTest {

    private TrancaService trancaService;
    private List<Tranca> trancas;

    @BeforeEach
    public void setUp() {
        trancas = new ArrayList<>();
        trancas.add(new Tranca(1, "Bicicleta 1", 1, "Localização 1", "2022", "Modelo 1", TrancaStatus.LIVRE));
        trancas.add(new Tranca(2, "Bicicleta 2", 2, "Localização 2", "2021", "Modelo 2", TrancaStatus.OCUPADA));
        trancas.add(new Tranca(3, "Bicicleta 3", 3, "Localização 3", "2020", "Modelo 3", TrancaStatus.NOVA));
        trancas.add(new Tranca(4, "Bicicleta 4", 4, "Localização 4", "2019", "Modelo 4", TrancaStatus.APOSENTADA));
        trancas.add(new Tranca(5, "Bicicleta 5", 5, "Localização 5", "2023", "Modelo 5", TrancaStatus.EM_REPARO));
        trancas.add(new Tranca(6, "Bicicleta 6", 6, "Localização 6", "2022", "Modelo 6", TrancaStatus.LIVRE));
        trancas.add(new Tranca(7, "Bicicleta 7", 7, "Localização 7", "2021", "Modelo 7", TrancaStatus.OCUPADA));
        trancas.add(new Tranca(8, "Bicicleta 8", 8, "Localização 8", "2020", "Modelo 8", TrancaStatus.NOVA));
        trancas.add(new Tranca(9, "Bicicleta 9", 9, "Localização 9", "2019", "Modelo 9", TrancaStatus.APOSENTADA));
        trancas.add(new Tranca(10, "Bicicleta 10", 10, "Localização 10", "2023", "Modelo 10", TrancaStatus.EM_REPARO));
        trancas.add(new Tranca(11, "Bicicleta 11", 11, "Localização 11", "2022", "Modelo 11", TrancaStatus.LIVRE));
        trancas.add(new Tranca(12, "Bicicleta 12", 12, "Localização 12", "2021", "Modelo 12", TrancaStatus.OCUPADA));
        trancas.add(new Tranca(13, "Bicicleta 13", 13, "Localização 13", "2020", "Modelo 13", TrancaStatus.NOVA));
        trancas.add(new Tranca(14, "Bicicleta 14", 14, "Localização 14", "2019", "Modelo 14", TrancaStatus.APOSENTADA));
        trancas.add(new Tranca(15, "Bicicleta 15", 15, "Localização 15", "2023", "Modelo 15", TrancaStatus.EM_REPARO));

        trancaService = new TrancaService(trancas);
    }

    @Test
    public void testGetTrancas() {
        List<Tranca> result = trancaService.getTrancas();

        assertEquals(trancas, result);
    }

    @Test
    public void testGetTrancaById() {
        int trancaId = 1;
        Tranca expectedTranca = new Tranca(1, "Bicicleta 1", 1, "Localização 1", "2022", "Modelo 1", TrancaStatus.LIVRE);

        Tranca result = trancaService.getTrancaById(trancaId);

        assertEquals(expectedTranca.getId(), result.getId());
        assertEquals(expectedTranca.getBicicleta(), result.getBicicleta());
        assertEquals(expectedTranca.getNumero(), result.getNumero());
        assertEquals(expectedTranca.getLocalizacao(), result.getLocalizacao());
        assertEquals(expectedTranca.getAnoDeFabricacao(), result.getAnoDeFabricacao());
        assertEquals(expectedTranca.getModelo(), result.getModelo());
        assertEquals(expectedTranca.getStatus(), result.getStatus());
    }

    @Test
    public void testCreateTranca() {
        Tranca tranca = new Tranca(16, "Bicicleta 16", 16, "Localização 16", "2023", "Modelo 16", TrancaStatus.NOVA);

        Tranca result = trancaService.createTranca(tranca);

        assertEquals(tranca, result);
        assertEquals(16, trancaService.getTrancas().size());
    }

    @Test
    public void testUpdateTranca() {
        Tranca tranca = new Tranca(1, "Bicicleta 1", 1, "Localização 1 Atualizada", "2022", "Modelo 1", TrancaStatus.APOSENTADA);

        Tranca result = trancaService.updateTranca(tranca);

        assertEquals(tranca, result);
        assertEquals("Localização 1 Atualizada", trancaService.getTrancaById(1).getLocalizacao());
        assertEquals(TrancaStatus.APOSENTADA, trancaService.getTrancaById(1).getStatus());
    }

    @Test
    public void testDeleteTranca() {
        int trancaId = 1;

        trancaService.deleteTranca(trancaId);

        List<Tranca> remainingTrancas = trancaService.getTrancas();
        assertEquals(14, remainingTrancas.size());

        Tranca remainingTranca = remainingTrancas.get(0);
        assertEquals(2, remainingTranca.getId());
        assertEquals("Bicicleta 2", remainingTranca.getBicicleta());
    }

    // Testes adicionais para as outras trancas

    @Test
    public void testGetTrancaById_WithInvalidId() {
        int trancaId = 20;

        Tranca result = trancaService.getTrancaById(trancaId);

        assertNull(result);
    }

    @Test
    public void testCreateTranca_WithExistingId() {
        Tranca tranca = new Tranca(1, "Bicicleta 16", 16, "Localização 16", "2023", "Modelo 16",  TrancaStatus.NOVA);

        Tranca result = trancaService.createTranca(tranca);

        assertNull(result);
        assertEquals(15, trancaService.getTrancas().size());
    }

    @Test
    public void testUpdateTranca_WithInvalidId() {
        Tranca tranca = new Tranca(20, "Bicicleta 20", 20, "Localização 20", "2022", "Modelo 20", TrancaStatus.APOSENTADA);

        Tranca result = trancaService.updateTranca(tranca);

        assertNull(result);
    }

    @Test
    public void testUpdateTranca_WithNullTranca() {
        Tranca tranca = null;

        Tranca result = trancaService.updateTranca(tranca);

        assertNull(result);
    }

    @Test
    public void testDeleteTranca_WithInvalidId() {
        int trancaId = 20;

        trancaService.deleteTranca(trancaId);

        assertEquals(15, trancaService.getTrancas().size());
    }

    @Test
    public void testDeleteTranca_WithExistingId() {
        int trancaId = 1;

        trancaService.deleteTranca(trancaId);

        List<Tranca> remainingTrancas = trancaService.getTrancas();
        assertEquals(14, remainingTrancas.size());

        Tranca remainingTranca = remainingTrancas.get(0);
        assertEquals(2, remainingTranca.getId());
        assertEquals("Bicicleta 2", remainingTranca.getBicicleta());
    }



    @Test
    public void testCreateTranca_NullTranca() {


        Tranca result = null;

        assertNull(result);
        assertEquals(15, trancaService.getTrancas().size());
    }


    @Test
    public void testUpdateTranca_WithExistingId() {
        Tranca tranca = new Tranca(1, "Bicicleta 1", 1, "Localização 1 Atualizada", "2022", "Modelo 1", TrancaStatus.APOSENTADA);

        Tranca result = trancaService.updateTranca(tranca);

        assertEquals(tranca, result);
        assertEquals("Localização 1 Atualizada", trancaService.getTrancaById(1).getLocalizacao());
        assertEquals(TrancaStatus.APOSENTADA, trancaService.getTrancaById(1).getStatus());
    }

    @Test
    public void testUpdateTranca_WithInvalidId2() {
        Tranca tranca = new Tranca(20, "Bicicleta 20", 20, "Localização 20", "2022", "Modelo 20", TrancaStatus.APOSENTADA);

        Tranca result = trancaService.updateTranca(tranca);

        assertNull(result);
    }

    @Test
    public void testUpdateTranca_WithNullTranca2() {
        Tranca tranca = null;

        Tranca result = trancaService.updateTranca(tranca);

        assertNull(result);
    }

    @Test
    public void testDeleteTranca_WithInvalidId2() {
        int trancaId = 20;

        trancaService.deleteTranca(trancaId);

        assertEquals(15, trancaService.getTrancas().size());
    }

    @Test
    public void testDeleteTranca_WithExistingId2() {
        int trancaId = 1;

        trancaService.deleteTranca(trancaId);

        List<Tranca> remainingTrancas = trancaService.getTrancas();
        assertEquals(14, remainingTrancas.size());

        Tranca remainingTranca = remainingTrancas.get(0);
        assertEquals(2, remainingTranca.getId());
        assertEquals("Bicicleta 2", remainingTranca.getBicicleta());
    }

    @Test
    public void testGetTrancaById_WithInvalidId2() {
        int trancaId = 20;

        Tranca result = trancaService.getTrancaById(trancaId);

        assertNull(result);
    }

    @Test
    public void testCreateTranca_WithExistingId2() {
        Tranca tranca = new Tranca(1, "Bicicleta 16", 16, "Localização 16", "2023", "Modelo 16", TrancaStatus.NOVA);

        Tranca result = trancaService.createTranca(tranca);

        assertNull(result);
        assertEquals(15, trancaService.getTrancas().size());
    }

    @Test
    public void testDeleteTranca_LastTranca() {
        int trancaId = 15;

        trancaService.deleteTranca(trancaId);

        List<Tranca> remainingTrancas = trancaService.getTrancas();
        assertEquals(14, remainingTrancas.size());

        Tranca remainingTranca = remainingTrancas.get(remainingTrancas.size() - 1);
        assertEquals(14, remainingTranca.getId());
        assertEquals("Bicicleta 14", remainingTranca.getBicicleta());
    }

}
