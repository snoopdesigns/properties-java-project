package org.snoopdesigns.props.parser.extractor;

import org.jsoup.nodes.Element;

public class ElementHtmlExtractor extends DataExtractor {
    @Override
    protected <T> String extractString(Element element, ExtractKey<T> extractKey) {
        return element.getElementsByAttributeValue("class", extractKey.getValue()).first().html();
    }
}
