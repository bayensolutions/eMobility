package org.unibl.etf.pj2.emobility.util;

import org.unibl.etf.pj2.emobility.HelloApplication;
import org.unibl.etf.pj2.emobility.model.rental.Coordinate;
import org.unibl.etf.pj2.emobility.model.rental.Rental;
import org.unibl.etf.pj2.emobility.model.vehicle.Bicycle;
import org.unibl.etf.pj2.emobility.model.vehicle.Car;
import org.unibl.etf.pj2.emobility.model.vehicle.Scooter;
import org.unibl.etf.pj2.emobility.model.vehicle.Vehicle;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class Util {

    private static final String CONFIG_PATH = "src/main/resources/config.properties";


    public static List<Vehicle> loadVehicles(String fileName) {
        List<Vehicle> vehicles = new ArrayList<>();
        try {
            List<String> lines = Files.readAllLines(Path.of(fileName));

            for (String line : lines) {
                if (lines.indexOf(line) != 0) {
                    String[] parts = line.split(",");
                    String id = parts[0];
                    String producer = parts[1];
                    String model = parts[2];
                    double price = Double.parseDouble(parts[4]);

                    switch (parts[8]) {
                        case "automobil" -> {
                            vehicles.add(new Car(id, producer, model, parts[3], price, 100, parts[7]));
                            HelloApplication.vehiclesList.add(new AbstractMap.SimpleEntry<>(id, parts[8]));
                        }
                        case "bicikl" -> {
                            vehicles.add(new Bicycle(id, producer, model, price, 100, Integer.parseInt(parts[5])));
                            HelloApplication.vehiclesList.add(new AbstractMap.SimpleEntry<>(id, parts[8]));
                        }
                        case "trotinet" -> {
                            vehicles.add(new Scooter(id, producer, model, price, 100, Integer.parseInt(parts[6])));
                            HelloApplication.vehiclesList.add(new AbstractMap.SimpleEntry<>(id, parts[8]));
                        }

                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return vehicles;
    }

    public static List<Rental> loadRentals(String fileName) {
        List<Rental> rentals = new ArrayList<>();
        try {
            List<String> lines = Files.readAllLines(Path.of(fileName));
            //Datum,Korisnik,ID prevoznog sredstva,Pocetna lokacija,Odrediste,Trajanje,Kvar,Promocija
            for (String line : lines) {
                if (lines.indexOf(line) != 0) {
                    String[] parts = line.split(",");
                    String date = parts[0];
                    String userName = parts[1];
                    String id = parts[2];

                    int startX = Integer.parseInt(parts[3].substring(2));
                    int startY = Integer.parseInt(parts[4].substring(0, parts[4].length() - 2));
                    int endX = Integer.parseInt(parts[5].substring(2));
                    int endY = Integer.parseInt(parts[6].substring(0, parts[6].length() - 2));
                    Coordinate start = new Coordinate(startX, startY);
                    Coordinate end = new Coordinate(endX, endY);

                    int duration = Integer.parseInt(parts[7]);
                    boolean failure = "da".equals(parts[8]);
                    boolean discount = "da\"".equals(parts[9]);

                    rentals.add(new Rental(id, date, userName, start, end, duration, failure, discount));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rentals;
    }

    public static String getVehicleType(String vehicleID) {
        for (AbstractMap.SimpleEntry<String, String> entry : HelloApplication.vehiclesList) {
            if (entry.getKey().equals(vehicleID)) {
                return entry.getValue();
            }
        }
        return null;
    }

    public static boolean isDistanceWide(Coordinate begin, Coordinate end) {
        return begin.getX() < 5 || begin.getY() < 5 || begin.getX() > 14 || begin.getY() > 14 || end.getX() < 5 || end.getY() < 5 || end.getX() > 14 || end.getY() > 14;
    }

    public static List<Coordinate> getPath(Coordinate begin, Coordinate end){
        List<Coordinate> path=new ArrayList<>();
        int currentX= begin.getX(), currentY= begin.getY();
        path.add(new Coordinate(currentX,currentY));
        while(currentX!=end.getX()) {
            if (begin.getX() < end.getX()) {
                path.add(new Coordinate(++currentX, currentY));
            } else {
                path.add(new Coordinate(--currentX, currentY));
            }
        }
        while(currentY!=end.getY()){
            if(begin.getY()<end.getY()){
                path.add(new Coordinate(currentX,++currentY));
            }
            else{
                path.add(new Coordinate(currentX,--currentY));
            }
        }
        return path;
    }

    public static Properties loadProperties() {
        Properties properties = new Properties();
        FileInputStream fip;
        try {
            fip = new FileInputStream(CONFIG_PATH);
            properties.load(fip);
        } catch (IOException e) {
            //System.Logger.getLogger(Util.class.getName()).log(System.Logger.Level.SEVERE, e.fillInStackTrace().toString());
        }
        return properties;
    }
}
