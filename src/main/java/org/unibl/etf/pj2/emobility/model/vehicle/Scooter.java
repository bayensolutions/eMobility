package org.unibl.etf.pj2.emobility.model.vehicle;

public class Scooter extends Vehicle {
    private int maximumSpeed;

    public Scooter(String id, String producer, String model, double purchasePrice, int currentBatteryLevel, int maximumSpeed) {
        super(id, producer, model, purchasePrice, currentBatteryLevel);
        this.maximumSpeed = maximumSpeed;
    }

    public int getMaximumSpeed() {
        return maximumSpeed;
    }

    @Override
    public String toString() {
        return "Scooter{" +
                "maximumSpeed=" + maximumSpeed +
                "} " + super.toString();
    }

    public void setMaximumSpeed(int maximumSpeed) {
        this.maximumSpeed = maximumSpeed;
    }
}
