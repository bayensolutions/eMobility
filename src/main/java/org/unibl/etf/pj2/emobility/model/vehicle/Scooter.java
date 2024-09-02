package org.unibl.etf.pj2.emobility.model.vehicle;

public class Scooter extends Vehicle{
    private int maximumSpeed;

    public Scooter(int id, String producer, String model, double purchasePrice, int currentBatteryLevel, int maximumSpeed) {
        super(id, producer, model, purchasePrice, currentBatteryLevel);
        this.maximumSpeed = maximumSpeed;
    }
}
