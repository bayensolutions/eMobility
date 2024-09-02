package org.unibl.etf.pj2.emobility.model.vehicle;

import org.unibl.etf.pj2.emobility.exceptions.IllegalNumberOfPassengersException;

public interface IPassengerCarrier {
    int getPassengerCount();
    void addPassenger();
    void removePassenger() throws IllegalNumberOfPassengersException;
}
