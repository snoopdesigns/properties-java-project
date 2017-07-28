package org.snoopdesigns.props.parser;

import org.jsoup.nodes.Element;

public class ElementDataExtractor extends DataExtractor {

    @Override
    protected <T> String extractString(Element element, ExtractKey<T> extractKey) {
        return element.getElementsByAttributeValue("class", extractKey.getValue()).first().text();
    }
}
