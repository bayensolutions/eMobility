package org.unibl.etf.pj2.emobility.controllers;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.unibl.etf.pj2.emobility.model.ui.BicyclesTableRowData;
import org.unibl.etf.pj2.emobility.model.ui.ResultsTableRowData;
import org.unibl.etf.pj2.emobility.model.ui.CarsTableRowData;
import org.unibl.etf.pj2.emobility.model.ui.ScootersTableRowData;
import org.unibl.etf.pj2.emobility.model.vehicle.Car;

import java.util.stream.IntStream;

public class VehiclesController {
    // Cars
    @FXML
    public TableView<CarsTableRowData> carsTable;

    @FXML
    private TableColumn<CarsTableRowData, Integer> carsNoColumn;

    @FXML
    private TableColumn<CarsTableRowData, String> carsIdColumn;

    @FXML
    private TableColumn<CarsTableRowData, String> carsProducerColumn;

    @FXML
    private TableColumn<CarsTableRowData, String> carsModelColumn;

    @FXML
    private TableColumn<CarsTableRowData, String> carsDateTimeColumn;

    @FXML
    private TableColumn<CarsTableRowData, Double> carsPurchasePriceColumn;

    @FXML
    private TableColumn<CarsTableRowData, Integer> carsBatteryLevelColumn;

    @FXML
    private TableColumn<CarsTableRowData, String> carsDescriptionColumn;

    // Bicycles
    @FXML
    public TableView<BicyclesTableRowData> bicyclesTable;

    @FXML
    private TableColumn<BicyclesTableRowData, Integer> bicyclesNoColumn;

    @FXML
    private TableColumn<BicyclesTableRowData, String> bicyclesIdColumn;

    @FXML
    private TableColumn<BicyclesTableRowData, String> bicyclesProducerColumn;

    @FXML
    private TableColumn<BicyclesTableRowData, String> bicyclesModelColumn;

    @FXML
    private TableColumn<BicyclesTableRowData, Double> bicyclesPurchasePriceColumn;

    @FXML
    private TableColumn<BicyclesTableRowData, Integer> bicyclesBatteryLevelColumn;

    @FXML
    private TableColumn<BicyclesTableRowData, Integer> bicyclesRangeColumn;

    // Scooters
    @FXML
    public TableView<ScootersTableRowData> scootersTable;
    @FXML
    private TableColumn<ScootersTableRowData, Integer> scootersNoColumn;

    @FXML
    private TableColumn<ScootersTableRowData, String> scootersIdColumn;

    @FXML
    private TableColumn<ScootersTableRowData, String> scootersProducerColumn;

    @FXML
    private TableColumn<ScootersTableRowData, String> scootersModelColumn;

    @FXML
    private TableColumn<ScootersTableRowData, Double> scootersPurchasePriceColumn;

    @FXML
    private TableColumn<ScootersTableRowData, Integer> scootersBatteryLevelColumn;

    @FXML
    private TableColumn<ScootersTableRowData, Integer> scootersMaximumSpeedColumn;

    @FXML
    public void initialize() {
        // TODO Zamijeniti sa podacima koji se ucitavaju iz fajla, Util !!!
        carsNoColumn.setCellValueFactory(cellData -> {
            int index = carsTable.getItems().indexOf(cellData.getValue()) + 1;
            return new SimpleIntegerProperty(index).asObject();
        });
        carsIdColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getId()));
        carsProducerColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getProducer()));
        carsModelColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getModel()));
        carsDateTimeColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDateTime()));
        carsPurchasePriceColumn.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getPurchasePrice()).asObject());
        carsBatteryLevelColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getCurrentBatteryLevel()).asObject());
        carsDescriptionColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDescription()));


        // Create some random car data
        ObservableList<CarsTableRowData> carList = FXCollections.observableArrayList(
                IntStream.range(0, 100).mapToObj(i -> new CarsTableRowData(
                        "ID" + String.format("%03d", i + 1), // Generate IDs like ID001, ID002, ...
                        "Producer" + (i + 1), // Example producer name
                        "Model" + (i + 1), // Example model name
                        20000.0 + (i * 1000), // Static date, change it as necessary
                        80 + (i % 20), // Example price, increases with each iteration
                        "2023-01-01", // Example battery level, varies
                        "Description for car " + (i + 1) // Example description
                )).toList()
        );

        // Set the table items
        carsTable.setItems(carList);


        // Bicycles table initialization
        bicyclesNoColumn.setCellValueFactory(cellData -> {
            int index = bicyclesTable.getItems().indexOf(cellData.getValue()) + 1;
            return new SimpleIntegerProperty(index).asObject();
        });
        bicyclesIdColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getId()));
        bicyclesProducerColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getProducer()));
        bicyclesModelColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getModel()));
        bicyclesPurchasePriceColumn.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getPurchasePrice()).asObject());
        bicyclesBatteryLevelColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getCurrentBatteryLevel()).asObject());
        bicyclesRangeColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getRange()).asObject());

        // Create random bicycle data
        ObservableList<BicyclesTableRowData> bicycleList = FXCollections.observableArrayList(
                IntStream.range(0, 100).mapToObj(i -> new BicyclesTableRowData(
                        "ID" + String.format("%03d", i + 1),
                        "Producer" + (i + 1),
                        "Model" + (i + 1),
                        1000.0 + (i * 200),
                        60 + (i % 40),
                        25 + (i % 10) // Random range between 25 and 34
                )).toList()
        );
        bicyclesTable.setItems(bicycleList);

        // Scooters table initialization
        scootersNoColumn.setCellValueFactory(cellData -> {
            int index = scootersTable.getItems().indexOf(cellData.getValue()) + 1;
            return new SimpleIntegerProperty(index).asObject();
        });
        scootersIdColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getId()));
        scootersProducerColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getProducer()));
        scootersModelColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getModel()));
        scootersPurchasePriceColumn.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getPurchasePrice()).asObject());
        scootersBatteryLevelColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getCurrentBatteryLevel()).asObject());
        scootersMaximumSpeedColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getMaximumSpeed()).asObject());

        // Create random scooter data
        ObservableList<ScootersTableRowData> scooterList = FXCollections.observableArrayList(
                IntStream.range(0, 100).mapToObj(i -> new ScootersTableRowData(
                        i + 1, // No for scooter
                        "ID" + String.format("%03d", i + 1),
                        "Producer" + (i + 1),
                        "Model" + (i + 1),
                        1500.0 + (i * 300),
                        50 + (i % 30),
                        40 + (i % 20) // Random maximum speed between 40 and 59
                )).toList()
        );
        scootersTable.setItems(scooterList);
    }

}
