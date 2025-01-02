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
import org.unibl.etf.pj2.emobility.model.ui.CarsTableRowData;
import org.unibl.etf.pj2.emobility.model.ui.FailuresTableRowData;
import org.unibl.etf.pj2.emobility.model.ui.ScootersTableRowData;

import java.util.stream.IntStream;

public class FailuresController {
    @FXML
    public TableView<FailuresTableRowData> failuresTable;

    @FXML
    public TableColumn<FailuresTableRowData, Integer> failuresNoColumn;

    @FXML
    public TableColumn<FailuresTableRowData, String> failuresIdColumn;

    @FXML
    public TableColumn<FailuresTableRowData, String> failuresVehicleTypeColumn;

    @FXML
    public TableColumn<FailuresTableRowData, String> failuresDateTimeColumn;

    @FXML
    public TableColumn<FailuresTableRowData, String> failuresDescriptionColumn;

    @FXML
    public void initialize() {
        // TODO Zamijeniti sa podacima koji se ucitavaju iz fajla, Util !!!
        failuresNoColumn.setCellValueFactory(cellData -> {
            int index = failuresTable.getItems().indexOf(cellData.getValue()) + 1;
            return new SimpleIntegerProperty(index).asObject();
        });
        failuresIdColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getId()));
        failuresDateTimeColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDateTime()));
        failuresVehicleTypeColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getVehicleType()));
        failuresDescriptionColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDescription()));


        // Create some random car data
        ObservableList<FailuresTableRowData> failuresList = FXCollections.observableArrayList(
                IntStream.range(0, 100).mapToObj(i -> new FailuresTableRowData(
                        "ID" + String.format("%03d", i + 1), // Generate IDs like ID001, ID002, ...
                        "Vehicle" + (i + 1), // Example producer name
                        "2025-01-02", // Example model name
                        "Description for car " + (i + 1) // Example description
                )).toList()
        );

        // Set the table items
        failuresTable.setItems(failuresList);
    }
}
