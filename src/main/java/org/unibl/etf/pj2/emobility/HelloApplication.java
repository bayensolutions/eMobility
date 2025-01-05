package org.unibl.etf.pj2.emobility;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.unibl.etf.pj2.emobility.model.rental.Rental;
import org.unibl.etf.pj2.emobility.model.vehicle.Bicycle;
import org.unibl.etf.pj2.emobility.model.vehicle.Car;
import org.unibl.etf.pj2.emobility.model.vehicle.Scooter;
import org.unibl.etf.pj2.emobility.model.vehicle.Vehicle;
import org.unibl.etf.pj2.emobility.util.Util;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class HelloApplication extends Application {

    public static final String vehiclesFileName = "PJ2 - projektni zadatak 2024 - Prevozna sredstva.csv";
    public static final String rentalsFileName = "PJ2 - projektni zadatak 2024 - Iznajmljivanja.csv";


    public static List<AbstractMap.SimpleEntry<String, String>> vehiclesList = new ArrayList<>();
    public static List<Car> cars=new ArrayList<>();
    public static List<Bicycle> bicycles=new ArrayList<>();
    public static List<Scooter> scooters=new ArrayList<>();


    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/fxml/hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1000, 700);
        stage.setResizable(false);
        stage.setScene(scene);
        scene.getStylesheets().add(getClass().getResource("/css/styles.css").toExternalForm());
        stage.show();
    }

    public static void main(String[] args) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d.M.yyyy HH:mm");
        launch();


        System.out.println(vehiclesList);

        List<Rental> sortedRentals = Util.loadRentals(rentalsFileName).stream().sorted((r1, r2) -> {
            LocalDateTime dt1 = LocalDateTime.parse(r1.getDateTime().replace("\"", "").trim(), formatter);
            LocalDateTime dt2 = LocalDateTime.parse(r2.getDateTime().replace("\"", "").trim(), formatter);
            return dt1.compareTo(dt2);
        }).distinct().collect(Collectors.toList());

        for (int i = 0; i < sortedRentals.size(); i++) {
            sortedRentals.get(i).setRentalNumber(i + 1);
        }
/*
        for(Rental r:sortedRentals){
            r.start();
            System.out.println(r);
            //System.out.println(Util.getPath(r.getStartCoordinate(),r.getEndCoordinate()));
        }
*/
    }
}