package com.cortexintelligence.app.crawler;

import com.cortexintelligence.app.crawler.impl.ArquivosCrawlerImpl;
import com.cortexintelligence.app.crawler.impl.GmailCrawlerImpl;
import com.cortexintelligence.app.crawler.impl.WebCrawlerImpl;

/**
 * Created by lennonjesus on 18/06/15.
 */
public enum TipoCrawler {

    ARQUIVOS("arquivos") {
        @Override
        public void execute(String ... args) {
            new ArquivosCrawlerImpl().start(args);
        }
    },
    GMAIL("gmail") {
        @Override
        public void execute(String ... args) {
            new GmailCrawlerImpl().start(args);
        }
    },
    WEB("web") {
        @Override
        public void execute(String ... args) {
            new WebCrawlerImpl().start(args);
        }
    };

    private String nome;

    TipoCrawler(String nome) {
        this.nome = nome;
    }

    /**
     * Recupera um Tipo a partir de uma String representando o valor de seu nome
     * @param nome nome do tipo a recuperar
     * @return o Tipo encontrado ou null, caso não haja correspondência
     */
    public static TipoCrawler from(String nome) {
        for (TipoCrawler tipoCrawler : TipoCrawler.values()) {
            if (tipoCrawler.nome.equalsIgnoreCase(nome)) {
                return tipoCrawler;
            }
        }

        return null;
    }


    public abstract void execute(String ... args);
}
