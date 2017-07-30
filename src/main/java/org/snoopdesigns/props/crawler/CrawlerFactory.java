package org.snoopdesigns.props.crawler;

import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import org.snoopdesigns.props.persistence.repository.ApartmentsRepository;
import org.snoopdesigns.props.persistence.repository.ComplexRepository;

public class CrawlerFactory implements CrawlController.WebCrawlerFactory{

    private ComplexRepository complexRepository;
    private ApartmentsRepository apartmentsRepository;
    private CrawlParameters crawlParameters;

    public CrawlerFactory(CrawlParameters crawlParameters, ComplexRepository complexRepository,
                          ApartmentsRepository apartmentsRepository) {
        this.crawlParameters = crawlParameters;
        this.complexRepository = complexRepository;
        this.apartmentsRepository = apartmentsRepository;
    }

    @Override
    public WebCrawler newInstance() throws Exception {
        return new Crawler(this.crawlParameters, this.complexRepository, this.apartmentsRepository);
    }
}
