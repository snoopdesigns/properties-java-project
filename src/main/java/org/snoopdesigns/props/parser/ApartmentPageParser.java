package org.snoopdesigns.props.parser;

import com.google.code.geocoder.Geocoder;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ApartmentPageParser extends AbstractParser<Apartment> {

    private Geocoder geocoder = new Geocoder();
    private TableDataExtractor tableDataExtractor = new TableDataExtractor();
    private ExtractKey<String> FLOOR_KEY = new ExtractKey<>(ValueParsers.STRING_PARSER, "этаж");
    private ExtractKey<String> HOUSE_TYPE = new ExtractKey<>(ValueParsers.STRING_PARSER, "тип дома");
    private ExtractKey<String> SELL_TYPE = new ExtractKey<>(ValueParsers.STRING_PARSER, "тип продажи");
    private ExtractKey<String> TOTAL_AREA = new ExtractKey<>(ValueParsers.STRING_PARSER, "общая площадь");
    private ExtractKey<String> ROOMS_AREA = new ExtractKey<>(ValueParsers.STRING_PARSER, "площадь комнат");
    private ExtractKey<String> LIVING_AREA = new ExtractKey<>(ValueParsers.STRING_PARSER, "жилая площадь");
    private ExtractKey<String> BALKONY = new ExtractKey<>(ValueParsers.STRING_PARSER, "балкон");
    private ExtractKey<String> ELEVATOR = new ExtractKey<>(ValueParsers.STRING_PARSER, "лифт");
    private ExtractKey<String> WINDOW = new ExtractKey<>(ValueParsers.STRING_PARSER, "вид из окна");
    private ExtractKey<String> COMPLEX_READY = new ExtractKey<>(ValueParsers.STRING_PARSER, "сдача гк");

    @Override
    public Apartment processContents(Element bodyDocument) {
        Elements infoElements = bodyDocument.getElementsByAttributeValue("class", "object_descr_props flat sale");
        Element infoElement = infoElements.first();

        String floor = tableDataExtractor.extractValue(infoElement.child(0), FLOOR_KEY);
        String ht = tableDataExtractor.extractValue(infoElement.child(0), HOUSE_TYPE);
        String st = tableDataExtractor.extractValue(infoElement.child(0), SELL_TYPE);
        String ta = tableDataExtractor.extractValue(infoElement.child(0), TOTAL_AREA);
        String ra = tableDataExtractor.extractValue(infoElement.child(0), ROOMS_AREA);
        String la = tableDataExtractor.extractValue(infoElement.child(0), LIVING_AREA);
        String b = tableDataExtractor.extractValue(infoElement.child(0), BALKONY);
        String elev = tableDataExtractor.extractValue(infoElement.child(0), ELEVATOR);
        String wins = tableDataExtractor.extractValue(infoElement.child(0), WINDOW);
        String cr = tableDataExtractor.extractValue(infoElement.child(0), COMPLEX_READY);
        System.out.println("Этаж: " + floor);
        System.out.println("Этаж: " + ht);
        System.out.println("Этаж: " + st);
        System.out.println("Этаж: " + ta);
        System.out.println("Этаж: " + ra);
        System.out.println("Этаж: " + la);
        System.out.println("Этаж: " + b);
        System.out.println("Этаж: " + elev);
        System.out.println("Этаж: " + wins);
        System.out.println("Этаж: " + cr);

        /*Element addrElement = bodyDocument.getElementsByAttributeValue("class", "object_descr_addr").first();
        System.out.println(addrElement.text());*/
        /*Element priceElement = bodyDocument.getElementsByAttributeValue("class", "object_descr_price").first();
        System.out.println(priceElement.text());*/

        /*try {
            GeocodeResponse resp = geocoder.geocode(new GeocoderRequest(addrElement.text(), "RU"));
            System.out.println(resp.getResults().get(0).getGeometry().getLocation());
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        return new Apartment();
    }
}
