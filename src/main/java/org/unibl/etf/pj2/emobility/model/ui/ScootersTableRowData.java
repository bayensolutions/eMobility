package org.unibl.etf.pj2.emobility.model.ui;

import javafx.beans.property.SimpleIntegerProperty;

public class ScootersTableRowData extends VehiclesTableRowData {
    private final SimpleIntegerProperty maximumSpeed;

    public ScootersTableRowData(String id, String producer, String model, double purchasePrice, int currentBatteryLevel, int maximumSpeed) {
        super(id, producer, model, purchasePrice, currentBatteryLevel);
        this.maximumSpeed = new SimpleIntegerProperty(maximumSpeed);
    }

    public int getMaximumSpeed() {
        return maximumSpeed.get();
    }
}
