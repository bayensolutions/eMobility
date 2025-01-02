package org.unibl.etf.pj2.emobility.model.ui;

import javafx.beans.property.SimpleIntegerProperty;

public class BicyclesTableRowData extends VehiclesTableRowData {
    private final SimpleIntegerProperty range;

    public BicyclesTableRowData(String id, String producer, String model, double purchasePrice, int currentBatteryLevel, int range) {
        super(id, producer, model, purchasePrice, currentBatteryLevel);
        this.range = new SimpleIntegerProperty(range);
    }

    public int getRange() {
        return range.get();
    }

}
