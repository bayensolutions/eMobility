package org.unibl.etf.pj2.emobility.model.ui;

import javafx.beans.property.SimpleStringProperty;

public class FailuresTableRowData {
    private final SimpleStringProperty id;
    private final SimpleStringProperty vehicleType;
    private final SimpleStringProperty dateTime;
    private final SimpleStringProperty description;

    public FailuresTableRowData(String id, String vehicleType, String dateTime, String description) {
        this.id=new SimpleStringProperty(id);
        this.vehicleType=new SimpleStringProperty(vehicleType);
        this.dateTime=new SimpleStringProperty(dateTime);
        this.description=new SimpleStringProperty(description);
    }

    public String getId() {
        return id.get();
    }

    public String getVehicleType() {
        return vehicleType.get();
    }

    public String getDateTime() {
        return dateTime.get();
    }

    public String getDescription() {
        return description.get();
    }

}
