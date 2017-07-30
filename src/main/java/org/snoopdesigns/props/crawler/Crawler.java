package org.snoopdesigns.props.crawler;

import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.url.WebURL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.snoopdesigns.props.parser.ApartmentPageParser;
import org.snoopdesigns.props.persistence.entities.Apartment;
import org.snoopdesigns.props.persistence.entities.Complex;
import org.snoopdesigns.props.persistence.repository.ApartmentsRepository;
import org.snoopdesigns.props.persistence.repository.ComplexRepository;

public class Crawler extends WebCrawler {

    private final static Logger logger = LoggerFactory.getLogger(Crawler.class);

    private CrawlParameters crawlParameters;
    private ComplexRepository complexRepository;
    private ApartmentsRepository apartmentsRepository;

    ApartmentPageParser parser = new ApartmentPageParser();

    public Crawler(CrawlParameters crawlParameters, ComplexRepository complexRepository,
                   ApartmentsRepository apartmentsRepository) {
        this.crawlParameters = crawlParameters;
        this.complexRepository = complexRepository;
        this.apartmentsRepository = apartmentsRepository;
    }

    @Override
    public boolean shouldVisit(Page referringPage, WebURL url) {
        String href = url.getURL().toLowerCase();
        boolean isComplexPageCrawl = href.contains("offer_type=newobject") && href.contains("&p=");
        boolean isApartmentInfoPageCrawl = href.contains("spb.cian.ru/sale/flat/");
        boolean isApartmentsListPageCrawl = href.contains("newobject%5b0%5d=") && href.contains("offer_type=flat");
        return isComplexPageCrawl || isApartmentsListPageCrawl || isApartmentInfoPageCrawl;
    }

    @Override
    public void visit(Page page) {
        String url = page.getWebURL().getURL();
        logger.info("URL: " + url);
        if (url.contains("spb.cian.ru/sale/flat/")) {
            Apartment ap = parser.parse(url, new String(page.getContentData()));
            if (ap.getComplex() != null) {
                Complex complex = complexRepository.findByCianId(ap.getComplex().getCianId());
                if (complex == null) {
                    complex = complexRepository.save(ap.getComplex());
                    logger.info("Saved new complex: id = " + complex.getId() + ", cianId = " + complex.getCianId());
                }
                ap.setComplex(complex);
            }
            apartmentsRepository.save(ap);
        }
    }
}
