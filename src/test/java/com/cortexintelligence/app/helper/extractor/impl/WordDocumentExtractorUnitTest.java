package com.cortexintelligence.app.helper.extractor.impl;

import org.apache.commons.lang.StringUtils;
import org.junit.Test;

import java.io.File;

import static com.cortexintelligence.app.TestConstants.DEFAULT_TEST_FILES_DIR;
import static org.junit.Assert.assertEquals;

/**
 * Created by lennonjesus on 18/06/15.
 */
public class WordDocumentExtractorUnitTest {

    @Test
    public void deveExtrairConteudoDeArquivoWordDocX() {
        String conteudo = WordDocumentExtractor.getInstance().extractFrom(new File(DEFAULT_TEST_FILES_DIR + "word.docx"));

        assertEquals("Documento Word", conteudo);
    }

    @Test
    public void deveExtrairConteudoDeArquivoWordDoc() {
        String conteudo = WordDocumentExtractor.getInstance().extractFrom(new File(DEFAULT_TEST_FILES_DIR + "word.doc"));

        assertEquals("Documento Word", conteudo);
    }

    @Test
    public void deveRetornarConteudoVazioCasoHajaAlgumErroNaRecuperacao() {
        assertEquals(StringUtils.EMPTY, WordDocumentExtractor.getInstance().extractFrom(new File(StringUtils.EMPTY)));
    }

}
