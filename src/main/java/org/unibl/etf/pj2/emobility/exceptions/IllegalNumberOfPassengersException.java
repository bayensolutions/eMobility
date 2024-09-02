package org.unibl.etf.pj2.emobility.exceptions;

public class IllegalNumberOfPassengersException extends Exception {
    private static final String ILLEGAL_NUMBER_OF_PASSENGERS_MESSAGE="Broj putnika u automobilu mora biti veci od 0.";

    public IllegalNumberOfPassengersException() {
        this(ILLEGAL_NUMBER_OF_PASSENGERS_MESSAGE);
    }

    public IllegalNumberOfPassengersException(String message){
        super(message);
    }
}
