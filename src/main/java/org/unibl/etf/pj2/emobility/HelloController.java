package org.unibl.etf.pj2.emobility;

import javafx.application.Platform;
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
import org.unibl.etf.pj2.emobility.model.rental.Coordinate;
import org.unibl.etf.pj2.emobility.model.rental.Rental;
import org.unibl.etf.pj2.emobility.util.Util;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

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
    private TextField[][] cells = new TextField[SIZE][SIZE];
    private Map<String, int[]> vehiclePositions = new ConcurrentHashMap<>();
    private final ExecutorService executor = Executors.newFixedThreadPool(10);


    @FXML
    public void initialize() {
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                TextField cell = new TextField();
                cell.setPrefSize(40, 40);
                cell.setEditable(false);
                cell.getStyleClass().add("text-field");

                if (shouldBeColored(row, col)) {
                    cell.getStyleClass().add("highlight");
                }

                cells[row][col] = cell;
                gridPane.add(cell, col, row);
            }
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d.M.yyyy HH:mm");

        List<Rental> sortedRentals = Util.loadRentals(HelloApplication.rentalsFileName).stream()
                .sorted((r1, r2) -> {
                    LocalDateTime dt1 = LocalDateTime.parse(r1.getDateTime().replace("\"", "").trim(), formatter);
                    LocalDateTime dt2 = LocalDateTime.parse(r2.getDateTime().replace("\"", "").trim(), formatter);
                    return dt1.compareTo(dt2);
                })
                .distinct()
                .collect(Collectors.toList());


        new Thread(() -> {
            LocalDateTime previousDateTime = null;

            for (Rental rental : sortedRentals) {
                LocalDateTime rentalDateTime = LocalDateTime.parse(rental.getDateTime().replace("\"", "").trim(), formatter);

                if (previousDateTime != null && !rentalDateTime.isEqual(previousDateTime)) {
                    try {
                        Thread.sleep(10000); // Pauza od 10 sekundi
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                // Pokrećemo vozilo
                Platform.runLater(() ->
                        startVehicle(rental.getVehicleID(), rental.getStartCoordinate(), rental.getEndCoordinate())
                );

                // Ažuriramo prethodni datum/vreme
                previousDateTime = rentalDateTime;
            }
        }).start();
    }


    private void startVehicle(String vehicleId, Coordinate begin, Coordinate end) {
        if (vehiclePositions.containsKey(vehicleId)) {
            System.out.println("Vozilo već postoji: " + vehicleId);
            return;
        }

        vehiclePositions.put(vehicleId, new int[]{begin.getX(), begin.getY()});

        List<Coordinate> path = Util.getPath(begin, end);

        executor.execute(() -> {
            try {
                for (Coordinate coordinate : path) {
                    int finalRow = coordinate.getX();
                    int finalCol = coordinate.getY();

                    Platform.runLater(() -> {
                        clearVehicle(vehicleId);
                        cells[finalRow][finalCol].setText(vehicleId);
                        vehiclePositions.put(vehicleId, new int[]{finalRow, finalCol});
                    });

                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }


    private void clearVehicle(String vehicleId) {
        int[] position = vehiclePositions.get(vehicleId);
        if (position != null) {
            int row = position[0];
            int col = position[1];
            cells[row][col].setText(""); // Brišemo tekst u ćeliji
        }
    }


    private void clearGrid() {
        // Brišemo sva polja u matrici
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                cells[row][col].setText("");
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
            stage.setTitle("Izvještaji");
            stage.setScene(new Scene(root, 840, 400)); // Adjust the size as needed
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
