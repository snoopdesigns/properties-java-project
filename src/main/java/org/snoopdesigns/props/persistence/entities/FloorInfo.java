package org.snoopdesigns.props.persistence.entities;

/**
 * Created by dmmorozo on 28.07.2017.
 */
public class FloorInfo {

    private Integer floorNumber;
    private Integer floorTotal;

    public FloorInfo() {
    }

    public FloorInfo(Integer floorNumber, Integer floorTotal) {
        this.floorNumber = floorNumber;
        this.floorTotal = floorTotal;
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

    @Override
    public String toString() {
        return "FloorInfo{" +
                "floorNumber=" + floorNumber +
                ", floorTotal=" + floorTotal +
                '}';
    }
}
