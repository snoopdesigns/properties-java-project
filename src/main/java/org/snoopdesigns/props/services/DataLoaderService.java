package org.snoopdesigns.props.services;

import java.io.File;

import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;
import org.snoopdesigns.props.crawler.CrawlParameters;
import org.snoopdesigns.props.crawler.CrawlerFactory;
import org.snoopdesigns.props.persistence.repository.ApartmentsRepository;
import org.snoopdesigns.props.persistence.repository.ComplexRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class DataLoaderService {

    @Autowired
    private ComplexRepository complexRepository;

    @Autowired
    private ApartmentsRepository apartmentsRepository;

    @Autowired
    private GeocoderService geocoderService;

    @Value("${proxyHost:#{null}}")
    private String proxyHost;

    @Value("${proxyPort:#{null}}")
    private Integer proxyPort;

    public void loadData() throws Exception {

        String crawlStorageFolder = "/home/dimka/crawler";
        this.deleteCrawlDirectory(new File(crawlStorageFolder));
        int numberOfCrawlers = 1;

        CrawlConfig config = new CrawlConfig();
        config.setCrawlStorageFolder(crawlStorageFolder);
        config.setResumableCrawling(true);
        config.setMaxPagesToFetch(300);
        if (proxyHost != null && proxyPort != null) {
            config.setProxyHost(proxyHost);
            config.setProxyPort(proxyPort);
        }
        config.setUserAgentString("Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/59.0.3071.115 Safari/537.36");

        PageFetcher pageFetcher = new PageFetcher(config);
        RobotstxtConfig robotstxtConfig = new RobotstxtConfig();
        robotstxtConfig.setEnabled(false);
        RobotstxtServer robotstxtServer = new RobotstxtServer(robotstxtConfig, pageFetcher);
        CrawlController controller = new CrawlController(config, pageFetcher, robotstxtServer);

        controller.addSeed("https://www.cian.ru/newobjects/list/?deal_type=sale&engine_version=2&offer_type=newobject&p=1&region=-2&room1=1");
        CrawlParameters crawlParameters = new CrawlParameters(true, false);
        controller.startNonBlocking(new CrawlerFactory(crawlParameters, complexRepository, apartmentsRepository, geocoderService), numberOfCrawlers);
    }

    private boolean deleteCrawlDirectory(File directory) {
        if(directory.exists()) {
            File[] files = directory.listFiles();
            if(null!=files){
                for(int i=0; i<files.length; i++) {
                    if(files[i].isDirectory()) {
                        deleteCrawlDirectory(files[i]);
                    } else {
                        files[i].delete();
                    }
                }
            }
        }
        return(directory.delete());
    }
}
