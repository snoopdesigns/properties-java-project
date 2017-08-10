package org.snoopdesigns.props.parser;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

@Component
public class ComplexesPageParser extends AbstractParser<List<String>> {

    @Override
    public List<String> processContents(String url, Element bodyDocument) {
        List<String> result = new ArrayList<>();
        Elements complexesListElements = bodyDocument.getElementsByAttributeValue("class", "serp-list");
        if (complexesListElements.size() == 1) {
            Element complexesList = complexesListElements.first();
            for (Element complexElement : complexesList.children()) {
                Elements complexLinkElements = complexElement.getElementsByAttributeValue("class", "serp-item__card-link serp-item__newobject__link link");
                if (complexLinkElements.size() == 1) {
                    Element complexesLinkElement = complexLinkElements.first();
                    String complexUrl = complexesLinkElement.attributes().get("href");
                    if (complexUrl != null) {
                        try {
                            String decodedUrl = URLDecoder.decode(complexUrl, "UTF-8");
                            decodedUrl = decodedUrl.substring(decodedUrl.indexOf("newobject[0]=") + 13, decodedUrl.length());
                            decodedUrl = decodedUrl.substring(0, decodedUrl.indexOf("&"));
                            //System.out.println(decodedUrl);
                            result.add(decodedUrl);
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        return result;
    }
}
