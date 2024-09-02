package org.unibl.etf.pj2.emobility.model.vehicle;

import org.unibl.etf.pj2.emobility.exceptions.IllegalNumberOfPassengersException;

import java.time.LocalDate;

public class Car extends Vehicle implements IPassengerCarrier{
    private LocalDate dateOfAcquisition;
    private String description;
    private int numberOfPassengers;

    public Car(int id, String producer, String model, double purchasePrice, int currentBatteryLevel, String description, int numberOfPassengers) {
        super(id, producer, model, purchasePrice, currentBatteryLevel);
        this.description = description;
        this.numberOfPassengers = numberOfPassengers;
        this.dateOfAcquisition=LocalDate.now();
    }

    @Override
    public int getPassengerCount() {
        return numberOfPassengers;
    }

    @Override
    public void addPassenger() {
        numberOfPassengers++;
    }

    @Override
    public void removePassenger() throws IllegalNumberOfPassengersException {
        if(numberOfPassengers>0){
            numberOfPassengers--;
        }
        else{
            throw new IllegalNumberOfPassengersException();
        }
    }
}
