package com.cortexintelligence.app.helper.extractor.impl;

import com.cortexintelligence.app.helper.extractor.IContentExtractor;
import org.apache.commons.lang.StringUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.List;

/**
 * Created by x4o7 on 18/06/2015.
 */
public class TextExtractor implements IContentExtractor {

    private static TextExtractor instance;

    private TextExtractor() {
        // construtor privado
    }

    public static TextExtractor getInstance() {
        if (instance == null) {
            instance = new TextExtractor();
        }
        return instance;
    }

    @Override
    public String extractFrom(File file) {
        StringBuilder conteudo = new StringBuilder();

        try {
            List<String> linhas = Files.readAllLines(file.toPath(), Charset.defaultCharset());

            for (String linha : linhas) {
                conteudo.append(linha);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return StringUtils.trim(conteudo.toString());

    }
}
