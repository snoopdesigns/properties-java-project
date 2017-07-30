package org.snoopdesigns.props.persistence.entities;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "APARTMENTS")
public class Apartment {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;

    @Column(name = "CIAN_ID")
    private String cianId;

    @JsonIgnore
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="COMPLEX_ID")
    private Complex complex;

    @Column(name = "URL")
    private String url;

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

    @Column(name = "PHONE")
    private String phone;

    @Column(name = "REPAIRS")
    private String repairs;

    @Column(name = "PRICE")
    private Integer price;

    @Column(name = "ADDRESS")
    private String address;

    @Column(name = "LAT")
    private Float lat;

    @Column(name = "LNG")
    private Float lng;

    public Apartment() {}

    public Long getId() {
        return id;
    }

    public String getCianId() {
        return cianId;
    }

    public void setCianId(String cianId) {
        this.cianId = cianId;
    }

    public Complex getComplex() {
        return complex;
    }

    public void setComplex(Complex complex) {
        this.complex = complex;
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

    public Float getLat() {
        return lat;
    }

    public void setLat(Float lat) {
        this.lat = lat;
    }

    public Float getLng() {
        return lng;
    }

    public void setLng(Float lng) {
        this.lng = lng;
    }
}
