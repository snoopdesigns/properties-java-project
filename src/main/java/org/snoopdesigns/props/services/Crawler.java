package org.snoopdesigns.props.services;

import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.url.WebURL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.snoopdesigns.props.persistence.entities.Complex;
import org.springframework.data.repository.CrudRepository;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class Crawler extends WebCrawler {

    private final static Logger logger = LoggerFactory.getLogger(Crawler.class);

    private CrudRepository complexRepository;

    public Crawler(CrudRepository complexRepository) {
        this.complexRepository = complexRepository;
    }

    @Override
    public boolean shouldVisit(Page referringPage, WebURL url) {
        String href = url.getURL().toLowerCase();
        return href.startsWith("https://www.cian.ru") && href.contains("newobject%5b0%5d=");
    }

    @Override
    public void visit(Page page) {
        String url = page.getWebURL().getURL();
        logger.info("URL: " + url);
        try {
            Pattern p = Pattern.compile("newobject%5B0%5D=([^&]+)");
            Matcher m = p.matcher(url);
            while (m.find()) {
                String number = m.group().split("=")[1];
                logger.info("Number: " + number);
                complexRepository.save(new Complex(number));
            }
        } catch (PatternSyntaxException ex) {
            // error handling
        }
    }
}
