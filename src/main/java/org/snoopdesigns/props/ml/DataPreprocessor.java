package org.snoopdesigns.props.ml;

import com.google.code.geocoder.model.LatLng;
import org.snoopdesigns.props.crawler.nextgen.entities.Apartment;
import org.snoopdesigns.props.crawler.nextgen.entities.Complex;
import org.snoopdesigns.props.services.GeocoderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DataPreprocessor {

    @Autowired
    private GeocoderService geocoderService;

    public void preprocessData(Complex complex) {
        LatLng latLng = geocoderService.geocodeLocation(complex.getAddress());
        complex.setLat(latLng.getLat().floatValue());
        complex.setLng(latLng.getLng().floatValue());
        for (Apartment apartment : complex.getApartments()) {
            apartment.setFirstFloor(ValueGenerators.IS_FIRST_FLOOR.generate(apartment));
            apartment.setLastFloor(ValueGenerators.IS_LAST_FLOOR.generate(apartment));
            apartment.setDistanceToCenter(ValueGenerators.DISTANCE_TO_CENTER.generate(apartment));
            apartment.setMetroInfo(ValueGenerators.CLOSEST_METRO.generate(apartment));
            apartment.setHouseTypeEnum(ValueGenerators.HOUSE_TYPE.generate(apartment));
            apartment.setBuildingType(ValueGenerators.BUILDING_TYPE.generate(apartment));
        }
    }
}
