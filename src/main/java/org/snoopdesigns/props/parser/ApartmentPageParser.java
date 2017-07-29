package org.snoopdesigns.props.parser;

import java.io.IOException;

import com.google.code.geocoder.Geocoder;
import com.google.code.geocoder.model.GeocodeResponse;
import com.google.code.geocoder.model.GeocoderRequest;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.snoopdesigns.props.parser.extractor.ElementDataExtractor;
import org.snoopdesigns.props.parser.extractor.ExtractKey;
import org.snoopdesigns.props.parser.extractor.HrefDataExtractor;
import org.snoopdesigns.props.parser.extractor.TableDataExtractor;
import org.snoopdesigns.props.parser.extractor.ValueParsers;
import org.snoopdesigns.props.persistence.entities.Apartment;
import org.snoopdesigns.props.persistence.entities.Complex;
import org.snoopdesigns.props.persistence.entities.FloorInfo;

public class ApartmentPageParser extends AbstractParser<Apartment> {

    private final static Logger logger = LoggerFactory.getLogger(ApartmentPageParser.class);

    private Geocoder geocoder = new Geocoder();
    private TableDataExtractor tableDataExtractor = new TableDataExtractor();
    private ElementDataExtractor elementDataExtractor = new ElementDataExtractor();
    private HrefDataExtractor hrefDataExtractor = new HrefDataExtractor();
    private ExtractKey<FloorInfo> FLOOR_KEY = new ExtractKey<>(ValueParsers.FLOOR_PARSER, "этаж");
    private ExtractKey<String> HOUSE_TYPE = new ExtractKey<>(ValueParsers.STRING_PARSER, "тип дома");
    private ExtractKey<String> SELL_TYPE = new ExtractKey<>(ValueParsers.STRING_PARSER, "тип продажи");
    private ExtractKey<Float> TOTAL_AREA = new ExtractKey<>(ValueParsers.AREA_PARSER, "общая площадь");
    private ExtractKey<Float> ROOMS_AREA = new ExtractKey<>(ValueParsers.AREA_PARSER, "площадь комнат");
    private ExtractKey<Float> LIVING_AREA = new ExtractKey<>(ValueParsers.AREA_PARSER, "жилая площадь");
    private ExtractKey<String> BALKONY = new ExtractKey<>(ValueParsers.STRING_PARSER, "балкон");
    private ExtractKey<String> ELEVATOR = new ExtractKey<>(ValueParsers.STRING_PARSER, "лифт");
    private ExtractKey<String> WINDOW = new ExtractKey<>(ValueParsers.STRING_PARSER, "вид из окна");
    private ExtractKey<String> COMPLEX_READY = new ExtractKey<>(ValueParsers.STRING_PARSER, "сдача гк");
    private ExtractKey<String> PHONE = new ExtractKey<>(ValueParsers.STRING_PARSER, "телефон");
    private ExtractKey<String> REPAIRS = new ExtractKey<>(ValueParsers.STRING_PARSER, "ремонт");
    private ExtractKey<Integer> PRICE = new ExtractKey<>(ValueParsers.PRICE_PARSER, "object_descr_price");
    private ExtractKey<String> ADDRESS = new ExtractKey<>(ValueParsers.STRING_PARSER, "object_descr_addr");

    private ExtractKey<Integer> COMPLEX = new ExtractKey<>(ValueParsers.COMPLEX_ID_PARSER, "object_descr_title");

    @Override
    public Apartment processContents(String url, Element bodyDocument) {
        Elements infoElements = bodyDocument.getElementsByAttributeValue("class", "object_descr_props flat sale");
        Element infoElement = infoElements.first();

        Apartment apartment = new Apartment();

        apartment.setFloorInfo(tableDataExtractor.extractValue(infoElement.child(0), FLOOR_KEY));
        apartment.setHouseType(tableDataExtractor.extractValue(infoElement.child(0), HOUSE_TYPE));
        apartment.setSellType(tableDataExtractor.extractValue(infoElement.child(0), SELL_TYPE));
        apartment.setTotalArea(tableDataExtractor.extractValue(infoElement.child(0), TOTAL_AREA));
        apartment.setRoomsArea(tableDataExtractor.extractValue(infoElement.child(0), ROOMS_AREA));
        apartment.setLivingArea(tableDataExtractor.extractValue(infoElement.child(0), LIVING_AREA));
        String b = tableDataExtractor.extractValue(infoElement.child(0), BALKONY);
        String elev = tableDataExtractor.extractValue(infoElement.child(0), ELEVATOR);
        apartment.setWindow(tableDataExtractor.extractValue(infoElement.child(0), WINDOW));
        String cr = tableDataExtractor.extractValue(infoElement.child(0), COMPLEX_READY);
        apartment.setPhone(tableDataExtractor.extractValue(infoElement.child(0), PHONE));
        apartment.setRepairs(tableDataExtractor.extractValue(infoElement.child(0), REPAIRS));
        apartment.setPrice(elementDataExtractor.extractValue(bodyDocument, PRICE));
        apartment.setAddress(elementDataExtractor.extractValue(bodyDocument, ADDRESS));

        Integer complexId = hrefDataExtractor.extractValue(bodyDocument, COMPLEX);
        apartment.setComplex(new Complex(complexId));
        apartment.setCianId(url.substring(url.indexOf("flat/") + 5, url.length() - 1));

        try {
            GeocodeResponse resp = geocoder.geocode(new GeocoderRequest(apartment.getAddress(), "RU"));
            System.out.println(resp.getResults().get(0).getGeometry().getLocation());
            apartment.setLat(resp.getResults().get(0).getGeometry().getLocation().getLat().floatValue());
            apartment.setLng(resp.getResults().get(0).getGeometry().getLocation().getLng().floatValue());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return apartment;
    }
}
