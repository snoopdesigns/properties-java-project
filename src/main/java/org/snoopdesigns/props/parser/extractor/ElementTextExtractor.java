package org.snoopdesigns.props.parser.extractor;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ElementTextExtractor extends DataExtractor {

    @Override
    protected <T> String extractString(Element element, ExtractKey<T> extractKey) {
        Elements elements = element.getElementsByAttributeValue("class", extractKey.getValue());
        if (!elements.isEmpty()) {
            return elements.first().text();
        } else {
            return null;
        }
    }
}
