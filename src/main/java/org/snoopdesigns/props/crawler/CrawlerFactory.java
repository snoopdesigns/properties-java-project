package org.snoopdesigns.props.crawler;

import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import org.snoopdesigns.props.persistence.repository.ApartmentsRepository;
import org.snoopdesigns.props.persistence.repository.ComplexRepository;

public class CrawlerFactory implements CrawlController.WebCrawlerFactory{

    ComplexRepository complexRepository;
    ApartmentsRepository apartmentsRepository;

    public CrawlerFactory(ComplexRepository complexRepository, ApartmentsRepository apartmentsRepository) {
        this.complexRepository = complexRepository;
        this.apartmentsRepository = apartmentsRepository;
    }

    @Override
    public WebCrawler newInstance() throws Exception {
        return new Crawler(this.complexRepository, this.apartmentsRepository);
    }
}
