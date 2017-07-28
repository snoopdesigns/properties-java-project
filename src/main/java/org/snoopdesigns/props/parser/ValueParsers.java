package org.snoopdesigns.props.parser;

public class ValueParsers {

    public static final ValueParser<Integer> INTEGER_PARSER = new ValueParser<Integer>() {
        @Override
        public Integer parse(String value) {
            return Integer.valueOf(value);
        }
    };

    public static final ValueParser<String> STRING_PARSER = new ValueParser<String>() {
        @Override
        public String parse(String value) {
            return value;
        }
    };
}
