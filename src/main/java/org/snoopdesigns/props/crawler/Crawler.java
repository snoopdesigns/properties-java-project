package org.snoopdesigns.props.crawler;

import java.io.IOException;

import com.google.code.geocoder.Geocoder;
import com.google.code.geocoder.model.GeocodeResponse;
import com.google.code.geocoder.model.GeocoderRequest;
import com.google.code.geocoder.model.GeocoderStatus;
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

    private ApartmentPageParser parser = new ApartmentPageParser();
    private Geocoder geocoder = new Geocoder();

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
                    try {
                        GeocodeResponse resp = geocoder.geocode(new GeocoderRequest(ap.getComplex().getAddress(), "RU"));
                        if (resp.getStatus().equals(GeocoderStatus.OK)) {
                            logger.info("Location: " + resp.getResults().get(0).getGeometry().getLocation());
                            ap.getComplex().setLat(resp.getResults().get(0).getGeometry().getLocation().getLat().floatValue());
                            ap.getComplex().setLng(resp.getResults().get(0).getGeometry().getLocation().getLng().floatValue());
                        } else {
                            logger.warn("Unable to geocode location: " + ap.getComplex().getAddress());
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    complex = complexRepository.save(ap.getComplex());
                    logger.info("Saved new complex: id = " + complex.getId() + ", cianId = " + complex.getCianId());
                }
                ap.setComplex(complex);
            }
            apartmentsRepository.save(ap);
        }
    }
}
