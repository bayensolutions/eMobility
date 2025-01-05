package org.unibl.etf.pj2.emobility.controllers;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.unibl.etf.pj2.emobility.HelloApplication;
import org.unibl.etf.pj2.emobility.model.ui.BicyclesTableRowData;
import org.unibl.etf.pj2.emobility.model.ui.ResultsTableRowData;
import org.unibl.etf.pj2.emobility.model.ui.CarsTableRowData;
import org.unibl.etf.pj2.emobility.model.ui.ScootersTableRowData;
import org.unibl.etf.pj2.emobility.model.vehicle.Car;
import org.unibl.etf.pj2.emobility.model.vehicle.Vehicle;
import org.unibl.etf.pj2.emobility.util.Util;

import java.util.List;
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
        List<Vehicle> vehicles = Util.loadVehicles(HelloApplication.vehiclesFileName);

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

        ObservableList<CarsTableRowData> carList = FXCollections.observableArrayList(
                HelloApplication.cars.stream().distinct()
                        .map(car -> new CarsTableRowData(
                                car.getId(),
                                car.getProducer(),
                                car.getModel(),
                                car.getPurchasePrice(),
                                car.getCurrentBatteryLevel(),
                                car.getDateOfAcquisition(),
                                car.getDescription()
                        ))
                        .toList()
        );
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

        ObservableList<BicyclesTableRowData> bicycleList = FXCollections.observableArrayList(
                HelloApplication.bicycles.stream().distinct()
                        .map(bicycle -> new BicyclesTableRowData(
                                bicycle.getId(),
                                bicycle.getProducer(),
                                bicycle.getModel(),
                                bicycle.getPurchasePrice(),
                                bicycle.getCurrentBatteryLevel(),
                                bicycle.getRange()
                        ))
                        .toList()
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

        ObservableList<ScootersTableRowData> scootersList = FXCollections.observableArrayList(
                HelloApplication.scooters.stream().distinct()
                        .map(scooter -> new ScootersTableRowData(
                                scooter.getId(),
                                scooter.getProducer(),
                                scooter.getModel(),
                                scooter.getPurchasePrice(),
                                scooter.getCurrentBatteryLevel(),
                                scooter.getMaximumSpeed()
                        ))
                        .toList()
        );

        scootersTable.setItems(scootersList);
    }

}
