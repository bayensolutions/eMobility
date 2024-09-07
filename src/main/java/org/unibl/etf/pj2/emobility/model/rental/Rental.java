package org.unibl.etf.pj2.emobility.model.rental;

import org.unibl.etf.pj2.emobility.model.user.User;

import java.time.LocalDateTime;

public class Rental {
    private String dateTime;
    private String userName;
    private Coordinate startCoordinate;
    private Coordinate endCoordinate;
    private double price;
    private long duration;

    public Rental(String dateTime, String userName, Coordinate startCoordinate, Coordinate endCoordinate, long duration) {
        this.dateTime = dateTime;
        this.userName = userName;
        this.startCoordinate = startCoordinate;
        this.endCoordinate = endCoordinate;
        this.duration=duration;
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

    public void setDuration(long duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "Rental{" +
                "dateTime='" + dateTime + '\'' +
                ", userName='" + userName + '\'' +
                ", startCoordinate=" + startCoordinate +
                ", endCoordinate=" + endCoordinate +
                ", price=" + price +
                ", duration=" + duration +
                '}';
    }
}
