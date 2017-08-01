package org.snoopdesigns.props.crawler;

import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import org.snoopdesigns.props.persistence.repository.ApartmentsRepository;
import org.snoopdesigns.props.persistence.repository.ComplexRepository;
import org.snoopdesigns.props.services.GeocoderService;

public class CrawlerFactory implements CrawlController.WebCrawlerFactory{

    private ComplexRepository complexRepository;
    private ApartmentsRepository apartmentsRepository;
    private CrawlParameters crawlParameters;
    private GeocoderService geocoderService;

    public CrawlerFactory(CrawlParameters crawlParameters, ComplexRepository complexRepository,
                          ApartmentsRepository apartmentsRepository, GeocoderService geocoderService) {
        this.crawlParameters = crawlParameters;
        this.complexRepository = complexRepository;
        this.apartmentsRepository = apartmentsRepository;
        this.geocoderService = geocoderService;
    }

    @Override
    public WebCrawler newInstance() throws Exception {
        return new Crawler(this.crawlParameters, this.complexRepository, this.apartmentsRepository, this.geocoderService);
    }
}
