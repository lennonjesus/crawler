package com.cortexintelligence.app.helper.extractor.impl;

import com.cortexintelligence.app.helper.extractor.IContentExtractor;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.xslf.usermodel.XMLSlideShow;
import org.apache.poi.xslf.usermodel.XSLFSlide;
import org.apache.poi.xslf.usermodel.XSLFTextShape;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Created by x4o7 on 18/06/2015.
 */
public class PowerPointExtractor implements IContentExtractor {

    private static PowerPointExtractor instance;

    private PowerPointExtractor() {
        // construtor privado
    }

    public static PowerPointExtractor getInstance() {
        if (instance == null) {
            instance = new PowerPointExtractor();
        }
        return instance;
    }

    @Override
    public String extractFrom(File file) {
        StringBuilder conteudo = new StringBuilder();

        try {
            FileInputStream fileInputStream = new FileInputStream(file);

            if (file.getName().endsWith(".ppt")) {
                conteudo.append(new org.apache.poi.hslf.extractor.PowerPointExtractor(fileInputStream).getText());
            } else if (file.getName().endsWith(".pptx")) {
                XMLSlideShow ppt = new XMLSlideShow(fileInputStream);
                for (XSLFSlide slide : ppt.getSlides()) {
                    for (XSLFTextShape shape : slide.getPlaceholders()) {
                        conteudo.append(shape.getText());
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return StringUtils.trim(conteudo.toString());
    }
}
