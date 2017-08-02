package org.snoopdesigns.props.ml.entity;

import org.snoopdesigns.props.persistence.entities.Apartment;

public class ApartmentExtended extends Apartment {

    private Boolean isFirstFloor;
    private Boolean isLastFloor;
    private Float distanceToCenter;
    private String closestMetro;

    public ApartmentExtended() {
        super();
    }

    public ApartmentExtended(Apartment apartment) {
        this.setCianId(apartment.getCianId());
        this.setComplex(apartment.getComplex());
        this.setUrl(apartment.getUrl());
        this.setFloorInfo(apartment.getFloorInfo());
        this.setHouseType(apartment.getHouseType());
        this.setSellType(apartment.getSellType());
        this.setTotalArea(apartment.getTotalArea());
        this.setRoomsArea(apartment.getRoomsArea());
        this.setLivingArea(apartment.getLivingArea());
        this.setWindow(apartment.getWindow());
        this.setPhone(apartment.getPhone());
        this.setRepairs(apartment.getRepairs());
        this.setPrice(apartment.getPrice());
        this.setAddress(apartment.getAddress());
    }

    public Boolean getFirstFloor() {
        return isFirstFloor;
    }

    public void setFirstFloor(Boolean firstFloor) {
        isFirstFloor = firstFloor;
    }

    public Boolean getLastFloor() {
        return isLastFloor;
    }

    public void setLastFloor(Boolean lastFloor) {
        isLastFloor = lastFloor;
    }

    public Float getDistanceToCenter() {
        return distanceToCenter;
    }

    public void setDistanceToCenter(Float distanceToCenter) {
        this.distanceToCenter = distanceToCenter;
    }

    public String getClosestMetro() {
        return closestMetro;
    }

    public void setClosestMetro(String closestMetro) {
        this.closestMetro = closestMetro;
    }
}
