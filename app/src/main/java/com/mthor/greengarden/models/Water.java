package com.mthor.greengarden.models;

public class Water {

    private float liters;
    private float price;
    private String month;

    public Water(float liters, float price, String month) {
        this.liters = liters;
        this.price = price;
        this.month = month;
    }

    public float getLiters() {
        return liters;
    }

    public void setLiters(float liters) {
        this.liters = liters;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }
}
