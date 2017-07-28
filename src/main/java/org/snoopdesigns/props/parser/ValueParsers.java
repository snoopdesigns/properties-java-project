package org.snoopdesigns.props.parser;

public class ValueParsers {

    public static final ValueParser<FloorInfo> FLOOR_PARSER = new ValueParser<FloorInfo>() {
        @Override
        public FloorInfo parse(String value) {
            String[] floorInfo = value.replaceAll("\u00A0","").split("/");
            return new FloorInfo(Integer.valueOf(floorInfo[0]), Integer.valueOf(floorInfo[1]));
        }
    };

    public static final ValueParser<Float> AREA_PARSER = new ValueParser<Float>() {
        @Override
        public Float parse(String value) {
            return Float.valueOf(value.replace("\u00A0"," ").replaceAll(",",".").split(" ")[0]);
        }
    };

    public static final ValueParser<Integer> PRICE_PARSER = new ValueParser<Integer>() {
        @Override
        public Integer parse(String value) {
            return Integer.valueOf(value.replace("\u00A0"," ").replace("руб.", "").replaceAll(" ", "").split(" ")[0]);
        }
    };

    public static final ValueParser<String> STRING_PARSER = new ValueParser<String>() {
        @Override
        public String parse(String value) {
            return value;
        }
    };
}