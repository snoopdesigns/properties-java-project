package org.snoopdesigns.props.ml;

import org.snoopdesigns.props.ml.entity.ApartmentExtended;
import org.snoopdesigns.props.ml.utils.Utils;

public class ValueGenerators {

    public static final SynteticFeatureGenerator<Boolean> IS_FIRST_FLOOR = new SynteticFeatureGenerator<Boolean>() {
        @Override
        public Boolean generate(ApartmentExtended apartmentExtended) {
            return apartmentExtended.getFloorInfo() != null ? apartmentExtended.getFloorInfo().getFloorNumber().equals(1) : null;
        }
    };

    public static final SynteticFeatureGenerator<Boolean> IS_LAST_FLOOR = new SynteticFeatureGenerator<Boolean>() {
        @Override
        public Boolean generate(ApartmentExtended apartmentExtended) {
            return apartmentExtended.getFloorInfo() != null ? apartmentExtended.getFloorInfo().getFloorNumber().equals(apartmentExtended.getFloorInfo().getFloorTotal()) : null;
        }
    };

    public static final SynteticFeatureGenerator<Double> DISTANCE_TO_CENTER = new SynteticFeatureGenerator<Double>() {
        @Override
        public Double generate(ApartmentExtended apartmentExtended) {
            return Utils.distance(apartmentExtended.getComplex().getLat().doubleValue(),
                    59.936311d,
                    apartmentExtended.getComplex().getLng().doubleValue(),
                    30.319862d);
        }
    };

    public static final SynteticFeatureGenerator<String> CLOSEST_METRO = new SynteticFeatureGenerator<String>() {
        @Override
        public String generate(ApartmentExtended apartmentExtended) {
            return Utils.findClosestMetro(apartmentExtended.getComplex().getLat(), apartmentExtended.getComplex().getLng());
        }
    };
}
