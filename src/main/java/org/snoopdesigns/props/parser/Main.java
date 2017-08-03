package org.snoopdesigns.props.parser;

import java.io.BufferedReader;
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
import org.snoopdesigns.props.persistence.entities.Apartment;

public class Main {

    public static void main(String[] args) throws Exception {

        System.setProperty("http.proxyHost", "proxy.t-systems.ru");
        System.setProperty("http.proxyPort", "3128");
        System.setProperty("https.proxyHost", "proxy.t-systems.ru");
        System.setProperty("https.proxyPort", "3128");
        Geocoder geocoder = new Geocoder();
        GeocodeResponse resp = geocoder.geocode(new GeocoderRequest("Ленинградская область, Тосненский район, Федоровское с/пос, Есенин вилладж кп", "RU"));

        ApartmentPageParser parser = new ApartmentPageParser();
        String url = "https://spb.cian.ru/sale/flat/150296416/";
        String contents = readContents(url);
        Apartment ap = parser.parse(url, contents);
        System.out.println(ap);
    }

    public static String readContentsTest(String url) throws Exception {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        try {
            HttpGet request = new HttpGet(url);
            CloseableHttpResponse response = httpclient.execute(request);
            return new BufferedReader(new InputStreamReader(response.getEntity().getContent())).lines().collect(Collectors.joining("\n"));
        } finally {
            httpclient.close();
        }
    }

    public static String readContents(String url) throws Exception {
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
