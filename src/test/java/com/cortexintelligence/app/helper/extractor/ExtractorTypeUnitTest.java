package com.cortexintelligence.app.helper.extractor;

import com.cortexintelligence.app.helper.extractor.impl.PDFExtractor;
import com.cortexintelligence.app.helper.extractor.impl.PowerPointExtractor;
import com.cortexintelligence.app.helper.extractor.impl.TextExtractor;
import com.cortexintelligence.app.helper.extractor.impl.WordDocumentExtractor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mock;

import java.io.File;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(JUnit4.class)
public class ExtractorTypeUnitTest {

    @Test
    public void deveRetornarPDFExtractor() {
        assertEquals(PDFExtractor.class, ExtractorType.PDF.getExtractor().getClass());

    }

    @Test
    public void deveRetornarPowerPointExtractor() {
        assertEquals(PowerPointExtractor.class, ExtractorType.POWERPOINT.getExtractor().getClass());
    }

    @Test
    public void deveRetornarTextExtractor() {
        assertEquals(TextExtractor.class, ExtractorType.TEXT.getExtractor().getClass());

    }

    @Test
    public void deveRetornarWordExtractor() {
        assertEquals(WordDocumentExtractor.class, ExtractorType.WORD.getExtractor().getClass());
    }

    @Test
    public void deveConverterStringEmExtractorTypeCorretamente() {

        assertEquals(ExtractorType.PDF, ExtractorType.from("pDF"));
        assertEquals(ExtractorType.POWERPOINT, ExtractorType.from("Ppt"));
        assertEquals(ExtractorType.POWERPOINT, ExtractorType.from("pptX"));
        assertEquals(ExtractorType.TEXT, ExtractorType.from("tXt"));
        assertEquals(ExtractorType.WORD, ExtractorType.from("doC"));
        assertEquals(ExtractorType.WORD, ExtractorType.from("DocX"));
        assertNull(ExtractorType.from("algo que não existe"));
        assertNull(ExtractorType.from(null));
        assertNull(ExtractorType.from(""));

    }

}