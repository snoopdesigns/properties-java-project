package org.snoopdesigns.props.parser;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.snoopdesigns.props.persistence.entities.Complex;
import org.springframework.stereotype.Component;

@Component
public class ComplexPageParser extends AbstractParser<Complex> {

    @Override
    public Complex processContents(String url, Element bodyDocument) {
        Complex c = new Complex();
        Elements titleElements = bodyDocument.getElementsByAttributeValueStarting("class", "content-title");
        if (titleElements.size() == 1) {
            c.setName(titleElements.first().children().first().text());
        }
        Elements statusElements = bodyDocument.getElementsByAttributeValueStarting("class", "content-status");
        Elements addressElements = bodyDocument.getElementsByAttributeValueStarting("class", "content-address");
        if (statusElements.size() == 1 && addressElements.size() == 1) {
            c.setAddress(addressElements.first().text());
        }
        return c;
    }
}
