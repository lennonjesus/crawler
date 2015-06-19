package com.cortexintelligence.app.crawler.impl;

import com.cortexintelligence.app.crawler.ICrawler;
import com.cortexintelligence.app.exceptions.ParametrosInsuficientesException;
import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;
import edu.uci.ics.crawler4j.url.WebURL;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.math.NumberUtils;

import java.util.regex.Pattern;

/**
 * Created by lennonjesus on 18/06/15.
 */
public class WebCrawlerImpl extends WebCrawler implements ICrawler {

    private static final Pattern IGNORED_EXTENSIONS = Pattern.compile(".*\\.(bmp|gif|jpg|png|mp3|exe|apk|asx|css|doc" +
            "|docx|flv|gif|jpeg|mid|mov|ogg|pdf|png|ppt|ra|ram|rm|swf|wav|wma|wmv|zip|m4a|m4v|mov|mp4|m4b)$");

    @Override
    public void start(String... args) {

        if (ArrayUtils.isEmpty(args) || ArrayUtils.getLength(args) < 2) {
            throw new ParametrosInsuficientesException();
        }

        String url = args[0];

        // profundidade default 0 se for informada profundidade invalida
        int profundidade = NumberUtils.isNumber(args[1]) ? Integer.parseInt(args[1]) : 0;

        String crawlStorageFolder = "temp";

        int numberOfCrawlers = 1;
        CrawlConfig config = new CrawlConfig();

        config.setCrawlStorageFolder(crawlStorageFolder);
        config.setPolitenessDelay(1000);
        config.setMaxDepthOfCrawling(profundidade);
        config.setMaxPagesToFetch(100);
        config.setIncludeBinaryContentInCrawling(false);
        config.setResumableCrawling(false);

        PageFetcher pageFetcher = new PageFetcher(config);
        RobotstxtConfig robotstxtConfig = new RobotstxtConfig();
        RobotstxtServer robotstxtServer = new RobotstxtServer(robotstxtConfig, pageFetcher);
        CrawlController controller = null;

        try {
            controller = new CrawlController(config, pageFetcher, robotstxtServer);

            controller.addSeed(url);

            controller.start(WebCrawlerImpl.class, numberOfCrawlers);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public boolean shouldVisit(Page referringPage, WebURL url) {
        String href = url.getURL().toLowerCase();

        if (IGNORED_EXTENSIONS.matcher(href).matches()) {
            return false;
        }

        return href.startsWith("http://") || href.startsWith("https://");
    }

    @Override
    public void visit(Page page) {
        String url = page.getWebURL().getURL();

        System.out.println("Titulo: " + url);

        if (page.getParseData() instanceof HtmlParseData) {
            HtmlParseData htmlParseData = (HtmlParseData) page.getParseData();
            String text = htmlParseData.getText();
            System.out.println("Conteudo: " + text);
        }

    }
}



