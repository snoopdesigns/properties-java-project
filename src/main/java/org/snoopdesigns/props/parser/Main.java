package org.snoopdesigns.props.parser;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class Main {

    public static void main(String[] args) throws Exception {
        ApartmentPageParser parser = new ApartmentPageParser();
        String contents = readContents("https://spb.cian.ru/sale/flat/150295433/");
        Apartment ap = parser.parse(contents);
        System.out.println(ap);
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
