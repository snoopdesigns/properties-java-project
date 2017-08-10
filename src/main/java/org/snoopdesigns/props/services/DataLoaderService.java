package org.snoopdesigns.props.services;

import java.util.List;

import org.snoopdesigns.props.crawler.nextgen.CrawlerServiceImpl;
import org.snoopdesigns.props.crawler.nextgen.entities.Complex;
import org.snoopdesigns.props.ml.DataPreprocessor;
import org.snoopdesigns.props.persistence.PersistenceServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class DataLoaderService {

    @Autowired
    private PersistenceServiceImpl persistenceService;

    @Autowired
    private CrawlerServiceImpl crawlerService;

    @Autowired
    private DataPreprocessor dataPreprocessor;

    @Value("${proxyHost:#{null}}")
    private String proxyHost;

    @Value("${proxyPort:#{null}}")
    private Integer proxyPort;

    public void loadData() {
        List<Complex> complexes = this.crawlerService.startCrawling(new CrawlerServiceImpl.CrawlParameters(10, true, false));
        complexes.forEach(c -> dataPreprocessor.preprocessData(c));
        complexes.forEach(c -> persistenceService.saveComplex(c));
    }
}
