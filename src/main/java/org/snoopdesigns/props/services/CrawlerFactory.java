package org.snoopdesigns.props.services;

import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import org.springframework.data.repository.CrudRepository;

public class CrawlerFactory implements CrawlController.WebCrawlerFactory{

    CrudRepository repository;

    public CrawlerFactory(CrudRepository repository) {
        this.repository = repository;
    }

    @Override
    public WebCrawler newInstance() throws Exception {
        return new Crawler(this.repository);
    }
}
