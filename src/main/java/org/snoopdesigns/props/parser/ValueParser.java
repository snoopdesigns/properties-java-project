package org.snoopdesigns.props.parser;

public interface ValueParser<T> {
    T parse(String value);
}
