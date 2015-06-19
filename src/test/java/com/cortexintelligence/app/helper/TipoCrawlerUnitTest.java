package com.cortexintelligence.app.helper;

import com.cortexintelligence.app.crawler.TipoCrawler;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(JUnit4.class)
public class TipoCrawlerUnitTest {

    @Test
    public void deveConverterStringEmTipoCorretamente() {

        assertEquals(TipoCrawler.ARQUIVOS, TipoCrawler.from("ARQUIVOS"));
        assertEquals(TipoCrawler.ARQUIVOS, TipoCrawler.from("arquivos"));
        assertEquals(TipoCrawler.ARQUIVOS, TipoCrawler.from("ArQuiVoS"));
        assertEquals(TipoCrawler.GMAIL, TipoCrawler.from("GMAIL"));
        assertEquals(TipoCrawler.GMAIL, TipoCrawler.from("gmail"));
        assertEquals(TipoCrawler.GMAIL, TipoCrawler.from("GmaiL"));
        assertEquals(TipoCrawler.WEB, TipoCrawler.from("WEB"));
        assertEquals(TipoCrawler.WEB, TipoCrawler.from("web"));
        assertEquals(TipoCrawler.WEB, TipoCrawler.from("wEb"));
        assertNull(TipoCrawler.from("algo que não existe"));
        assertNull(TipoCrawler.from(null));
        assertNull(TipoCrawler.from(""));

    }

}