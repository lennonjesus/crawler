package com.cortexintelligence.app.crawler;

import com.cortexintelligence.app.helper.extractor.IContentExtractor;
import com.cortexintelligence.app.helper.extractor.impl.PDFExtractor;
import com.cortexintelligence.app.helper.extractor.impl.PowerPointExtractor;
import com.cortexintelligence.app.helper.extractor.impl.TextExtractor;
import com.cortexintelligence.app.helper.extractor.impl.WordExtractor;

import java.io.File;

/**
 * Created by x4o7 on 18/06/2015.
 */
public enum ContentExtractorType {

    PDF("pdf") {
        @Override
        public IContentExtractor extractContent(File file) {
            return PDFExtractor.getInstance();
        }
    },
    POWERPOINT("ppt", "pptx") {
        @Override
        public IContentExtractor extractContent(File file) {
            return PowerPointExtractor.getInstance();
        }
    },
    TEXT("txt") {
        @Override
        public IContentExtractor extractContent(File file) {
            return TextExtractor.getInstance();
        }
    },
    WORD("doc", "docx") {
        @Override
        public IContentExtractor extractContent(File file) {
            return WordExtractor.getInstance();
        }
    };

    private String[] extensions;

    ContentExtractorType(String... extensions) {
        this.extensions = extensions;
    }

    public abstract IContentExtractor extractContent(File file);


}
