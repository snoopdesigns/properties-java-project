package org.snoopdesigns.props.parser.extractor;

public interface ValueParser<T> {
    T parse(String value);
}
