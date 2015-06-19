package com.cortexintelligence.app.helper.extractor.impl;

import org.apache.commons.lang.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.File;

import static com.cortexintelligence.app.TestConstants.DEFAULT_TEST_FILES_DIR;
import static org.junit.Assert.assertEquals;

@RunWith(JUnit4.class)
public class PDFExtractorUnitTest {

    @Test
    public void deveExtrairConteudoDeArquivoPDF() {
        String conteudo = PDFExtractor.getInstance().extractFrom(new File(DEFAULT_TEST_FILES_DIR + "pdf.pdf"));

        assertEquals("Documento PDF", conteudo);
    }

    @Test
    public void deveRetornarConteudoVazioCasoHajaAlgumErroNaRecuperacao() {
        assertEquals(StringUtils.EMPTY, PDFExtractor.getInstance().extractFrom(new File(StringUtils.EMPTY)));
    }

}