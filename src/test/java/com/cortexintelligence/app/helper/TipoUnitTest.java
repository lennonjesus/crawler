package com.cortexintelligence.app.helper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(JUnit4.class)
public class TipoUnitTest {

    @Test
    public void deveConverterStringEmTipoCorretamente() {

        assertEquals(Tipo.ARQUIVOS, Tipo.from("ARQUIVOS"));
        assertEquals(Tipo.ARQUIVOS, Tipo.from("arquivos"));
        assertEquals(Tipo.ARQUIVOS, Tipo.from("ArQuiVoS"));
        assertEquals(Tipo.GMAIL, Tipo.from("GMAIL"));
        assertEquals(Tipo.GMAIL, Tipo.from("gmail"));
        assertEquals(Tipo.GMAIL, Tipo.from("GmaiL"));
        assertEquals(Tipo.WEB, Tipo.from("WEB"));
        assertEquals(Tipo.WEB, Tipo.from("web"));
        assertEquals(Tipo.WEB, Tipo.from("wEb"));
        assertNull(Tipo.from("algo que não existe"));
        assertNull(Tipo.from(null));
        assertNull(Tipo.from(""));

    }

}