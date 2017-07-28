package org.snoopdesigns.props.parser.extractor;

public class ExtractKey<T> {

    private ValueParser<T> parser;
    private String value;

    public T parse(String value) {
        if (value != null) {
            return parser.parse(value.trim());
        } else {
            return null;
        }
    }

    public ExtractKey(ValueParser<T> parser, String value) {
        this.parser = parser;
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
