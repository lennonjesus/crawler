package com.cortexintelligence.app.crawler.impl;

import com.cortexintelligence.app.crawler.ICrawler;
import com.cortexintelligence.app.sample.BasicCrawler;
import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;
import edu.uci.ics.crawler4j.url.WebURL;
import org.apache.http.Header;

import java.util.regex.Pattern;

/**
 * Created by lennonjesus on 18/06/15.
 */
public class WebCrawlerImpl extends WebCrawler implements ICrawler {

    private static final Pattern IMAGE_EXTENSIONS = Pattern.compile(".*\\.(bmp|gif|jpg|png)$");

    @Override
    public void start(String ... args) {
        String crawlStorageFolder = "temp";

        int numberOfCrawlers = 1;
        CrawlConfig config = new CrawlConfig();
        config.setCrawlStorageFolder(crawlStorageFolder);

        config.setPolitenessDelay(1000);
        config.setMaxDepthOfCrawling(2); // FIXME
        config.setMaxPagesToFetch(100);
        config.setIncludeBinaryContentInCrawling(false);
        config.setResumableCrawling(false);

        PageFetcher pageFetcher = new PageFetcher(config);
        RobotstxtConfig robotstxtConfig = new RobotstxtConfig();
        RobotstxtServer robotstxtServer = new RobotstxtServer(robotstxtConfig, pageFetcher);
        CrawlController controller = null;

        try {
            controller = new CrawlController(config, pageFetcher, robotstxtServer);

            controller.addSeed("http://portalpetrobras.petrobras.com.br"); // FIXME

            controller.start(BasicCrawler.class, numberOfCrawlers);
        } catch (Exception e) {
            e.printStackTrace(); // FIXME
        }


    }

    @Override
    public boolean shouldVisit(Page referringPage, WebURL url) {
        String href = url.getURL().toLowerCase();

        if (IMAGE_EXTENSIONS.matcher(href).matches()) {
            return false;
        }

        return href.startsWith("http://") || href.startsWith("https://"); //FIXME
    }

    @Override
    public void visit(Page page) {
        String url = page.getWebURL().getURL();

        if (page.getParseData() instanceof HtmlParseData) {
            HtmlParseData htmlParseData = (HtmlParseData) page.getParseData();
            String text = htmlParseData.getText();
            System.out.println(text);
        }

        Header[] responseHeaders = page.getFetchResponseHeaders();
        if (responseHeaders != null) {
            logger.debug("Response headers:");
            for (Header header : responseHeaders) {
                logger.debug("\t{}: {}", header.getName(), header.getValue());
            }
        }

        logger.debug("=============");
    }
}
