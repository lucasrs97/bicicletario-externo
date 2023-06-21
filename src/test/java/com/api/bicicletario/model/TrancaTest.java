package com.api.bicicletario.model;

import com.api.bicicletario.enumerator.TrancaStatus;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;


public class TrancaTest {
    private Tranca tranca;

    @Before
    public void setUp() {
        tranca = new Tranca(1, "Bicicleta1", 123, "Local1", "2021", "Modelo1", TrancaStatus.LIVRE);
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
        Assert.assertEquals(TrancaStatus.LIVRE, tranca.getStatus());
    }

    @Test
    public void testSetStatus() {
        tranca.setStatus(TrancaStatus.valueOf(String.valueOf(TrancaStatus.APOSENTADA)));
        Assert.assertEquals(TrancaStatus.APOSENTADA, tranca.getStatus());
    }
    @Test
    public void testHashCode() {
        Tranca tranca1 = new Tranca(1, "bicicleta1", 1, "localizacao1", "2021", "modelo1", TrancaStatus.LIVRE);
        Tranca tranca2 = new Tranca(1, "bicicleta1", 1, "localizacao1", "2021", "modelo1", TrancaStatus.LIVRE);

        Assert.assertEquals(tranca1.hashCode(), tranca2.hashCode());
    }
    @Test
    public void testHashCode2() {
        Tranca tranca1 = new Tranca(2, "bicicleta2", 3, "localizacao1", "2021", "modelo1", TrancaStatus.OCUPADA);
        Tranca tranca2 = new Tranca(2, "bicicleta2", 3, "localizacao1", "2021", "modelo1", TrancaStatus.OCUPADA);

        Assert.assertEquals(tranca1.hashCode(), tranca2.hashCode());
    }
    @Test
    public void testNotEquals() {
        Tranca other = new Tranca(2, "Bicicleta2", 456, "Local2", "2022", "Modelo2", TrancaStatus.LIVRE);
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
    public void testEquals() {
        Tranca tranca1 = new Tranca(1, "bicicleta1", 1, "localizacao1", "2021", "modelo1", TrancaStatus.LIVRE);
        Tranca tranca2 = new Tranca(1, "bicicleta1", 1, "localizacao1", "2021", "modelo1", TrancaStatus.LIVRE);

        Assert.assertEquals(tranca1, tranca2);
    }
    @Test
    public void testGetId2() {
        Tranca tranca = new Tranca();
        tranca.setId(1);
        Assert.assertEquals(1, tranca.getId());
    }

    @Test
    public void testSetId2() {
        Tranca tranca = new Tranca();
        tranca.setId(1);
        Assert.assertEquals(1, tranca.getId());
    }

    @Test
    public void testGetBicicleta2() {
        Tranca tranca = new Tranca();
        tranca.setBicicleta("bicicleta1");
        Assert.assertEquals("bicicleta1", tranca.getBicicleta());
    }

    @Test
    public void testSetBicicleta2() {
        Tranca tranca = new Tranca();
        tranca.setBicicleta("bicicleta1");
        Assert.assertEquals("bicicleta1", tranca.getBicicleta());
    }

    @Test
    public void testGetNumero2() {
        Tranca tranca = new Tranca();
        tranca.setNumero(1);
        Assert.assertEquals(1, tranca.getNumero());
    }

    @Test
    public void testSetNumero2() {
        Tranca tranca = new Tranca();
        tranca.setNumero(1);
        Assert.assertEquals(1, tranca.getNumero());
    }

    @Test
    public void testGetLocalizacao2() {
        Tranca tranca = new Tranca();
        tranca.setLocalizacao("localizacao1");
        Assert.assertEquals("localizacao1", tranca.getLocalizacao());
    }

    @Test
    public void testSetLocalizacao2() {
        Tranca tranca = new Tranca();
        tranca.setLocalizacao("localizacao1");
        Assert.assertEquals("localizacao1", tranca.getLocalizacao());
    }

    @Test
    public void testGetAnoDeFabricacao2() {
        Tranca tranca = new Tranca();
        tranca.setAnoDeFabricacao("2021");
        Assert.assertEquals("2021", tranca.getAnoDeFabricacao());
    }

    @Test
    public void testSetAnoDeFabricacao2() {
        Tranca tranca = new Tranca();
        tranca.setAnoDeFabricacao("2021");
        Assert.assertEquals("2021", tranca.getAnoDeFabricacao());
    }

    @Test
    public void testGetModelo2() {
        Tranca tranca = new Tranca();
        tranca.setModelo("modelo1");
        Assert.assertEquals("modelo1", tranca.getModelo());
    }

    @Test
    public void testSetModelo2() {
        Tranca tranca = new Tranca();
        tranca.setModelo("modelo1");
        Assert.assertEquals("modelo1", tranca.getModelo());
    }

    @Test
    public void testGetStatus2() {
        Tranca tranca = new Tranca();
        TrancaStatus status = TrancaStatus.LIVRE;
        tranca.setStatus(status);
        Assert.assertEquals(status, tranca.getStatus());
    }

    @Test
    public void testSetStatus2() {
        Tranca tranca = new Tranca();
        TrancaStatus status = TrancaStatus.LIVRE;
        tranca.setStatus(status);
        Assert.assertEquals(status, tranca.getStatus());


    }
    @Test
    public void testConstructor() {
        Assertions.assertEquals(1, tranca.getId());
        Assertions.assertEquals("Bicicleta1", tranca.getBicicleta());
        Assertions.assertEquals(123, tranca.getNumero());
        Assertions.assertEquals("Local1", tranca.getLocalizacao());
        Assertions.assertEquals("2021", tranca.getAnoDeFabricacao());
        Assertions.assertEquals("Modelo1", tranca.getModelo());
        Assertions.assertEquals(TrancaStatus.LIVRE, tranca.getStatus());
    }

    @Test
    public void testDefaultConstructor() {
        Tranca defaultTranca = new Tranca();
        Assertions.assertNull(defaultTranca.getBicicleta());
        Assertions.assertEquals(0, defaultTranca.getId());

    }

    @Test
    public void testSetters() {
        tranca.setId(2);
        Assertions.assertEquals(2, tranca.getId());

        tranca.setBicicleta("Bicicleta2");
        Assertions.assertEquals("Bicicleta2", tranca.getBicicleta());

        tranca.setNumero(456);
        Assertions.assertEquals(456, tranca.getNumero());

        tranca.setLocalizacao("Local2");
        Assertions.assertEquals("Local2", tranca.getLocalizacao());

        tranca.setAnoDeFabricacao("2021");
        Assertions.assertEquals("2021", tranca.getAnoDeFabricacao());

        tranca.setModelo("Modelo2");
        Assertions.assertEquals("Modelo2", tranca.getModelo());

        tranca.setStatus(TrancaStatus.LIVRE);
        Assertions.assertEquals(TrancaStatus.LIVRE, tranca.getStatus());
    }


}
