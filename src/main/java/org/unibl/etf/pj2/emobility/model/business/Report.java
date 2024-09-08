package org.unibl.etf.pj2.emobility.model.business;

import java.util.List;
import java.util.Map;

public class Report {

    private List<Bill> bills;

    public Report(List<Bill> bills) {
        this.bills = bills;
    }

    // TODO: 1
    public double calculateTotalIncome(){
        return bills.stream().mapToDouble(Bill::getPrice).sum();
    }

    // TODO: 2
    public double calculateTotalDiscount(){
        return bills.stream().mapToDouble(e->e.getPrice()*(1+e.getDiscount()/100.00)).sum();
    }

    // TODO: 3
    public double calculateTotalPromoDiscounts(){
        return 0;
    }

    // TODO: 4
    // TODO: 5
    // TODO: 6
    // TODO: 7
    // TODO: 8
}
