package org.unibl.etf.pj2.emobility.model.vehicle;

public class Bicycle extends Vehicle {
    private int range;

    public Bicycle(String id, String producer, String model, double purchasePrice, int currentBatteryLevel, int range) {
        super(id, producer, model, purchasePrice, currentBatteryLevel);
        this.range = range;
    }

    public int getRange() {
        return range;
    }

    public void setRange(int range) {
        this.range = range;
    }

    @Override
    public String toString() {
        return "Bicycle{" +
                "range=" + range +
                "} " + super.toString();
    }
}
