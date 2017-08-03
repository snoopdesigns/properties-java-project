package org.snoopdesigns.props.parser.extractor;

import org.snoopdesigns.props.persistence.entities.FloorInfo;

public class ValueParsers {

    public static final ValueParser<FloorInfo> FLOOR_PARSER = new ValueParser<FloorInfo>() {
        @Override
        public FloorInfo parse(String value) {
            value = value.replaceAll("\u00A0","");
            if (value.contains("(")) { // contains 'Квартиры на других этажах'
                value = value.substring(0, value.indexOf("(") - 1);
            }
            String[] floorInfo = value.replaceAll("\u00A0","").split("/");
            return new FloorInfo(Integer.valueOf(floorInfo[0]), Integer.valueOf(floorInfo[1]));
        }
    };

    public static final ValueParser<Float> AREA_PARSER = new ValueParser<Float>() {
        @Override
        public Float parse(String value) {
            String replaced = value.replace("\u00A0"," ");
            if (replaced.matches(".*\\d+.*")) {
                if (replaced.contains("-")) {
                    replaced = replaced.substring(0, replaced.indexOf("-"));
                }
                return Float.valueOf(replaced.replaceAll(",", ".").split(" ")[0]);
            } else {
                return null;
            }
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

    public static final ValueParser<Integer> COMPLEX_ID_PARSER = new ValueParser<Integer>() {
        @Override
        public Integer parse(String value) {
            value = value.substring(value.indexOf("<a href=") + 9, value.indexOf("/\"") + 1);
            value = value.substring(value.lastIndexOf("-"), value.length()-1);
            value = value.replaceAll("[^\\d.]", "");
            return Integer.valueOf(value);
        }
    };

    public static final ValueParser<String> COMPLEX_NAME_PARSER = new ValueParser<String>() {
        @Override
        public String parse(String value) {
            return value.substring(value.indexOf("\">")+2, value.indexOf("</a>"));
        }
    };
}
