package org.unibl.etf.pj2.emobility;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.controlsfx.control.tableview2.filter.filtereditor.SouthFilter;
import org.unibl.etf.pj2.emobility.model.rental.Rental;
import org.unibl.etf.pj2.emobility.model.vehicle.Vehicle;
import org.unibl.etf.pj2.emobility.util.Util;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class HelloApplication extends Application {

    public static final String vehiclesFileName = "PJ2 - projektni zadatak 2024 - Prevozna sredstva.csv";
    public static final String rentalsFileName = "PJ2 - projektni zadatak 2024 - Iznajmljivanja.csv";


    public static List<AbstractMap.SimpleEntry<String, String>> vehiclesList = new ArrayList<>();


    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1000, 600);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d.M.yyyy HH:mm");
        //launch();

        List<Vehicle> vehicles = Util.loadVehicles(vehiclesFileName);
        System.out.println(vehiclesList);

        List<Rental> sortedRentals = Util.loadRentals(rentalsFileName).stream().sorted((r1, r2) -> {
            LocalDateTime dt1 = LocalDateTime.parse(r1.getDateTime().replace("\"", "").trim(), formatter);
            LocalDateTime dt2 = LocalDateTime.parse(r2.getDateTime().replace("\"", "").trim(), formatter);
            return dt1.compareTo(dt2);
        }).distinct().collect(Collectors.toList());
        System.out.println(sortedRentals);
        for (Rental r : sortedRentals) {
            System.out.println(r.calculateRental());
        }


    }
}