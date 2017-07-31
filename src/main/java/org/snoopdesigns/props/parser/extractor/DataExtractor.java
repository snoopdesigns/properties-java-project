package org.snoopdesigns.props.parser.extractor;

import org.jsoup.nodes.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class DataExtractor {

    private final static Logger logger = LoggerFactory.getLogger(DataExtractor.class);

    public <T> T extractValue(Element element, ExtractKey<T> extractKey) {
        try {
            String val = this.extractString(element, extractKey);
            return extractKey.parse(val);
        } catch (Exception e) {
            logger.warn("Unable to extract field: " + extractKey.getValue());
            return null;
        }
    }

    protected abstract <T> String extractString(Element element, ExtractKey<T> extractKey);
}
