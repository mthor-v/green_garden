package com.mthor.greengarden.models;

public class Energy {

    private float kiloWats;
    private float price;
    private String month;

    public Energy(float kiloWats, float price, String month) {
        this.kiloWats = kiloWats;
        this.price = price;
        this.month = month;
    }

    public float getKiloWats() {
        return kiloWats;
    }

    public void setKiloWats(float kiloWats) {
        this.kiloWats = kiloWats;
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
