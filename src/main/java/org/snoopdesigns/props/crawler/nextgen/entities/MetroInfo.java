package org.snoopdesigns.props.crawler.nextgen.entities;

public class MetroInfo {

    private Double distanceToMetro;
    private String metroName;
    private Float metroLat;
    private Float metroLng;

    public MetroInfo() {
    }

    public MetroInfo(Double distanceToMetro, String metroName, Float metroLat, Float metroLng) {
        this.distanceToMetro = distanceToMetro;
        this.metroName = metroName;
        this.metroLat = metroLat;
        this.metroLng = metroLng;
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
}
