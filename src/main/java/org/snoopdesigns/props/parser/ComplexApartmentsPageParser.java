package org.snoopdesigns.props.parser;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

@Component
public class ComplexApartmentsPageParser extends AbstractParser<List<String>> {
    @Override
    public List<String> processContents(String url, Element bodyDocument) {
        List<String> result = new ArrayList<>();
        Elements apartmentElements = bodyDocument.getElementsByAttributeValueStarting("class", "offer--");
        for (Element apartmentElement : apartmentElements) {
            if (apartmentElement.children().size() > 0) {
                if (apartmentElement.children().first().tag().getName().equals("a")) {
                    String apartmentUrl = apartmentElement.children().first().attributes().get("href");
                    if (apartmentUrl != null) {
                        result.add(apartmentUrl);
                    }
                }
            }
        }
        return result;
    }
}
