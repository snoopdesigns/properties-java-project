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
import org.springframework.stereotype.Component;

@Component
public class DataLoaderService {

    @Autowired
    private ComplexRepository complexRepository;

    @Autowired
    private ApartmentsRepository apartmentsRepository;

    public void loadData() throws Exception {

        String crawlStorageFolder = "/home/dimka/crawler";
        this.deleteCrawlDirectory(new File(crawlStorageFolder));
        int numberOfCrawlers = 1;

        CrawlConfig config = new CrawlConfig();
        config.setCrawlStorageFolder(crawlStorageFolder);
        config.setResumableCrawling(true);
        config.setMaxPagesToFetch(150);

        PageFetcher pageFetcher = new PageFetcher(config);
        RobotstxtConfig robotstxtConfig = new RobotstxtConfig();
        robotstxtConfig.setEnabled(false);
        RobotstxtServer robotstxtServer = new RobotstxtServer(robotstxtConfig, pageFetcher);
        CrawlController controller = new CrawlController(config, pageFetcher, robotstxtServer);

        controller.addSeed("https://www.cian.ru/newobjects/list/?deal_type=sale&engine_version=2&offer_type=newobject&p=1&region=-2&room1=1");
        CrawlParameters crawlParameters = new CrawlParameters(true, false);
        controller.start(new CrawlerFactory(crawlParameters, complexRepository, apartmentsRepository), numberOfCrawlers);
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
