package org.snoopdesigns.props.ml.entity;

import org.snoopdesigns.props.persistence.entities.Apartment;

public class ApartmentExtended extends Apartment {

    private Boolean isFirstFloor;
    private Boolean isLastFloor;
    private Float distanceToCenter;

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

    public Float getDistanceToCenter() {
        return distanceToCenter;
    }

    public void setDistanceToCenter(Float distanceToCenter) {
        this.distanceToCenter = distanceToCenter;
    }
}
