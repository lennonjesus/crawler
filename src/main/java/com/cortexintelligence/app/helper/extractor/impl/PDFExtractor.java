package com.cortexintelligence.app.helper.extractor.impl;

import com.cortexintelligence.app.helper.extractor.IContentExtractor;

import java.io.File;

/**
 * Created by x4o7 on 18/06/2015.
 */
public class PDFExtractor implements IContentExtractor {

    private static PDFExtractor instance;

    private PDFExtractor() {
        // construtor privado
    }

    public static PDFExtractor getInstance() {
        if (instance != null) {
            instance = new PDFExtractor();
        }
        return instance;
    }

    @Override
    public String extractFrom(File file) {
        return null;
    }
}
