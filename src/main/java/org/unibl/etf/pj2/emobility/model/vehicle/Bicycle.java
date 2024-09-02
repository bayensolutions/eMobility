package org.unibl.etf.pj2.emobility.model.vehicle;

public class Bicycle extends Vehicle{
    private int range;

    public Bicycle(int id, String producer, String model, double purchasePrice, int currentBatteryLevel, int range) {
        super(id, producer, model, purchasePrice, currentBatteryLevel);
        this.range = range;
    }
}
