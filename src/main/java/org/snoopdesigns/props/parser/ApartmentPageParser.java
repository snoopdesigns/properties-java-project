package org.snoopdesigns.props.parser;

import com.google.code.geocoder.Geocoder;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.snoopdesigns.props.persistence.entities.Apartment;
import org.snoopdesigns.props.persistence.entities.FloorInfo;

public class ApartmentPageParser extends AbstractParser<Apartment> {

    private Geocoder geocoder = new Geocoder();
    private TableDataExtractor tableDataExtractor = new TableDataExtractor();
    private ElementDataExtractor elementDataExtractor = new ElementDataExtractor();
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
    private ExtractKey<Integer> PRICE = new ExtractKey<>(ValueParsers.PRICE_PARSER, "object_descr_price");
    private ExtractKey<String> ADDRESS = new ExtractKey<>(ValueParsers.STRING_PARSER, "object_descr_addr");

    @Override
    public Apartment processContents(Element bodyDocument) {
        Elements infoElements = bodyDocument.getElementsByAttributeValue("class", "object_descr_props flat sale");
        Element infoElement = infoElements.first();

        FloorInfo floor = tableDataExtractor.extractValue(infoElement.child(0), FLOOR_KEY);
        String ht = tableDataExtractor.extractValue(infoElement.child(0), HOUSE_TYPE);
        String st = tableDataExtractor.extractValue(infoElement.child(0), SELL_TYPE);
        Float ta = tableDataExtractor.extractValue(infoElement.child(0), TOTAL_AREA);
        Float ra = tableDataExtractor.extractValue(infoElement.child(0), ROOMS_AREA);
        Float la = tableDataExtractor.extractValue(infoElement.child(0), LIVING_AREA);
        String b = tableDataExtractor.extractValue(infoElement.child(0), BALKONY);
        String elev = tableDataExtractor.extractValue(infoElement.child(0), ELEVATOR);
        String wins = tableDataExtractor.extractValue(infoElement.child(0), WINDOW);
        String cr = tableDataExtractor.extractValue(infoElement.child(0), COMPLEX_READY);
        Integer price = elementDataExtractor.extractValue(bodyDocument, PRICE);
        String address = elementDataExtractor.extractValue(bodyDocument, ADDRESS);

        /*try {
            GeocodeResponse resp = geocoder.geocode(new GeocoderRequest(addrElement.text(), "RU"));
            System.out.println(resp.getResults().get(0).getGeometry().getLocation());
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        return new Apartment(floor, ht, st, ta, ra, la, cr, price, address);
    }
}
