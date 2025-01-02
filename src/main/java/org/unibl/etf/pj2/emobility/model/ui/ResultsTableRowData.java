package org.unibl.etf.pj2.emobility.model.ui;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

public class ResultsTableRowData {
    private final SimpleStringProperty date;
    private final SimpleDoubleProperty totalIncome;
    private final SimpleDoubleProperty totalDiscount;
    private final SimpleDoubleProperty totalPromotions;
    private final SimpleDoubleProperty totalDrives;
    private final SimpleDoubleProperty totalMaintain;
    private final SimpleDoubleProperty totalRepair;

    public ResultsTableRowData(String date, double totalIncome, double totalDiscount,
                               double totalPromotions, double totalDrives,
                               double totalMaintain, double totalRepair) {
        this.date = new SimpleStringProperty(date);
        this.totalIncome = new SimpleDoubleProperty(totalIncome);
        this.totalDiscount = new SimpleDoubleProperty(totalDiscount);
        this.totalPromotions = new SimpleDoubleProperty(totalPromotions);
        this.totalDrives = new SimpleDoubleProperty(totalDrives);
        this.totalMaintain = new SimpleDoubleProperty(totalMaintain);
        this.totalRepair = new SimpleDoubleProperty(totalRepair);
    }

    public String getDate() {
        return date.get();
    }

    public double getTotalIncome() {
        return totalIncome.get();
    }

    public double getTotalDiscount() {
        return totalDiscount.get();
    }

    public double getTotalPromotions() {
        return totalPromotions.get();
    }

    public double getTotalDrives() {
        return totalDrives.get();
    }

    public double getTotalMaintain() {
        return totalMaintain.get();
    }

    public double getTotalRepair() {
        return totalRepair.get();
    }
}

