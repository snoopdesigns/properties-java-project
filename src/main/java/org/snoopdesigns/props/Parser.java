package org.snoopdesigns.props;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

import com.google.code.geocoder.Geocoder;
import com.google.code.geocoder.model.GeocodeResponse;
import com.google.code.geocoder.model.GeocoderRequest;
import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Parser {

    public static void main(String[] args) throws Exception {
        Parser parser = new Parser();
        String contents = parser.readContents("https://spb.cian.ru/sale/flat/150295433/");
        //System.out.println(contents);
        parser.parse(contents);
    }

    public void parse(String contents) throws IOException {
        Element docBody = Jsoup.parse(contents).body();
        Elements infoElements = docBody.getElementsByAttributeValue("class", "object_descr_props flat sale");
        Element infoElement = infoElements.first();
        for (Element element : infoElement.child(0).children()) {
            System.out.println(element.text());
        }

        Element addrElement = docBody.getElementsByAttributeValue("class", "object_descr_addr").first();
        System.out.println(addrElement.text());
        Element priceElement = docBody.getElementsByAttributeValue("class", "object_descr_price").first();
        System.out.println(priceElement.text());

        Geocoder geocoder = new Geocoder();
        GeocodeResponse resp = geocoder.geocode(new GeocoderRequest(addrElement.text(), "RU"));
        System.out.println(resp.getResults().get(0).getGeometry().getLocation());

    }

    public String readContents(String url) throws Exception {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        try {
            HttpHost target = new HttpHost("spb.cian.ru", 80, "http");
            HttpHost proxy = new HttpHost("proxy.t-systems.ru", 3128, "http");

            RequestConfig config = RequestConfig.custom().setProxy(proxy).build();
            HttpGet request = new HttpGet("/sale/flat/150295433/");
            request.setConfig(config);
            CloseableHttpResponse response = httpclient.execute(target, request);
            response.getEntity().getContent();
            return new BufferedReader(new InputStreamReader(response.getEntity().getContent())).lines().collect(Collectors.joining("\n"));
        } finally {
            httpclient.close();
        }
    }
}
