package com.cortexintelligence.app.crawler.impl;

import com.cortexintelligence.app.crawler.ICrawler;
import com.cortexintelligence.app.exceptions.DiretorioInvalidoException;
import com.cortexintelligence.app.exceptions.ParametrosInsuficientesException;
import com.cortexintelligence.app.helper.extractor.ExtractorType;
import com.cortexintelligence.app.helper.extractor.IContentExtractor;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;

import java.io.File;
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

        Collection<File> files = FileUtils.listFiles(diretorio, null, true); //listando arquivos recursivamente

        for (File file : files) {

            ExtractorType extractorType = ExtractorType.from(FilenameUtils.getExtension(file.getName()));

            String conteudo = StringUtils.EMPTY;

            if (extractorType != null) {
                IContentExtractor extractor = extractorType.getExtractor();
                conteudo = extractor.extractFrom(file);
            }

            System.out.println("Título: " + file.getPath());
            System.out.println("Conteudo: " + conteudo);
        }

    }


}
