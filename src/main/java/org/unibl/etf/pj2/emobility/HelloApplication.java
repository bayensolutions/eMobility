package org.unibl.etf.pj2.emobility;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.controlsfx.control.tableview2.filter.filtereditor.SouthFilter;
import org.unibl.etf.pj2.emobility.util.Util;

import java.io.IOException;

public class HelloApplication extends Application {

    public static final String vehiclesFileName ="PJ2 - projektni zadatak 2024 - Prevozna sredstva.csv";
    public static final String rentalsFileName ="PJ2 - projektni zadatak 2024 - Iznajmljivanja.csv";

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1000, 600);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        //launch();
        //System.out.println(Util.loadVehicles(vehiclesFileName));
        System.out.println(Util.loadRentals(rentalsFileName));
    }
}