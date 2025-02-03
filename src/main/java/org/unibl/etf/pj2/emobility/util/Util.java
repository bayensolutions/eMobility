package org.unibl.etf.pj2.emobility.util;

import org.unibl.etf.pj2.emobility.HelloApplication;
import org.unibl.etf.pj2.emobility.model.rental.Coordinate;
import org.unibl.etf.pj2.emobility.model.rental.Rental;
import org.unibl.etf.pj2.emobility.model.vehicle.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

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
                            Car car = new Car(id, producer, model, parts[3], price, 100, parts[7]);
                            vehicles.add(car);
                            HelloApplication.vehiclesList.add(new AbstractMap.SimpleEntry<>(id, parts[8]));
                            HelloApplication.cars.add(car);
                        }
                        case "bicikl" -> {
                            Bicycle bicycle = new Bicycle(id, producer, model, price, 100, Integer.parseInt(parts[5]));
                            vehicles.add(bicycle);
                            HelloApplication.vehiclesList.add(new AbstractMap.SimpleEntry<>(id, parts[8]));
                            HelloApplication.bicycles.add(bicycle);
                        }
                        case "trotinet" -> {
                            Scooter scooter = new Scooter(id, producer, model, price, 100, Integer.parseInt(parts[6]));
                            vehicles.add(scooter);
                            HelloApplication.vehiclesList.add(new AbstractMap.SimpleEntry<>(id, parts[8]));
                            HelloApplication.scooters.add(scooter);
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

    public static List<Rental> sortRentals(List<Rental> rentals){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d.M.yyyy HH:mm");
        List<Rental> sortedRentals = rentals.stream().sorted((r1, r2) -> {
            LocalDateTime dt1 = LocalDateTime.parse(r1.getDateTime().replace("\"", "").trim(), formatter);
            LocalDateTime dt2 = LocalDateTime.parse(r2.getDateTime().replace("\"", "").trim(), formatter);
            return dt1.compareTo(dt2);
        }).distinct().collect(Collectors.toList());
        for (int i = 0; i < sortedRentals.size(); i++) {
            sortedRentals.get(i).setRentalNumber(i + 1);
        }
        return sortedRentals;
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

    public static List<Coordinate> getPath(Coordinate begin, Coordinate end) {
        List<Coordinate> path = new ArrayList<>();
        int currentX = begin.getX(), currentY = begin.getY();
        path.add(new Coordinate(currentX, currentY));
        while (currentX != end.getX()) {
            if (begin.getX() < end.getX()) {
                path.add(new Coordinate(++currentX, currentY));
            } else {
                path.add(new Coordinate(--currentX, currentY));
            }
        }
        while (currentY != end.getY()) {
            if (begin.getY() < end.getY()) {
                path.add(new Coordinate(currentX, ++currentY));
            } else {
                path.add(new Coordinate(currentX, --currentY));
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

    public static double calculateTotalIncome(List<Rental> rentals) {
        return rentals.stream()
                .distinct()
                .mapToDouble(Rental::getPrice)
                .sum();
    }

    public static double calculateTotalPromotions(List<Rental> rentals) {
        Properties properties = Util.loadProperties();
        int discountProm = Integer.parseInt(properties.getProperty("DISCOUNT_PROM"));

        return rentals.stream()
                .filter(rental -> rental.isPromoDiscount() && !rental.isFailure()) // Samo promo rentanja
                .mapToDouble(rental -> {
                    double priceWithDiscount = rental.calculateRentalPrice();
                    double priceWithoutDiscount = priceWithDiscount / (1 - discountProm / 100.0);
                    return priceWithoutDiscount - priceWithDiscount; // Samo iznos promocije
                })
                .sum();
    }

    public static double calculateTotalDiscount(List<Rental> rentals) {
        Properties properties = Util.loadProperties();
        int discountProm = Integer.parseInt(properties.getProperty("DISCOUNT_PROM"));
        int discount = Integer.parseInt(properties.getProperty("DISCOUNT"));

        return rentals.stream()
                .filter(rental -> !rental.isFailure()) // Isključujemo neuspešna rentanja
                .mapToDouble(rental -> {
                    double priceWithDiscounts = rental.calculateRentalPrice(); // Konačna cena sa popustima
                    double priceWithoutDiscounts = priceWithDiscounts;

                    // Ako je promo popust, skaliramo cenu unazad
                    if (rental.isPromoDiscount()) {
                        priceWithoutDiscounts /= (1 - discountProm / 100.0);
                    }
                    // Ako je standardni popust (svako 10. rentanje), dodatno skaliramo unazad
                    if (rental.getRentalNumber() % 10 == 0) {
                        priceWithoutDiscounts /= (1 - discount / 100.0);
                    }

                    return priceWithoutDiscounts - priceWithDiscounts; // Razlika je ukupni popust
                })
                .sum();
    }

    public static long countCityRides(List<Rental> rentals) {
        return rentals.stream()
                //.filter(rental -> !isDistanceWide(rental.getStartCoordinate(), rental.getEndCoordinate())) // Filtriramo vožnje koje nisu u široj zoni
                .count(); // Prebrojavamo
    }

    public static double calculateTotalRepairCosts(List<Rental> rentals, List<Vehicle> vehicles) {
        double totalRepairCosts = 0.0;

        for (Rental rental : rentals) {
            if (rental.isFailure()) {  // Ako je bilo kvara
                // Pronalazimo vozilo na osnovu vehicleID
                Vehicle vehicle = null;
                for (Vehicle v : vehicles) {
                    if (v.getId().equals(rental.getVehicleID())) {
                        vehicle = v;
                        break;
                    }
                }

                if (vehicle != null) {
                    double repairCoefficient = 0.0;

                    if (vehicle instanceof ICar) {
                        repairCoefficient = ((ICar) vehicle).getRepairCoefficient();
                    }
                    else if (vehicle instanceof IBicycle) {
                        repairCoefficient = ((IBicycle) vehicle).getRepairCoefficient();
                    }
                    else if (vehicle instanceof IScooter) {
                        repairCoefficient = ((IScooter) vehicle).getRepairCoefficient();
                    }

                    totalRepairCosts += repairCoefficient * vehicle.getPurchasePrice();
                }
            }
        }
        return totalRepairCosts;
    }



}
