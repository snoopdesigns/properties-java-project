package org.snoopdesigns.props.crawler;

import java.util.stream.IntStream;

import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;
import org.snoopdesigns.props.persistence.repository.ApartmentsRepository;
import org.snoopdesigns.props.persistence.repository.ComplexRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DataLoader {

    @Autowired
    private ComplexRepository complexRepository;

    @Autowired
    private ApartmentsRepository apartmentsRepository;

    public void loadData(Integer maxPages) throws Exception {

        String crawlStorageFolder = "/data/crawl/root";
        int numberOfCrawlers = 1;

        CrawlConfig config = new CrawlConfig();
        config.setCrawlStorageFolder(crawlStorageFolder);
        config.setMaxDepthOfCrawling(3);
        config.setMaxPagesToFetch(150);

        PageFetcher pageFetcher = new PageFetcher(config);
        RobotstxtConfig robotstxtConfig = new RobotstxtConfig();
        robotstxtConfig.setEnabled(false);
        RobotstxtServer robotstxtServer = new RobotstxtServer(robotstxtConfig, pageFetcher);
        CrawlController controller = new CrawlController(config, pageFetcher, robotstxtServer);

        for (Integer page : IntStream.range(1, maxPages+1).toArray()) {
            controller.addSeed(String.format("https://www.cian.ru/newobjects/list/?deal_type=sale&engine_version=2&offer_type=newobject&p=%d&region=-2&room1=1", page));
        }
        controller.start(new CrawlerFactory(complexRepository, apartmentsRepository), numberOfCrawlers);
    }
}
