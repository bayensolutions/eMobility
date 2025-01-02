package org.unibl.etf.pj2.emobility.model.ui;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class VehiclesTableRowData {

    private final SimpleStringProperty id;
    private final SimpleStringProperty producer;
    private final SimpleStringProperty model;
    private final SimpleDoubleProperty purchasePrice;
    private final SimpleIntegerProperty currentBatteryLevel;

    public VehiclesTableRowData(String id, String producer, String model, double purchasePrice, int currentBatteryLevel){
        this.id=new SimpleStringProperty(id);
        this.producer=new SimpleStringProperty(producer);
        this.model=new SimpleStringProperty(model);
        this.purchasePrice=new SimpleDoubleProperty(purchasePrice);
        this.currentBatteryLevel=new SimpleIntegerProperty(currentBatteryLevel);
    }

    public String getId() {
        return id.get();
    }

    public String getProducer() {
        return producer.get();
    }

    public String getModel() {
        return model.get();
    }

    public double getPurchasePrice() {
        return purchasePrice.get();
    }

    public int getCurrentBatteryLevel() {
        return currentBatteryLevel.get();
    }

}
