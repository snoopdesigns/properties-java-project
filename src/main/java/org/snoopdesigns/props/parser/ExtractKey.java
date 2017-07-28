package org.snoopdesigns.props.parser;

public class ExtractKey<T> {

    private ValueParser<T> parser;
    private String value;

    public T parse(String value) {
        return parser.parse(value.trim());
    }

    public ExtractKey(ValueParser<T> parser, String value) {
        this.parser = parser;
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
