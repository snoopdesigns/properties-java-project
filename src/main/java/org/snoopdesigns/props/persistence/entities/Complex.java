package org.snoopdesigns.props.persistence.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "COMPLEXES")
public class Complex {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Column(name = "CIAN_ID")
    private Integer cianId;

    @Column(name = "NAME")
    private String name;

    @Column(name = "ADDRESS")
    private String address;

    @Column(name = "LAT")
    private Float lat;

    @Column(name = "LNG")
    private Float lng;

    @OneToMany(mappedBy="complex")
    private List<Apartment> apartments;

    public Complex() {}

    public Complex(Integer cianId, String name, String address) {
        this.cianId = cianId;
        this.name = name;
        this.address = address;
    }

    public Long getId() {
        return id;
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
