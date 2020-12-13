package com.carmgmt.model;

public class Car {
    private int carid;
    private String carname;
    private String cartype;
    private int price;
    private String chargingbattery;
    private String fillingtank;

    public Car() {
    }

    public int getCarid() {
        return carid;
    }

    public void setCarid(int carid) {
        this.carid = carid;
    }

    public String getCarname() {
        return carname;
    }

    public void setCarname(String carname) {
        this.carname = carname;
    }

    public String getCartype() {
        return cartype;
    }

    public void setCartype(String cartype) {
        this.cartype = cartype;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getChargingbattery() {
        return chargingbattery;
    }

    public void setChargingbattery(String chargingbattery) {
        this.chargingbattery = chargingbattery;
    }

    public String getFillingtank() {
        return fillingtank;
    }

    public void setFillingtank(String fillingtank) {
        this.fillingtank = fillingtank;
    }
}

