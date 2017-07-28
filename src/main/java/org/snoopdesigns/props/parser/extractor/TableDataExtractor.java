package org.snoopdesigns.props.parser.extractor;

import org.jsoup.nodes.Element;

public class TableDataExtractor extends DataExtractor {

    @Override
    public <T> String extractString(Element element, ExtractKey<T> extractKey) {
        for (Element elem : element.children()) {
            if (elem.child(0).text().toLowerCase().contains(extractKey.getValue().toLowerCase())) {
                return elem.child(1).text();
            }
        }
        return null;
    }
}
