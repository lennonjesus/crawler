package com.cortexintelligence.app.helper.extractor.impl;

import com.cortexintelligence.app.helper.extractor.IContentExtractor;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Created by x4o7 on 18/06/2015.
 */
public class WordDocumentExtractor implements IContentExtractor {

    private static WordDocumentExtractor instance;

    private WordDocumentExtractor() {
        // construtor privado
    }

    public static WordDocumentExtractor getInstance() {
        if (instance == null) {
            instance = new WordDocumentExtractor();
        }
        return instance;
    }

    @Override
    public String extractFrom(File file) {

        String conteudo = StringUtils.EMPTY;

        try {
            FileInputStream fileInputStream = new FileInputStream(file);

            if (file.getName().endsWith(".doc")) {
                conteudo = new WordExtractor(fileInputStream).getText();
            } else if (file.getName().endsWith(".docx")) {
                XWPFDocument doc = new XWPFDocument(fileInputStream);
                conteudo = new XWPFWordExtractor(doc).getText();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        return StringUtils.trim(conteudo);
    }
}
