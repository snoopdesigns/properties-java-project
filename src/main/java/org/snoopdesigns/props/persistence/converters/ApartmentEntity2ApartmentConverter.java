package org.snoopdesigns.props.persistence.converters;

import org.snoopdesigns.props.crawler.nextgen.entities.Apartment;
import org.snoopdesigns.props.crawler.nextgen.entities.FloorInfo;
import org.snoopdesigns.props.crawler.nextgen.entities.MetroInfo;
import org.snoopdesigns.props.persistence.entities.ApartmentEntity;
import org.springframework.core.convert.converter.Converter;

@TypeConverter
public class ApartmentEntity2ApartmentConverter implements Converter<ApartmentEntity, Apartment> {
    @Override
    public Apartment convert(ApartmentEntity apartmentEntity) {
        Apartment apartment = new Apartment();
        apartment.setCianId(apartmentEntity.getCianId());
        apartment.setUrl(apartmentEntity.getUrl());
        apartment.setFloorInfo(new FloorInfo(apartmentEntity.getFloorNumber(), apartmentEntity.getFloorTotal()));
        apartment.setHouseType(apartmentEntity.getHouseType());
        apartment.setSellType(apartmentEntity.getSellType());
        apartment.setTotalArea(apartmentEntity.getTotalArea());
        apartment.setRoomsArea(apartmentEntity.getRoomsArea());
        apartment.setLivingArea(apartmentEntity.getLivingArea());
        apartment.setWindow(apartmentEntity.getWindow());
        apartment.setPhone(apartmentEntity.getPhone());
        apartment.setRepairs(apartmentEntity.getRepairs());
        apartment.setPrice(apartmentEntity.getPrice());
        apartment.setAddress(apartmentEntity.getAddress());
        apartment.setFirstFloor(apartmentEntity.getFirstFloor());
        apartment.setLastFloor(apartmentEntity.getLastFloor());
        apartment.setDistanceToCenter(apartmentEntity.getDistanceToCenter());
        apartment.setMetroInfo(new MetroInfo(apartmentEntity.getDistanceToMetro(),
                apartmentEntity.getMetroName(), apartmentEntity.getMetroLat(),
                apartmentEntity.getMetroLng()));
        apartment.setHouseTypeEnum(apartmentEntity.getHouseTypeEnum());
        apartment.setBuildingType(apartmentEntity.getBuildingType());
        return apartment;
    }
}
