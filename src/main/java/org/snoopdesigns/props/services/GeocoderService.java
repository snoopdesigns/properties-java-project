package org.snoopdesigns.props.services;

import java.io.IOException;

import com.google.code.geocoder.Geocoder;
import com.google.code.geocoder.model.GeocodeResponse;
import com.google.code.geocoder.model.GeocoderRequest;
import com.google.code.geocoder.model.GeocoderStatus;
import com.google.code.geocoder.model.LatLng;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class GeocoderService {

    private final static Logger logger = LoggerFactory.getLogger(GeocoderService.class);

    private Geocoder geocoder = new Geocoder();

    public LatLng geocodeLocation(String address) {
        try {
            GeocodeResponse resp = geocoder.geocode(new GeocoderRequest(address, "RU"));
            if (resp.getStatus().equals(GeocoderStatus.OK)) {
                logger.info("Location: " + resp.getResults().get(0).getGeometry().getLocation());
                return resp.getResults().get(0).getGeometry().getLocation();
            } else {
                logger.warn("Unable to geocode location: " + address);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
