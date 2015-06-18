package com.cortexintelligence.app.helper.extractor.impl;

import com.cortexintelligence.app.helper.extractor.IContentExtractor;

import java.io.File;

/**
 * Created by x4o7 on 18/06/2015.
 */
public class TextExtractor implements IContentExtractor {

    private static TextExtractor instance;

    private TextExtractor() {
        // construtor privado
    }

    public static TextExtractor getInstance() {
        if (instance != null) {
            instance = new TextExtractor();
        }
        return instance;
    }

    @Override
    public String extractFrom(File file) {
        return null;
    }
}
