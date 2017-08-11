package org.snoopdesigns.props.controllers;

public class DataInfo {
    private Integer numComplexes;
    private Integer numApartments;

    public DataInfo() {
    }

    public DataInfo(Integer numComplexes, Integer numApartments) {
        this.numComplexes = numComplexes;
        this.numApartments = numApartments;
    }

    public Integer getNumComplexes() {
        return numComplexes;
    }

    public void setNumComplexes(Integer numComplexes) {
        this.numComplexes = numComplexes;
    }

    public Integer getNumApartments() {
        return numApartments;
    }

    public void setNumApartments(Integer numApartments) {
        this.numApartments = numApartments;
    }
}
