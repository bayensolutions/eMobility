package org.unibl.etf.pj2.emobility.model.vehicle;

import java.time.LocalDate;

public class Car extends Vehicle implements IMultiPassengerCarrier {
    private String dateOfAcquisition;
    private String description;
    //private int numberOfPassengers;

    public Car(String id, String producer, String model, String dateTime, double purchasePrice, int currentBatteryLevel, String description) {
        super(id, producer, model, purchasePrice, currentBatteryLevel);
        this.description = description;
        this.dateOfAcquisition = dateTime;
    }

    public String getDateOfAcquisition() {
        return dateOfAcquisition;
    }

    public void setDateOfAcquisition(String dateOfAcquisition) {
        this.dateOfAcquisition = dateOfAcquisition;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Car{" +
                "dateOfAcquisition=" + dateOfAcquisition +
                ", description='" + description + '\'' +
                "} " + super.toString();
    }

    /*
    public int getNumberOfPassengers() {
        return numberOfPassengers;
    }

    public void setNumberOfPassengers(int numberOfPassengers) {
        this.numberOfPassengers = numberOfPassengers;
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
        if (numberOfPassengers > 0) {
            numberOfPassengers--;
        } else {
            throw new IllegalNumberOfPassengersException();
        }
    }

 */
}
