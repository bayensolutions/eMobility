package org.unibl.etf.pj2.emobility.model.rental;

import org.unibl.etf.pj2.emobility.model.user.User;
import org.unibl.etf.pj2.emobility.util.Util;

import java.time.LocalDateTime;
import java.util.Properties;

public class Rental extends Thread {
    private String vehicleID;
    private String dateTime;
    private String userName;
    private Coordinate startCoordinate;
    private Coordinate endCoordinate;
    private double price;
    private int duration;
    private static int rentalNumber=0;

    public Rental(String vehicleID, String dateTime, String userName, Coordinate startCoordinate, Coordinate endCoordinate, int duration) {
        this.vehicleID=vehicleID;
        this.dateTime = dateTime;
        this.userName = userName;
        this.startCoordinate = startCoordinate;
        this.endCoordinate = endCoordinate;
        this.duration=duration;
        rentalNumber++;
    }

    public double calculateRental(){
        Properties properties= Util.loadProperties();
        //System.out.println(properties);
        int scooterUnitPrice=Integer.parseInt(properties.getProperty("SCOOTER_UNIT_PRICE"));
        int carUnitPrice=Integer.parseInt(properties.getProperty("CAR_UNIT_PRICE"));
        int bikeUnitPrice=Integer.parseInt(properties.getProperty("BIKE_UNIT_PRICE"));
        int distanceNarrow=Integer.parseInt(properties.getProperty("DISTANCE_NARROW"));
        int distanceWide=Integer.parseInt(properties.getProperty("DISTANCE_WIDE"));
        int discountProm=Integer.parseInt(properties.getProperty("DISCOUNT_PROM"));
        int discount=Integer.parseInt(properties.getProperty("DISCOUNT"));

        String vehicleType=Util.getVehicleType(vehicleID);
        int basicPrice;
        switch (vehicleType){
            case "automobil" -> basicPrice=carUnitPrice*duration;
            case "bicikl"-> basicPrice=bikeUnitPrice*duration;
            case "trotinet"->basicPrice=scooterUnitPrice*duration;
            default -> basicPrice=0;
        }

        return (double) basicPrice;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Coordinate getStartCoordinate() {
        return startCoordinate;
    }

    public void setStartCoordinate(Coordinate startCoordinate) {
        this.startCoordinate = startCoordinate;
    }

    public Coordinate getEndCoordinate() {
        return endCoordinate;
    }

    public void setEndCoordinate(Coordinate endCoordinate) {
        this.endCoordinate = endCoordinate;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "Rental{" +
                "vehicleID='" + vehicleID + '\'' +
                ", dateTime='" + dateTime + '\'' +
                ", userName='" + userName + '\'' +
                ", startCoordinate=" + startCoordinate +
                ", endCoordinate=" + endCoordinate +
                ", price=" + price +
                ", duration=" + duration +
                "} " + super.toString();
    }
}
