package org.snoopdesigns.props.crawler.nextgen.entities;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.snoopdesigns.props.controllers.CustomApartmentsSerializer;

@JsonSerialize(using=CustomApartmentsSerializer.class)
public class Apartment {

    private String cianId;
    private Complex complex;
    private String url;
    private FloorInfo floorInfo;
    private String houseType;
    private String sellType;
    private Float totalArea;
    private Float roomsArea;
    private Float livingArea;
    private String window;
    private String phone;
    private String repairs;
    private Integer price;
    private String address;
    private Boolean isFirstFloor;
    private Boolean isLastFloor;
    private Double distanceToCenter;
    private MetroInfo metroInfo;
    private HouseType houseTypeEnum;
    private BuildingType buildingType;

    public Apartment() {}

    public Complex getComplex() {
        return complex;
    }

    public void setComplex(Complex complex) {
        this.complex = complex;
    }

    public String getCianId() {
        return cianId;
    }

    public void setCianId(String cianId) {
        this.cianId = cianId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public FloorInfo getFloorInfo() {
        return floorInfo;
    }

    public void setFloorInfo(FloorInfo floorInfo) {
        this.floorInfo = floorInfo;
    }

    public String getHouseType() {
        return houseType;
    }

    public void setHouseType(String houseType) {
        this.houseType = houseType;
    }

    public String getSellType() {
        return sellType;
    }

    public void setSellType(String sellType) {
        this.sellType = sellType;
    }

    public Float getTotalArea() {
        return totalArea;
    }

    public void setTotalArea(Float totalArea) {
        this.totalArea = totalArea;
    }

    public Float getRoomsArea() {
        return roomsArea;
    }

    public void setRoomsArea(Float roomsArea) {
        this.roomsArea = roomsArea;
    }

    public Float getLivingArea() {
        return livingArea;
    }

    public void setLivingArea(Float livingArea) {
        this.livingArea = livingArea;
    }

    public String getWindow() {
        return window;
    }

    public void setWindow(String window) {
        this.window = window;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRepairs() {
        return repairs;
    }

    public void setRepairs(String repairs) {
        this.repairs = repairs;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public Double getDistanceToCenter() {
        return distanceToCenter;
    }

    public void setDistanceToCenter(Double distanceToCenter) {
        this.distanceToCenter = distanceToCenter;
    }

    public MetroInfo getMetroInfo() {
        return metroInfo;
    }

    public void setMetroInfo(MetroInfo metroInfo) {
        this.metroInfo = metroInfo;
    }

    public HouseType getHouseTypeEnum() {
        return houseTypeEnum;
    }

    public void setHouseTypeEnum(HouseType houseTypeEnum) {
        this.houseTypeEnum = houseTypeEnum;
    }

    public BuildingType getBuildingType() {
        return buildingType;
    }

    public void setBuildingType(BuildingType buildingType) {
        this.buildingType = buildingType;
    }
}
