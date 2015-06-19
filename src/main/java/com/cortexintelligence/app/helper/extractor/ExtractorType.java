package com.cortexintelligence.app.helper.extractor;

import com.cortexintelligence.app.helper.extractor.impl.PDFExtractor;
import com.cortexintelligence.app.helper.extractor.impl.PowerPointExtractor;
import com.cortexintelligence.app.helper.extractor.impl.TextExtractor;
import com.cortexintelligence.app.helper.extractor.impl.WordDocumentExtractor;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;

/**
 * Created by x4o7 on 18/06/2015.
 */
public enum ExtractorType {

    PDF("pdf") {
        @Override
        public IContentExtractor getExtractor() {
            return PDFExtractor.getInstance();
        }
    },
    POWERPOINT("ppt", "pptx") {
        @Override
        public IContentExtractor getExtractor() {
            return PowerPointExtractor.getInstance();
        }
    },
    TEXT("txt") {
        @Override
        public IContentExtractor getExtractor() {
            return TextExtractor.getInstance();
        }
    },
    WORD("doc", "docx") {
        @Override
        public IContentExtractor getExtractor() {
            return WordDocumentExtractor.getInstance();
        }
    };

    private String[] extensions;

    ExtractorType(String... extensions) {
        this.extensions = extensions;
    }

    public static ExtractorType from(String str) {
        for (ExtractorType type : values()) {
            if (ArrayUtils.contains(type.extensions, StringUtils.lowerCase(str))) {
                return type;
            }
        }
        return null;
    }

    public abstract IContentExtractor getExtractor();


}
