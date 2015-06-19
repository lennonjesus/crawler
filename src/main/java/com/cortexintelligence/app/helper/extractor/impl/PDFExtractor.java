package com.cortexintelligence.app.helper.extractor.impl;

import com.cortexintelligence.app.helper.extractor.IContentExtractor;
import org.apache.commons.lang.StringUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;

import java.io.File;
import java.io.IOException;

/**
 * Created by x4o7 on 18/06/2015.
 */
public class PDFExtractor implements IContentExtractor {

    private static PDFExtractor instance;

    private PDFExtractor() {
        // construtor privado
    }

    public static PDFExtractor getInstance() {
        if (instance == null) {
            instance = new PDFExtractor();
        }
        return instance;
    }

    @Override
    public String extractFrom(File file) {
        String conteudo = StringUtils.EMPTY;

        try {
            conteudo = new PDFTextStripper().getText(PDDocument.load(file));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return StringUtils.trim(conteudo);
    }
}
