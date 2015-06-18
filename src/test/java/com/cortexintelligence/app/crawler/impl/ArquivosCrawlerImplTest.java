package com.cortexintelligence.app.crawler.impl;

import com.cortexintelligence.app.crawler.ICrawler;
import com.cortexintelligence.app.exceptions.DiretorioInvalidoException;
import com.cortexintelligence.app.exceptions.ParametrosInsuficientesException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class ArquivosCrawlerImplTest {

    private ICrawler crawler = new ArquivosCrawlerImpl();

    @Test(expected = ParametrosInsuficientesException.class)
    public void deveCriticarArgumentoNulo() {
        crawler.start(null);
    }

    @Test(expected = ParametrosInsuficientesException.class)
    public void deveCriticarArgumentoVazio() {
        crawler.start("");
    }

    @Test(expected = DiretorioInvalidoException.class)
    public void deveCriticarDiretorioInformadoInexistente() {
         crawler.start("DIRETORIO INEXISTENTE");
    }

}