package org.snoopdesigns.props.crawler.nextgen;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.snoopdesigns.props.crawler.nextgen.entities.Apartment;
import org.snoopdesigns.props.crawler.nextgen.entities.Complex;
import org.snoopdesigns.props.parser.ApartmentPageParser;
import org.snoopdesigns.props.parser.ComplexApartmentsPageParser;
import org.snoopdesigns.props.parser.ComplexPageParser;
import org.snoopdesigns.props.parser.ComplexesPageParser;
import org.snoopdesigns.props.services.GeocoderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class CrawlerServiceImpl {

    private final String USER_AGENT = "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/59.0.3071.115 Safari/537.36";

    @Value("${proxyHost:#{null}}")
    private String proxyHost;

    @Value("${proxyPort:#{null}}")
    private Integer proxyPort;

    @Autowired
    private ComplexesPageParser complexesPageParser;

    @Autowired
    private ComplexApartmentsPageParser complexApartmentsPageParser;

    @Autowired
    private ApartmentPageParser apartmentPageParser;

    @Autowired
    private ComplexPageParser complexPageParser;

    @Autowired
    private GeocoderService geocoderService;

    public List<Complex> startCrawling(CrawlParameters parameters) {
        int totalNum = 0;
        List<Complex> complexes = new ArrayList<>();
        try {
            for (int i = 0; i < 2; i++) { // while(true)
                String complexesPageUrl = this.generateComplexesPageUrl(parameters, i + 1);
                List<String> complexIds = this.processComplexesPage(complexesPageUrl, this.getHttpContents(complexesPageUrl));
                for (String complexId : complexIds) {
                    Complex complex = null;
                    for (int j = 0; j < 2; j++) {
                        String complexApartmentsPageUrl = this.generateComplexApartmentsPageUrl(parameters, j + 1, complexId);
                        if (j == 0) {
                            complex = this.processComplexPage(complexApartmentsPageUrl, this.getHttpContents(complexApartmentsPageUrl));
                            complex.setCianId(Integer.valueOf(complexId));
                            complexes.add(complex);
                        }
                        List<String> apartmentUrls = this.processComplexApartmentsPage(complexesPageUrl, this.getHttpContents(complexApartmentsPageUrl));
                        for (String apartmentUrl : apartmentUrls) {
                            Apartment apartment = this.processApartmentPage(apartmentUrl, this.getHttpContents(apartmentUrl));
                            if (totalNum < parameters.getMaxPages()) {
                                apartment.setComplex(complex);
                                complex.getApartments().add(apartment);
                                totalNum++;
                            } else {
                                return complexes;
                            }

                        }
                        if (apartmentUrls.size() < 26) break;
                    }
                }
                if (complexIds.size() < 25) break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return complexes;
    }

    public List<String> processComplexesPage(String url, String contents) {
        return complexesPageParser.parse(url, contents);
    }

    public List<String> processComplexApartmentsPage(String url, String contents) {
        return complexApartmentsPageParser.parse(url, contents);
    }

    public Apartment processApartmentPage(String url, String contents) {
        return apartmentPageParser.parse(url, contents);
    }

    public Complex processComplexPage(String url, String contents) {
        return complexPageParser.parse(url, contents);
    }

    private String generateComplexesPageUrl(CrawlParameters parameters, Integer pageNumber) {
        return String.format("/newobjects/list/?deal_type=sale&engine_version=2&offer_type=newobject&region=-2&room1=1&p=%s", pageNumber);
    }

    private String generateComplexApartmentsPageUrl(CrawlParameters parameters, Integer pageNumber, String complexId) {
        return String.format("/cat.php?deal_type=sale&engine_version=2&from_developer=1&newobject[0]=%s&offer_type=flat&room1=1&p=%s", complexId, pageNumber);
    }

    private String getHttpContents(String url) throws Exception {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpHost target = new HttpHost("www.cian.ru", 80, "http");
        HttpGet request = new HttpGet(url);
        request.addHeader("User-Agent", USER_AGENT);
        if (proxyHost != null && proxyPort != null) {
            HttpHost proxy = new HttpHost(proxyHost, proxyPort, "http");
            RequestConfig config = RequestConfig.custom()
                    .setProxy(proxy)
                    .build();
            request.setConfig(config);
        }

        try (CloseableHttpResponse response = httpclient.execute(target, request)) {
            System.out.println("----------------------------------------");
            System.out.println("http://cian.ru" + url);
            System.out.println(response.getStatusLine());
            return EntityUtils.toString(response.getEntity());
        }
    }

    public static class CrawlParameters {

        private Integer maxPages;
        private boolean singleRoom;
        private boolean doubleRoom;

        public CrawlParameters(Integer maxPages, boolean singleRoom, boolean doubleRoom) {
            this.maxPages = maxPages;
            this.singleRoom = singleRoom;
            this.doubleRoom = doubleRoom;
        }

        public Integer getMaxPages() {
            return maxPages;
        }

        public boolean isSingleRoom() {
            return singleRoom;
        }

        public boolean isDoubleRoom() {
            return doubleRoom;
        }
    }
}
