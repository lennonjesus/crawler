package com.cortexintelligence.app.crawler.impl;

import com.cortexintelligence.app.crawler.ICrawler;
import com.cortexintelligence.app.exceptions.DiretorioInvalidoException;
import com.cortexintelligence.app.exceptions.ParametrosInsuficientesException;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;

import java.io.File;

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


    }


}
