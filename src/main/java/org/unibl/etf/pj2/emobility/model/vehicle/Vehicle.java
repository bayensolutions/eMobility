package org.unibl.etf.pj2.emobility.model.vehicle;

import java.time.LocalDateTime;

public abstract class Vehicle extends Thread implements IDrivable {
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


}
