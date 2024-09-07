package org.unibl.etf.pj2.emobility.model.vehicle;

interface IDrivable {
    int chargeBattery(int amount);
    int dischargeBattery(int amount);
    void reportBreakdown(String description);
}
