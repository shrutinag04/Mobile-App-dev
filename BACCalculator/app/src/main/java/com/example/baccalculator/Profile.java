package com.example.baccalculator;

public class Profile {
    String gender;
    Double weight;
    public Profile(){

    }

    public Profile(String gender, Double weight) {
        this.gender = gender;
        this.weight = weight;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }
}
