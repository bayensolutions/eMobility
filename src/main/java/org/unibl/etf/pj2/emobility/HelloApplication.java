package org.unibl.etf.pj2.emobility;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.unibl.etf.pj2.emobility.model.rental.Rental;
import org.unibl.etf.pj2.emobility.model.vehicle.Bicycle;
import org.unibl.etf.pj2.emobility.model.vehicle.Car;
import org.unibl.etf.pj2.emobility.model.vehicle.Scooter;
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
        Util.loadVehicles(vehiclesFileName);

        List<Rental> rentals=Util.loadRentals(rentalsFileName);
        List<Rental> sortedRentals=Util.sortRentals(rentals);

        System.out.println("RENTALS");
        System.out.println(rentals);
        System.out.println("SORTED RENTALS");
        System.out.println(sortedRentals);

        launch();

        /*
        for(Rental r:sortedRentals){
            r.start();
            System.out.println(r);
            //System.out.println(Util.getPath(r.getStartCoordinate(),r.getEndCoordinate()));
        }
        */


        System.out.println(Util.loadRentals(rentalsFileName));
        System.out.println(Util.loadVehicles(vehiclesFileName));
        System.out.println("SUMA: "+Util.calculateTotalIncome(sortedRentals));
        System.out.println("SUMA POPUSTI: "+Util.calculateTotalDiscount(sortedRentals));
        System.out.println("SUMA PROMO POPUSTI: "+Util.calculateTotalPromotions(sortedRentals));
        System.out.println("UKUPNO VOZNJI U UZEM DIJELU GRADA: "+Util.countCityRides(sortedRentals));
        System.out.println("UKUPNO ZA POPRAVKU KVAROVA: "+Util.calculateTotalRepairCosts(sortedRentals,Util.loadVehicles(vehiclesFileName)));
        System.out.println("A"+sortedRentals.get(0).getDateTime()+"A");
    }
}