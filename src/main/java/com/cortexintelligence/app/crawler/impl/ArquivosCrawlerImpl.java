package com.cortexintelligence.app.crawler.impl;

import com.cortexintelligence.app.crawler.ICrawler;
import com.cortexintelligence.app.exceptions.DiretorioInvalidoException;
import com.cortexintelligence.app.exceptions.ParametrosInsuficientesException;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;
import org.apache.poi.hslf.extractor.PowerPointExtractor;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.xslf.usermodel.XMLSlideShow;
import org.apache.poi.xslf.usermodel.XSLFSlide;
import org.apache.poi.xslf.usermodel.XSLFTextShape;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.Collection;
import java.util.List;

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

                FileInputStream fileInputStream = new FileInputStream(file);

                if (name.endsWith(".doc")) {
                    conteudo = new WordExtractor(fileInputStream).getText();
                } else if (name.endsWith(".ppt")) {
                    conteudo = new PowerPointExtractor(fileInputStream).getText();
                } else if (name.endsWith(".txt")) {
                    List<String> linhas = Files.readAllLines(file.toPath(), Charset.defaultCharset());
                    conteudo = linhas != null ? linhas.toString() : StringUtils.EMPTY; //FIXME remover []
                } else if (name.endsWith(".pdf")) {
                    conteudo = new PDFTextStripper().getText(PDDocument.load(file)); //FIXME
                } else if (name.endsWith(".docx")) {
                    XWPFDocument doc = new XWPFDocument(fileInputStream);
                    conteudo = new XWPFWordExtractor(doc).getText(); //FIXME
                } else if (name.endsWith(".pptx")) {
                    XMLSlideShow ppt = new XMLSlideShow(fileInputStream);
                    for (XSLFSlide slide : ppt.getSlides()) {

                        for (XSLFTextShape shape : slide.getPlaceholders()) {
                            conteudo = shape.getText(); // FIXME append!
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
