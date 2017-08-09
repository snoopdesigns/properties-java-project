package org.snoopdesigns.props.crawler.nextgen;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.snoopdesigns.props.parser.AbstractParser;
import org.springframework.stereotype.Component;

@Component
public class ComplexApartmentsPageParser extends AbstractParser<List<String>> {
    @Override
    public List<String> processContents(String url, Element bodyDocument) {
        List<String> result = new ArrayList<>();
        Elements apartmentElements = bodyDocument.getElementsByAttributeValueStarting("class", "offer--");
        for (Element apartmentElement : apartmentElements) {
            if (apartmentElement.children().size() > 0) {
                String apartmentUrl = apartmentElement.children().first().attributes().get("href");
                if (apartmentUrl != null) {
                    result.add(apartmentUrl);
                }
            }
        }
        return result;
    }
}
