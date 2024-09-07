package org.unibl.etf.pj2.emobility.model.user.document;

public class Passport extends Document {
    private String country;

    public Passport(String country, String number){
        super(number);
        this.country=country;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
