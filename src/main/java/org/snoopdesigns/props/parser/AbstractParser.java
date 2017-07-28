package org.snoopdesigns.props.parser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;

public abstract class AbstractParser<T> {

    public T parse(String contents) {
        Element docBody = Jsoup.parse(contents).body();
        return this.processContents(docBody);
    }
    public abstract T processContents(Element bodyDocument);
}
