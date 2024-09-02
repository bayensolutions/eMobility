package org.unibl.etf.pj2.emobility.model.vehicle;

public interface IDrivable {
    int chargeBattery(int ammount);
    int dischargeBattery(int ammount);
    void reportBreakdown(String description);
}
