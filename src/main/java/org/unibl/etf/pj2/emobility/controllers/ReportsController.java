package org.unibl.etf.pj2.emobility.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.unibl.etf.pj2.emobility.HelloApplication;
import org.unibl.etf.pj2.emobility.model.rental.Rental;
import org.unibl.etf.pj2.emobility.model.ui.ResultsTableRowData;
import org.unibl.etf.pj2.emobility.util.Util;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    public void initialize() {
        List<Rental> rentals = HelloApplication.sortedRentals;

        double totalIncomeValue = Util.calculateTotalIncome(rentals);
        double totalDiscountValue = Util.calculateTotalDiscount(rentals);
        double totalPromotionsValue = Util.calculateTotalPromotions(rentals);
        double totalDrivesValue = Util.countCityRides(rentals);
        double totalMaintainValue = 0.2 * totalIncomeValue;
        double totalRepairValue = Util.calculateTotalRepairCosts(rentals, Util.loadVehicles(HelloApplication.vehiclesFileName));
        double totalCostsValue=0.2*(totalIncomeValue-totalMaintainValue);
        double totalTaxValue=0.1*(totalIncomeValue-totalMaintainValue-totalRepairValue-totalCostsValue);

        totalIncome.setText(formatNumber(totalIncomeValue,2));
        totalDiscount.setText(formatNumber(totalDiscountValue,2));
        totalPromotions.setText(formatNumber(totalPromotionsValue,2));
        totalDrives.setText(formatNumber(totalDrivesValue,0));
        totalMaintain.setText(formatNumber(totalMaintainValue,2));
        totalRepair.setText(formatNumber(totalRepairValue,2));
        totalCosts.setText(formatNumber(totalCostsValue,2));
        totalTax.setText(formatNumber(totalTaxValue,2));

        Map<String, ResultsTableRowData> reportData = rentals.stream()
                .collect(Collectors.groupingBy(
                        r -> {
                            String dateTime = r.getDateTime();
                            return (dateTime != null && dateTime.contains(" ")) ? dateTime.trim().split(" ")[0] : "NEPOZNAT DATUM";
                        },
                        Collectors.collectingAndThen(Collectors.toList(), list -> {

                            double totalIncome = Util.calculateTotalIncome(list);
                            double totalDiscount = Util.calculateTotalDiscount(list);
                            double totalPromotions = Util.calculateTotalPromotions(list);
                            double totalDrives = Util.countCityRides(list);
                            double totalMaintain = 0.2*totalIncome;
                            double totalRepair = Util.calculateTotalRepairCosts(list,Util.loadVehicles(HelloApplication.vehiclesFileName));

                            return new ResultsTableRowData(list.get(0).getDateTime().split(" ")[0],
                                    totalIncome, totalDiscount, totalPromotions, totalDrives, totalMaintain, totalRepair);
                        })
                ));

        ObservableList<ResultsTableRowData> data = FXCollections.observableArrayList(reportData.values());

        dateColumn.setCellValueFactory(cellData -> cellData.getValue().dateProperty());
        totalIncomeColumn.setCellValueFactory(cellData -> cellData.getValue().totalIncomeProperty().asObject());
        totalDiscountColumn.setCellValueFactory(cellData -> cellData.getValue().totalDiscountProperty().asObject());
        totalPromotionsColumn.setCellValueFactory(cellData -> cellData.getValue().totalPromotionsProperty().asObject());
        totalDrivesColumn.setCellValueFactory(cellData -> cellData.getValue().totalDrivesProperty().asObject());
        totalMaintainColumn.setCellValueFactory(cellData -> cellData.getValue().totalMaintainProperty().asObject());
        totalRepairColumn.setCellValueFactory(cellData -> cellData.getValue().totalRepairProperty().asObject());

        formatColumn(totalIncomeColumn,2);
        formatColumn(totalDiscountColumn,2);
        formatColumn(totalPromotionsColumn,2);
        formatColumn(totalDrivesColumn,0);
        formatColumn(totalMaintainColumn,2);
        formatColumn(totalRepairColumn,2);

        dailyReportTable.setItems(data);
    }

    private void formatColumn(TableColumn<ResultsTableRowData, Double> column, int decimalPlaces) {
        column.setCellFactory(tc -> new TableCell<ResultsTableRowData, Double>() {
            @Override
            protected void updateItem(Double value, boolean empty) {
                super.updateItem(value, empty);
                if (empty || value == null) {
                    setText("");
                } else {
                    // Formatiraj broj sa 2 decimale
                    setText(String.format("%."+decimalPlaces+"f", value));
                }
            }
        });
    }

    private String formatNumber(double value, int decimalPlaces) {
        return String.format("%."+decimalPlaces+"f", value);
    }
}
