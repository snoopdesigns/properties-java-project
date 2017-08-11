package org.snoopdesigns.props.crawler.nextgen.entities;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.snoopdesigns.props.controllers.CustomComplexSerializer;

@JsonSerialize(using=CustomComplexSerializer.class)
public class Complex {

    private Integer cianId;
    private String name;
    private String address;
    private Float lat;
    private Float lng;
    private List<Apartment> apartments = new ArrayList<>();

    public Complex() {}

    public Complex(Integer cianId, String name, String address) {
        this.cianId = cianId;
        this.name = name;
        this.address = address;
    }

    public Integer getCianId() {
        return cianId;
    }

    public void setCianId(Integer cianId) {
        this.cianId = cianId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public List<Apartment> getApartments() {
        return apartments;
    }

    public void setApartments(List<Apartment> apartments) {
        this.apartments = apartments;
    }
}
