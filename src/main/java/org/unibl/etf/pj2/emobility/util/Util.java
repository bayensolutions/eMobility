package org.unibl.etf.pj2.emobility.util;

import org.unibl.etf.pj2.emobility.model.rental.Coordinate;
import org.unibl.etf.pj2.emobility.model.rental.Rental;
import org.unibl.etf.pj2.emobility.model.vehicle.Bicycle;
import org.unibl.etf.pj2.emobility.model.vehicle.Car;
import org.unibl.etf.pj2.emobility.model.vehicle.Scooter;
import org.unibl.etf.pj2.emobility.model.vehicle.Vehicle;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Util {

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
                        case "automobil" -> vehicles.add(new Car(id, producer, model, parts[3], price, 100, parts[7]));
                        case "bicikl" -> vehicles.add(new Bicycle(id, producer, model, price, 100, Integer.parseInt(parts[5])));
                        case "trotinet" -> vehicles.add(new Scooter(id, producer, model, price, 100, Integer.parseInt(parts[6])));
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
                    boolean discount = "da".equals(parts[9]);

                    rentals.add(new Rental(date, userName, start, end, duration));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rentals;
    }
}
