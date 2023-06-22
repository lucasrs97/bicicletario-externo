import com.api.bicicletario.enumerator.CobrancaStatus;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

public class CobrancaStatusTest {

    @Test
    public void testEnumValues() {
        // Cria um mock da enumeração

        // Verifica se os valores das enumerações são os esperados
        assertEquals(5, CobrancaStatus.values().length);
        assertEquals(CobrancaStatus.PENDENTE, CobrancaStatus.valueOf("PENDENTE"));
        assertEquals(CobrancaStatus.PAGA, CobrancaStatus.valueOf("PAGA"));
        assertEquals(CobrancaStatus.FALHA, CobrancaStatus.valueOf("FALHA"));
        assertEquals(CobrancaStatus.CANCELADA, CobrancaStatus.valueOf("CANCELADA"));
        assertEquals(CobrancaStatus.OCUPADA, CobrancaStatus.valueOf("OCUPADA"));
    }
}
