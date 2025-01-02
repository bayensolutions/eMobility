package org.unibl.etf.pj2.emobility.controllers;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.unibl.etf.pj2.emobility.model.ui.ResultsTableRowData;

import java.time.LocalDate;
import java.util.stream.IntStream;

public class ReportsController {


    @FXML
    private Label totalIncome;

    @FXML
    private Label totalDiscount;

    @FXML
    private Label totalPromotions;
    @FXML
    private Label totalDrives;
    @FXML
    private Label totalMaintain;
    @FXML
    private Label totalRepair;
    @FXML
    private Label totalCosts;
    @FXML
    private Label totalTax;

    @FXML
    public TableView<ResultsTableRowData> dailyReportTable;
    @FXML
    private TableColumn<ResultsTableRowData, String> dateColumn;

    @FXML
    private TableColumn<ResultsTableRowData, Double> totalIncomeColumn;

    @FXML
    private TableColumn<ResultsTableRowData, Double> totalDiscountColumn;

    @FXML
    private TableColumn<ResultsTableRowData, Double> totalPromotionsColumn;

    @FXML
    private TableColumn<ResultsTableRowData, Double> totalDrivesColumn;

    @FXML
    private TableColumn<ResultsTableRowData, Double> totalMaintainColumn;

    @FXML
    private TableColumn<ResultsTableRowData, Double> totalRepairColumn;

    @FXML
    public void initialize() {
        // Set cell value factories
        dateColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDate()));
        totalIncomeColumn.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getTotalIncome()).asObject());
        totalDiscountColumn.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getTotalDiscount()).asObject());
        totalPromotionsColumn.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getTotalPromotions()).asObject());
        totalDrivesColumn.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getTotalDrives()).asObject());
        totalMaintainColumn.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getTotalMaintain()).asObject());
        totalRepairColumn.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getTotalRepair()).asObject());

        // Generate 100 TableRowData objects using Streams
        ObservableList<ResultsTableRowData> data = FXCollections.observableArrayList(
                IntStream.range(0, 100).mapToObj(i -> {
                    LocalDate date = LocalDate.of(2025, 1, 1).plusDays(i);
                    double income = 450 + Math.random() * 150; // Random income between 450 and 600
                    double expense = 30 + Math.random() * 70;  // Random expense between 30 and 100
                    double tax = 15 + Math.random() * 20;      // Random tax between 15 and 35
                    double totalIncome = income + tax;        // Example calculation
                    double netProfit = income - expense;      // Example calculation
                    double profitMargin = netProfit * 0.1;    // Example calculation

                    return new ResultsTableRowData(date.toString(), income, expense, tax, totalIncome, netProfit, profitMargin);
                }).toList()
        );

        dailyReportTable.setItems(data);
    }

}
