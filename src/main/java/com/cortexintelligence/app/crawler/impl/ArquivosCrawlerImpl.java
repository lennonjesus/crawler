package com.cortexintelligence.app.crawler.impl;

import com.cortexintelligence.app.crawler.ICrawler;
import com.cortexintelligence.app.exceptions.DiretorioInvalidoException;
import com.cortexintelligence.app.exceptions.ParametrosInsuficientesException;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hslf.extractor.PowerPointExtractor;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.xslf.usermodel.XMLSlideShow;
import org.apache.poi.xslf.usermodel.XSLFSlide;
import org.apache.poi.xslf.usermodel.XSLFTextShape;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.Collection;

/**
 * Created by lennonjesus on 18/06/15.
 */
public class ArquivosCrawlerImpl extends WebCrawler implements ICrawler {

    @Override
    public void start(String... args) {
        if (ArrayUtils.isEmpty(args) || StringUtils.isEmpty(args[0])) {
            throw new ParametrosInsuficientesException();
        }

        File diretorio = new File(args[0]);

        if (!diretorio.exists() || !diretorio.isDirectory()) {
            throw new DiretorioInvalidoException();
        }

        Collection<File> files = FileUtils.listFiles(diretorio, null, true);

        for (File file : files) {
            System.out.println("Título: " + file.getPath());

            String conteudo = StringUtils.EMPTY;

            try {
                String name = file.getName();

                if (name.endsWith(".doc")) {
                    conteudo = new WordExtractor(new FileInputStream(file)).getText();
                } else if (name.endsWith(".ppt")) {
                    conteudo = new PowerPointExtractor(new FileInputStream(file)).getText();
                } else if (name.endsWith(".txt")) {
                    conteudo = Files.readAllLines(file.toPath(), Charset.defaultCharset()).toString(); //FIXME remover []
                } else if (name.endsWith(".pdf")) {
                    // conteudo = new PDFTextStripper().getText(PDDocument.load(file)); //FIXME
                } else if (name.endsWith(".docx")) {

                } else if (name.endsWith(".pptx")) {
                    XMLSlideShow ppt = new XMLSlideShow(new FileInputStream(file));
                    for (XSLFSlide slide : ppt.getSlides()) {

                        for (XSLFTextShape shape : slide.getPlaceholders()) {
                            conteudo = shape.getText(); // FIXME
                        }
                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

            System.out.println("Conteudo: " + conteudo);
        }

    }



}
