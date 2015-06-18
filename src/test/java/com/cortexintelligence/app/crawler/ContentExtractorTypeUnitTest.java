package com.cortexintelligence.app.crawler;

import com.cortexintelligence.app.helper.extractor.impl.PDFExtractor;
import com.cortexintelligence.app.helper.extractor.impl.PowerPointExtractor;
import com.cortexintelligence.app.helper.extractor.impl.TextExtractor;
import com.cortexintelligence.app.helper.extractor.impl.WordExtractor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.File;

import static org.junit.Assert.assertEquals;

@RunWith(JUnit4.class)
public class ContentExtractorTypeUnitTest {

    @Test
    public void deveRetornarPDFExtractor() {
        assertEquals(PDFExtractor.class, ContentExtractorType.PDF.extractContent(new File("blah.pdf")).getClass());
    }

    @Test
    public void deveRetornarPowerPointExtractor() {
        assertEquals(PowerPointExtractor.class, ContentExtractorType.POWERPOINT.extractContent(new File("blah.ppt")).getClass());
        assertEquals(PowerPointExtractor.class, ContentExtractorType.POWERPOINT.extractContent(new File("blah.pptx")).getClass());
    }

    @Test
    public void deveRetornarTextExtractor() {
        assertEquals(TextExtractor.class, ContentExtractorType.TEXT.extractContent(new File("blah.txt")).getClass());
    }

    @Test
    public void deveRetornarWordExtractor() {
        assertEquals(WordExtractor.class, ContentExtractorType.WORD.extractContent(new File("blah.doc")).getClass());
        assertEquals(WordExtractor.class, ContentExtractorType.WORD.extractContent(new File("blah.docx")).getClass());
    }

}