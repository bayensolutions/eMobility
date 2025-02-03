package org.unibl.etf.pj2.emobility.model.rental;

import org.unibl.etf.pj2.emobility.model.user.User;
import org.unibl.etf.pj2.emobility.util.Util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Properties;

public class Rental extends Thread {
    private String vehicleID;
    private String dateTime;
    private String userName;
    private Coordinate startCoordinate;
    private Coordinate endCoordinate;
    private double price;
    private int duration;
    private boolean failure;
    private boolean promoDiscount;

    private int rentalNumber = 0;

    public Rental(String vehicleID, String dateTime, String userName, Coordinate startCoordinate, Coordinate endCoordinate, int duration, boolean failure, boolean promoDiscount) {
        this.vehicleID = vehicleID;
        this.dateTime = dateTime;
        this.userName = userName;
        this.startCoordinate = startCoordinate;
        this.endCoordinate = endCoordinate;
        this.duration = duration;
        this.failure = failure;
        this.promoDiscount = promoDiscount;
        rentalNumber++;
        this.price = calculateRentalPrice();
    }

    @Override
    public void run() {
        List<Coordinate> path = Util.getPath(startCoordinate, endCoordinate);
        for (Coordinate currentPosition : path) {
            System.out.println(this.vehicleID + " trenutno se nalazi na poziciji " + currentPosition);
        }

        try {
            Thread.sleep(1000); // Simulacija kretanja vozila (1 sekunda između pozicija)
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // Kada se rentanje završi, kreiraj račun
        generateBill();
    }

    private void generateBill() {
        Properties properties = Util.loadProperties();
        String billsPath = Paths.get(properties.getProperty("BILL_FILE_PATH")).toString();

        File directory = new File(billsPath);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        String fileName = "bill_" + vehicleID + "_" + userName + ".txt";
        File billFile = new File(directory, fileName);

        try (FileWriter writer = new FileWriter(billFile)) {
            writer.write("********** Račun za Rentanje **********\n");
            writer.write("ID vozila: " + vehicleID + "\n");
            writer.write("Ime korisnika: " + userName + "\n");
            writer.write("Datum i vreme rentanja: " + dateTime + "\n");
            writer.write("Početne koordinate: " + startCoordinate + "\n");
            writer.write("Krajnje koordinate: " + endCoordinate + "\n");
            writer.write("Trajanje (u sekundama): " + duration + "\n");
            writer.write("Promotivni popust: " + (promoDiscount ? "Da" : "Ne") + "\n");
            writer.write("Cijena: " + String.format("%.2f", price) + " KM\n");
            writer.write("***************************************\n");

            System.out.println("Račun uspješno kreiran: " + billFile.getAbsolutePath());
        } catch (IOException e) {
            System.err.println("Greška pri kreiranju računa: " + e.getMessage());
        }
    }

    public double calculateRentalPrice() {
        Properties properties = Util.loadProperties();
        //System.out.println(properties);
        int scooterUnitPrice = Integer.parseInt(properties.getProperty("SCOOTER_UNIT_PRICE"));
        int carUnitPrice = Integer.parseInt(properties.getProperty("CAR_UNIT_PRICE"));
        int bikeUnitPrice = Integer.parseInt(properties.getProperty("BIKE_UNIT_PRICE"));
        int distanceNarrow = Integer.parseInt(properties.getProperty("DISTANCE_NARROW"));
        int distanceWide = Integer.parseInt(properties.getProperty("DISTANCE_WIDE"));
        int discountProm = Integer.parseInt(properties.getProperty("DISCOUNT_PROM"));
        int discount = Integer.parseInt(properties.getProperty("DISCOUNT"));

        if (!failure) {
            String vehicleType = Util.getVehicleType(vehicleID);
            double basicPrice;
            switch (vehicleType) {
                case "automobil" -> basicPrice = carUnitPrice * duration;
                case "bicikl" -> basicPrice = bikeUnitPrice * duration;
                case "trotinet" -> basicPrice = scooterUnitPrice * duration;
                default -> basicPrice = 0;
            }
            if (Util.isDistanceWide(startCoordinate, endCoordinate)) basicPrice *= distanceWide;
            else basicPrice *= distanceNarrow;
            if (rentalNumber % 10 == 0) {
                basicPrice -= basicPrice * discount / 100.00;
            }
            if (promoDiscount) {
                basicPrice -= basicPrice * discountProm / 100.00;
            }
            return basicPrice;
        }
        return 0;
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

    public String getVehicleID() {
        return vehicleID;
    }

    public void setVehicleID(String vehicleID) {
        this.vehicleID = vehicleID;
    }

    public boolean isFailure() {
        return failure;
    }

    public void setFailure(boolean failure) {
        this.failure = failure;
    }

    public boolean isPromoDiscount() {
        return promoDiscount;
    }

    public void setPromoDiscount(boolean promoDiscount) {
        this.promoDiscount = promoDiscount;
    }

    public int getRentalNumber() {
        return rentalNumber;
    }

    public void setRentalNumber(int rentalNumber) {
        this.rentalNumber = rentalNumber;
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
                ", failure=" + failure +
                ", promoDiscount=" + promoDiscount +
                ", rentalNumber= " + rentalNumber +
                "} " + "\n";
    }
}
