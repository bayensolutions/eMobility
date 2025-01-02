package org.unibl.etf.pj2.emobility;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.geometry.Insets;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloController {

    @FXML
    public Button repairsButton;
    @FXML
    public Button reportsButton;
    @FXML
    public Button vehiclesButton;
    @FXML
    private GridPane gridPane;

    private static final int SIZE = 20; // Veličina grida

    @FXML
    public void initialize() {
        // Dinamički kreiramo TextFieldove i dodajemo ih u GridPane
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                TextField cell = new TextField();
                cell.setPrefSize(40, 40); // Veličina svakog polja
                cell.setEditable(false); // Onemogućavamo editovanje
                cell.getStyleClass().add("text-field");

                if (shouldBeColored(row, col)) {
                    cell.getStyleClass().add("highlight"); // Dodajemo klasu za bojenje
                }

                if (row == 5 && col == 5) {
                    cell.setText("T");
                }

                // Dodajemo TextField u GridPane
                gridPane.add(cell, col, row);
            }
        }
    }


    private boolean shouldBeColored(int row, int col) {
        return (row > 4 && row < 15 && col > 4 && col < 15);
    }

    public void openVehiclesWindow(ActionEvent actionEvent) {
        try {
            // Load the FXML for the reports window using the correct resource path
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/vehicles.fxml"));
            Parent root = loader.load();

            // Create a new stage for the reports window
            Stage stage = new Stage();
            stage.setTitle("Vozila");
            stage.setScene(new Scene(root, 840, 400)); // Adjust the size as needed
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void openRepairsWindow(ActionEvent actionEvent) {
        try {
            // Load the FXML for the reports window using the correct resource path
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/failures.fxml"));
            Parent root = loader.load();

            // Create a new stage for the reports window
            Stage stage = new Stage();
            stage.setTitle("Kvarovi");
            stage.setScene(new Scene(root, 840, 400)); // Adjust the size as needed
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void openReportsWindow(ActionEvent event) {
        try {
            // Load the FXML for the reports window using the correct resource path
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/reports.fxml"));
            Parent root = loader.load();

            // Create a new stage for the reports window
            Stage stage = new Stage();
            stage.setTitle("Reports");
            stage.setScene(new Scene(root, 840, 400)); // Adjust the size as needed
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
