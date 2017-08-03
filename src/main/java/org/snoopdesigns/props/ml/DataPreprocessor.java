package org.snoopdesigns.props.ml;

import java.util.ArrayList;
import java.util.List;

import org.snoopdesigns.props.ml.entity.ApartmentExtended;
import org.snoopdesigns.props.persistence.entities.Apartment;
import org.springframework.stereotype.Component;

@Component
public class DataPreprocessor {

    public List<ApartmentExtended> preprocessData(List<Apartment> apartments) {
        List<ApartmentExtended> apartmentExtendedList = new ArrayList<>();
        for (Apartment apartment : apartments) {
            ApartmentExtended extended = new ApartmentExtended(apartment);
            extended.setFirstFloor(ValueGenerators.IS_FIRST_FLOOR.generate(extended));
            extended.setLastFloor(ValueGenerators.IS_LAST_FLOOR.generate(extended));
            extended.setDistanceToCenter(ValueGenerators.DISTANCE_TO_CENTER.generate(extended));
            extended.setMetroInfo(ValueGenerators.CLOSEST_METRO.generate(extended));
            apartmentExtendedList.add(extended);
        }
        return apartmentExtendedList;
    }
}
