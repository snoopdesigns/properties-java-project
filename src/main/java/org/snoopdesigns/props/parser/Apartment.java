package org.snoopdesigns.props.parser;

public class Apartment {

    private FloorInfo floorInfo;
    private String houseType;
    private String sellType;
    private Float totalArea;
    private Float roomsArea;
    private Float livingArea;
    private boolean balkony;
    private boolean elevator;
    private String window;
    private Integer price;
    private String address;

    public Apartment() {
    }

    public Apartment(FloorInfo floorInfo, String houseType, String sellType, Float totalArea,
                     Float roomsArea, Float livingArea, boolean balkony, boolean elevator, String window, Integer price,
                     String address) {
        this.floorInfo = floorInfo;
        this.houseType = houseType;
        this.sellType = sellType;
        this.totalArea = totalArea;
        this.roomsArea = roomsArea;
        this.livingArea = livingArea;
        this.balkony = balkony;
        this.elevator = elevator;
        this.window = window;
        this.price = price;
        this.address = address;
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

    public boolean isBalkony() {
        return balkony;
    }

    public void setBalkony(boolean balkony) {
        this.balkony = balkony;
    }

    public boolean isElevator() {
        return elevator;
    }

    public void setElevator(boolean elevator) {
        this.elevator = elevator;
    }

    public String getWindow() {
        return window;
    }

    public void setWindow(String window) {
        this.window = window;
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

    @Override
    public String toString() {
        return "Apartment{" +
                "floorInfo=" + floorInfo +
                ", houseType='" + houseType + '\'' +
                ", sellType='" + sellType + '\'' +
                ", totalArea=" + totalArea +
                ", roomsArea=" + roomsArea +
                ", livingArea=" + livingArea +
                ", balkony=" + balkony +
                ", elevator=" + elevator +
                ", window='" + window + '\'' +
                ", price=" + price +
                ", address='" + address + '\'' +
                '}';
    }
}
