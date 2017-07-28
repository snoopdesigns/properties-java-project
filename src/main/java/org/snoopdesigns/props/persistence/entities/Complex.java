package org.snoopdesigns.props.persistence.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "COMPLEXES")
public class Complex {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Column(name = "CIAN_ID")
    private String cianId;

    protected Complex() {}

    public Complex(String cianId) {
        this.cianId = cianId;
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

    @Override
    public String toString() {
        return "Complex{" +
                "id=" + id +
                ", cianId='" + cianId + '\'' +
                '}';
    }
}
