package org.snoopdesigns.props.persistence.converters;

import org.snoopdesigns.props.crawler.nextgen.entities.Apartment;
import org.snoopdesigns.props.persistence.entities.ApartmentEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;

@TypeConverter
public class Apartment2ApartmentEntityConverter implements Converter<Apartment, ApartmentEntity> {

    @Autowired
    private ConversionService conversionService;

    @Override
    public ApartmentEntity convert(Apartment apartment) {
        ApartmentEntity apartmentEntity = new ApartmentEntity();
        apartmentEntity.setCianId(apartment.getCianId());
        apartmentEntity.setUrl(apartment.getUrl());
        apartmentEntity.setFloorNumber(apartment.getFloorInfo() != null ? apartment.getFloorInfo().getFloorNumber() : null);
        apartmentEntity.setFloorTotal(apartment.getFloorInfo() != null ? apartment.getFloorInfo().getFloorTotal() : null);
        apartmentEntity.setHouseType(apartment.getHouseType());
        apartmentEntity.setSellType(apartment.getSellType());
        apartmentEntity.setTotalArea(apartment.getTotalArea());
        apartmentEntity.setRoomsArea(apartment.getRoomsArea());
        apartmentEntity.setLivingArea(apartment.getLivingArea());
        apartmentEntity.setWindow(apartment.getWindow());
        apartmentEntity.setPhone(apartment.getPhone());
        apartmentEntity.setRepairs(apartment.getRepairs());
        apartmentEntity.setPrice(apartment.getPrice());
        apartmentEntity.setAddress(apartment.getAddress());
        apartmentEntity.setFirstFloor(apartment.getFirstFloor());
        apartmentEntity.setLastFloor(apartment.getLastFloor());
        apartmentEntity.setDistanceToCenter(apartment.getDistanceToCenter());
        apartmentEntity.setDistanceToMetro(apartment.getMetroInfo() != null ? apartment.getMetroInfo().getDistanceToMetro() : null);
        apartmentEntity.setMetroName(apartment.getMetroInfo() != null ? apartment.getMetroInfo().getMetroName() : null);
        apartmentEntity.setMetroLat(apartment.getMetroInfo() != null ? apartment.getMetroInfo().getMetroLat() : null);
        apartmentEntity.setMetroLng(apartment.getMetroInfo() != null ? apartment.getMetroInfo().getMetroLng() : null);
        apartmentEntity.setHouseTypeEnum(apartment.getHouseTypeEnum());
        apartmentEntity.setBuildingType(apartment.getBuildingType());
        return apartmentEntity;
    }
}
