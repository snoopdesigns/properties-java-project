package org.snoopdesigns.props.parser;

import org.jsoup.nodes.Element;

public abstract class DataExtractor {

    public <T> T extractValue(Element element, ExtractKey<T> extractKey) {
        String val = this.extractString(element, extractKey);
        return extractKey.parse(val);
    }

    protected abstract <T> String extractString(Element element, ExtractKey<T> extractKey);
}
