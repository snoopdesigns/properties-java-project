package org.snoopdesigns.props.persistence.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.snoopdesigns.props.crawler.nextgen.entities.BuildingType;
import org.snoopdesigns.props.crawler.nextgen.entities.HouseType;

@Entity
@Table(name = "APARTMENTS")
public class ApartmentEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;

    @Column(name = "CIAN_ID")
    private String cianId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="COMPLEX_ID")
    private ComplexEntity complex;

    @Column(name = "URL")
    private String url;

    @Column(name = "FLOOR_NUMBER")
    private Integer floorNumber;

    @Column(name = "FLOOR_TOTAL")
    private Integer floorTotal;

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

    @Column(name = "IS_FIRST_FLOOR")
    private Boolean isFirstFloor;

    @Column(name = "IS_LAST_FLOOR")
    private Boolean isLastFloor;

    @Column(name = "DISTANCE_TO_CENTER")
    private Double distanceToCenter;

    @Column(name = "DISTANCE_TO_METRO")
    private Double distanceToMetro;

    @Column(name = "METRO_NAME")
    private String metroName;

    @Column(name = "METRO_LAT")
    private Float metroLat;

    @Column(name = "METRO_LNG")
    private Float metroLng;

    @Enumerated(EnumType.STRING)
    @Column(name = "HOUSE_TYPE_ENUM")
    private HouseType houseTypeEnum;

    @Enumerated(EnumType.STRING)
    @Column(name = "BUILDING_TYPE")
    private BuildingType buildingType;

    public ApartmentEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCianId() {
        return cianId;
    }

    public void setCianId(String cianId) {
        this.cianId = cianId;
    }

    public ComplexEntity getComplex() {
        return complex;
    }

    public void setComplex(ComplexEntity complex) {
        this.complex = complex;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getFloorNumber() {
        return floorNumber;
    }

    public void setFloorNumber(Integer floorNumber) {
        this.floorNumber = floorNumber;
    }

    public Integer getFloorTotal() {
        return floorTotal;
    }

    public void setFloorTotal(Integer floorTotal) {
        this.floorTotal = floorTotal;
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

    public Double getDistanceToMetro() {
        return distanceToMetro;
    }

    public void setDistanceToMetro(Double distanceToMetro) {
        this.distanceToMetro = distanceToMetro;
    }

    public String getMetroName() {
        return metroName;
    }

    public void setMetroName(String metroName) {
        this.metroName = metroName;
    }

    public Float getMetroLat() {
        return metroLat;
    }

    public void setMetroLat(Float metroLat) {
        this.metroLat = metroLat;
    }

    public Float getMetroLng() {
        return metroLng;
    }

    public void setMetroLng(Float metroLng) {
        this.metroLng = metroLng;
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
