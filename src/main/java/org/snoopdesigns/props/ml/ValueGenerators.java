package org.snoopdesigns.props.ml;

import org.snoopdesigns.props.ml.entity.ApartmentExtended;
import org.snoopdesigns.props.ml.entity.BuildingType;
import org.snoopdesigns.props.ml.entity.HouseType;
import org.snoopdesigns.props.ml.entity.MetroInfo;
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
            if (apartmentExtended.getComplex().getLat() != null && apartmentExtended.getComplex().getLng() != null) {
                return Utils.distance(apartmentExtended.getComplex().getLat().doubleValue(),
                        59.936311d,
                        apartmentExtended.getComplex().getLng().doubleValue(),
                        30.319862d);
            } else {
                return null;
            }
        }
    };

    public static final SynteticFeatureGenerator<MetroInfo> CLOSEST_METRO = new SynteticFeatureGenerator<MetroInfo>() {
        @Override
        public MetroInfo generate(ApartmentExtended apartmentExtended) {
            if (apartmentExtended.getComplex().getLat() != null && apartmentExtended.getComplex().getLng() != null) {
                return Utils.findClosestMetro(apartmentExtended.getComplex().getLat(), apartmentExtended.getComplex().getLng());
            } else {
                return null;
            }
        }
    };
    public static final SynteticFeatureGenerator<HouseType> HOUSE_TYPE = new SynteticFeatureGenerator<HouseType>() {
        @Override
        public HouseType generate(ApartmentExtended apartmentExtended) {
            if (apartmentExtended.getHouseType() != null) {
                if (apartmentExtended.getHouseType().contains("кирпичный")) {
                    return HouseType.BRICK_MONOLITIC;
                }
                if (apartmentExtended.getHouseType().contains("кирпично-монолитный")) {
                    return HouseType.BRICK_MONOLITIC;
                }
                if (apartmentExtended.getHouseType().contains("панельный")) {
                    return HouseType.PANEL;
                }
            }
            return null;
        }
    };
    public static final SynteticFeatureGenerator<BuildingType> BUILDING_TYPE = new SynteticFeatureGenerator<BuildingType>() {
        @Override
        public BuildingType generate(ApartmentExtended apartmentExtended) {
            if (apartmentExtended.getHouseType() != null) {
                if (apartmentExtended.getHouseType().contains("новостройка")) {
                    return BuildingType.NEW;
                }
                if (apartmentExtended.getHouseType().contains("вторич")) {
                    return BuildingType.SECONDARY_MARKET;
                }
            }
            return null;
        }
    };
}
