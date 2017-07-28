package org.snoopdesigns.props.parser;

public class Apartment {

    private String cost;
    private String test;

    public Apartment() {
    }

    public Apartment(String cost, String test) {
        this.cost = cost;
        this.test = test;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getTest() {
        return test;
    }

    public void setTest(String test) {
        this.test = test;
    }
}
