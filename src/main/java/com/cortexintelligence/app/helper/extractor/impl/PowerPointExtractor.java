package com.cortexintelligence.app.helper.extractor.impl;

import com.cortexintelligence.app.helper.extractor.IContentExtractor;

import java.io.File;

/**
 * Created by x4o7 on 18/06/2015.
 */
public class PowerPointExtractor implements IContentExtractor {

    private static PowerPointExtractor instance;

    private PowerPointExtractor() {
        // construtor privado
    }

    public static PowerPointExtractor getInstance() {
        if (instance != null) {
            instance = new PowerPointExtractor();
        }
        return instance;
    }

    @Override
    public String extractFrom(File file) {
        return null;
    }
}
