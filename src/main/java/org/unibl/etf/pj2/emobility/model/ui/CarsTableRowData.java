package org.unibl.etf.pj2.emobility.model.ui;

import javafx.beans.property.SimpleStringProperty;
import org.unibl.etf.pj2.emobility.model.vehicle.Car;

public class CarsTableRowData extends VehiclesTableRowData {
    private final SimpleStringProperty dateTime;
    private final SimpleStringProperty description;

    public CarsTableRowData(String id, String producer, String model, double purchasePrice, int currentBatteryLevel, String dateTime, String description) {
        super(id, producer, model, purchasePrice, currentBatteryLevel);
        this.dateTime = new SimpleStringProperty(dateTime);
        this.description = new SimpleStringProperty(description);
    }

    public String getDateTime() {
        return dateTime.get();
    }

    public String getDescription() {
        return description.get();
    }

}
