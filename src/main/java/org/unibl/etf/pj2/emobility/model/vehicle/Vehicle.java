package org.unibl.etf.pj2.emobility.model.vehicle;

import java.time.LocalDateTime;

public abstract class Vehicle extends Thread implements IDrivable {
    protected int id;
    protected String producer;
    protected String model;
    protected double purchasePrice;
    protected int currentBatteryLevel;

    public Vehicle(int id, String producer, String model, double purchasePrice, int currentBatteryLevel) {
        this.id = id;
        this.producer = producer;
        this.model = model;
        this.purchasePrice = purchasePrice;
        this.currentBatteryLevel = currentBatteryLevel;
    }

    @Override
    public int chargeBattery(int ammount) {
        return currentBatteryLevel+=ammount;
    }

    @Override
    public int dischargeBattery(int ammount) {
        return currentBatteryLevel-=ammount;
    }

    @Override
    public void reportBreakdown(String description) {
        System.out.println(description+ LocalDateTime.now());
    }



}
