package org.unibl.etf.pj2.emobility.model.business;

public class Bill {
    private double price;
    private int discount;
    private int promoDiscounts;

    public int getDiscount() {
        return discount;
    }

    public double getPrice() {
        return price;
    }

    public int getPromoDiscounts() {
        return promoDiscounts;
    }
}
