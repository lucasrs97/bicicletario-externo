import com.api.bicicletario.model.Tranca;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class TrancaTest {
    private Tranca tranca;

    @Before
    public void setUp() {
        tranca = new Tranca(1, "Bicicleta1", 123, "Local1", "2021", "Modelo1", "Ativo");
    }

    @Test
    public void testGetId() {
        Assert.assertEquals(1, tranca.getId());
    }

    @Test
    public void testSetId() {
        tranca.setId(2);
        Assert.assertEquals(2, tranca.getId());
    }

    @Test
    public void testGetBicicleta() {
        Assert.assertEquals("Bicicleta1", tranca.getBicicleta());
    }

    @Test
    public void testSetBicicleta() {
        tranca.setBicicleta("Bicicleta2");
        Assert.assertEquals("Bicicleta2", tranca.getBicicleta());
    }

    @Test
    public void testGetNumero() {
        Assert.assertEquals(123, tranca.getNumero());
    }

    @Test
    public void testSetNumero() {
        tranca.setNumero(456);
        Assert.assertEquals(456, tranca.getNumero());
    }

    @Test
    public void testGetLocalizacao() {
        Assert.assertEquals("Local1", tranca.getLocalizacao());
    }

    @Test
    public void testSetLocalizacao() {
        tranca.setLocalizacao("Local2");
        Assert.assertEquals("Local2", tranca.getLocalizacao());
    }

    @Test
    public void testGetAnoDeFabricacao() {
        Assert.assertEquals("2021", tranca.getAnoDeFabricacao());
    }

    @Test
    public void testSetAnoDeFabricacao() {
        tranca.setAnoDeFabricacao("2022");
        Assert.assertEquals("2022", tranca.getAnoDeFabricacao());
    }

    @Test
    public void testGetModelo() {
        Assert.assertEquals("Modelo1", tranca.getModelo());
    }

    @Test
    public void testSetModelo() {
        tranca.setModelo("Modelo2");
        Assert.assertEquals("Modelo2", tranca.getModelo());
    }

    @Test
    public void testGetStatus() {
        Assert.assertEquals("Ativo", tranca.getStatus());
    }

    @Test
    public void testSetStatus() {
        tranca.setStatus("Inativo");
        Assert.assertEquals("Inativo", tranca.getStatus());
    }

    @Test
    public void testEquals() {
        Tranca other = new Tranca(1, "Bicicleta1", 123, "Local1", "2021", "Modelo1", "Ativo");
        Assert.assertEquals(tranca, other);
    }

    @Test
    public void testNotEquals() {
        Tranca other = new Tranca(2, "Bicicleta2", 456, "Local2", "2022", "Modelo2", "Inativo");
        Assert.assertNotEquals(tranca, other);
    }

    @Test
    public void testEmptyConstructor() {
        Tranca emptyTranca = new Tranca();
        Assert.assertNotNull(emptyTranca);
        Assert.assertEquals(0, emptyTranca.getId());
        Assert.assertNull(emptyTranca.getBicicleta());
        Assert.assertEquals(0, emptyTranca.getNumero());
        Assert.assertNull(emptyTranca.getLocalizacao());
        Assert.assertNull(emptyTranca.getAnoDeFabricacao());
        Assert.assertNull(emptyTranca.getModelo());
        Assert.assertNull(emptyTranca.getStatus());
    }

    @Test
    public void testHashCode() {
        Tranca other = new Tranca(1, "Bicicleta1", 123, "Local1", "2021", "Modelo1", "Ativo");
        Assert.assertEquals(tranca.hashCode(), other.hashCode());
    }
}
