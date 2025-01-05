package org.unibl.etf.pj2.emobility.model.vehicle;

import java.time.LocalDateTime;
import java.util.Objects;

public abstract class Vehicle implements IDrivable {
    protected String id;
    protected String producer;
    protected String model;
    protected double purchasePrice;
    protected int currentBatteryLevel;

    public Vehicle(String id, String producer, String model, double purchasePrice, int currentBatteryLevel) {
        this.id = id;
        this.producer = producer;
        this.model = model;
        this.purchasePrice = purchasePrice;
        this.currentBatteryLevel = currentBatteryLevel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vehicle vehicle = (Vehicle) o;
        return id.equals(vehicle.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "id=" + id +
                ", producer='" + producer + '\'' +
                ", model='" + model + '\'' +
                ", purchasePrice=" + purchasePrice +
                ", currentBatteryLevel=" + currentBatteryLevel +
                "} " + super.toString();
    }

    @Override
    public int chargeBattery(int amount) {
        return currentBatteryLevel += amount;
    }

    @Override
    public int dischargeBattery(int amount) {
        return currentBatteryLevel -= amount;
    }

    @Override
    public void reportBreakdown(String description) {
        System.out.println(description + LocalDateTime.now());
    }

    public String getId() {
        return id;
    }

    public String getProducer() {
        return producer;
    }

    public String getModel() {
        return model;
    }

    public double getPurchasePrice() {
        return purchasePrice;
    }

    public int getCurrentBatteryLevel() {
        return currentBatteryLevel;
    }
}
