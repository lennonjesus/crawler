package com.cortexintelligence.app.helper.extractor.impl;

import com.cortexintelligence.app.helper.extractor.IContentExtractor;

import java.io.File;

/**
 * Created by x4o7 on 18/06/2015.
 */
public class WordExtractor implements IContentExtractor {

    private static WordExtractor instance;

    private WordExtractor() {
        // construtor privado
    }

    public static WordExtractor getInstance() {
        if (instance != null) {
            instance = new WordExtractor();
        }
        return instance;
    }

    @Override
    public String extractFrom(File file) {
        return null;
    }
}
