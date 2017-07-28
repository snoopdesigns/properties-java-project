package org.snoopdesigns.props.parser.extractor;

import org.jsoup.nodes.Element;

public class HrefDataExtractor extends DataExtractor {
    @Override
    protected <T> String extractString(Element element, ExtractKey<T> extractKey) {
        return element.getElementsByAttributeValue("class", extractKey.getValue()).first().child(0).attr("href");
    }
}
