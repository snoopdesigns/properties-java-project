package org.snoopdesigns.props.persistence.entities;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "APARTMENTS")
public class Apartment {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name="floorNumber", column=@Column(name="FLOOR_NUMBER")),
            @AttributeOverride(name="floorTotal", column=@Column(name="FLOOR_TOTAL"))
    })
    private FloorInfo floorInfo;

    @Column(name = "HOUSE_TYPE")
    private String houseType;

    @Column(name = "SELL_TYPE")
    private String sellType;

    @Column(name = "TOTAL_AREA")
    private Float totalArea;

    @Column(name = "ROOMS_AREA")
    private Float roomsArea;

    @Column(name = "LIVING_AREA")
    private Float livingArea;

    @Column(name = "WINDOW")
    private String window;

    @Column(name = "PRICE")
    private Integer price;

    @Column(name = "ADDRESS")
    private String address;

    public Apartment() {
    }

    public Apartment(FloorInfo floorInfo, String houseType, String sellType, Float totalArea,
                     Float roomsArea, Float livingArea, String window, Integer price,
                     String address) {
        this.floorInfo = floorInfo;
        this.houseType = houseType;
        this.sellType = sellType;
        this.totalArea = totalArea;
        this.roomsArea = roomsArea;
        this.livingArea = livingArea;
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
                ", window='" + window +
                ", price=" + price +
                ", address='" + address +
                '}';
    }
}
