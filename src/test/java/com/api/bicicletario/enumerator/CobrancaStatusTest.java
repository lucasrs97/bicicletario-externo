package com.api.bicicletario.enumerator;

import com.api.bicicletario.enumerator.CobrancaStatus;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CobrancaStatusTest {
    @Test
    public void testEnumValues() {
        assertEquals(5, CobrancaStatus.values().length);
        assertEquals(CobrancaStatus.PENDENTE, CobrancaStatus.valueOf("PENDENTE"));
        assertEquals(CobrancaStatus.PAGA, CobrancaStatus.valueOf("PAGA"));
        assertEquals(CobrancaStatus.FALHA, CobrancaStatus.valueOf("FALHA"));
        assertEquals(CobrancaStatus.CANCELADA, CobrancaStatus.valueOf("CANCELADA"));
        assertEquals(CobrancaStatus.OCUPADA, CobrancaStatus.valueOf("OCUPADA"));
    }
}
