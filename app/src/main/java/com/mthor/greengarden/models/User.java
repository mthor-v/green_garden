package com.mthor.greengarden.models;

public class User {

    String fullName;
    String email;
    String city;
    String password;

    public User(String fullName, String email, String city, String password) {
        this.fullName = fullName;
        this.email = email;
        this.city = city;
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
