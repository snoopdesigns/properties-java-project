package org.snoopdesigns.props.persistence.entities;

import javax.persistence.*;
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

    @OneToMany(mappedBy="complex")
    private List<Apartment> apartments;

    public Complex() {}

    public Complex(Integer cianId) {
        this.cianId = cianId;
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

    public List<Apartment> getApartments() {
        return apartments;
    }

    public void setApartments(List<Apartment> apartments) {
        this.apartments = apartments;
    }
}
