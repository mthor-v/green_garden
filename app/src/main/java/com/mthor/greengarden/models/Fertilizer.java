package com.mthor.greengarden.models;

public class Fertilizer {

    private float kilos;
    private float price;
    private String month;

    public Fertilizer(float kilos, float price, String month) {
        this.kilos = kilos;
        this.price = price;
        this.month = month;
    }

    public float getKilos() {
        return kilos;
    }

    public void setKilos(float kilos) {
        this.kilos = kilos;
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
