package org.unibl.etf.pj2.emobility.model.user;

import org.unibl.etf.pj2.emobility.model.user.document.Document;
import org.unibl.etf.pj2.emobility.model.user.document.DriversLicence;

public class User {
    private String name;
    private Document document;
    private DriversLicence driversLicence;

    public User(String name, Document document, DriversLicence driversLicence) {
        this.name = name;
        this.document = document;
        this.driversLicence = driversLicence;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Document getUserID() {
        return document;
    }

    public void setUserID(Document document) {
        this.document = document;
    }

    public DriversLicence getDriversLicence() {
        return driversLicence;
    }

    public void setDriversLicence(DriversLicence driversLicence) {
        this.driversLicence = driversLicence;
    }
}
