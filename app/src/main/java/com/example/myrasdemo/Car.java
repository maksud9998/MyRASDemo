package com.example.myrasdemo;

public class Car {
    private String car_name;
    private String seat_capacity;
    private Integer rent_price;
    private String car_image;

    public Car(){}
    public Car(String car_name, String seat_capacity, Integer rent_price, String car_image)
    {
        this.car_name = car_name;
        this.seat_capacity = seat_capacity;
        this.rent_price = rent_price;
        this.car_image = car_image;
    }

    public String getCar_name() {
        return car_name;
    }

    public void setCar_name(String car_name) {
        this.car_name = car_name;
    }

    public String getSeat_capacity() {
        return seat_capacity;
    }

    public void setSeat_capacity(String seat_capacity) {
        this.seat_capacity = seat_capacity;
    }

    public Integer getRent_price() {
        return rent_price;
    }

    public void setRent_price(Integer rent_price) {
        this.rent_price = rent_price;
    }

    public String getCar_image() {
        return car_image;
    }

    public void setCar_image(String car_image) {
        this.car_image = car_image;
    }
}
