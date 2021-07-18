package com.example.myrasdemo;

public class Car {
    String id, car_name,car_image, car_no_plate, body_type, transmission_type, seat_capacity, fuel_type, booking_status, car_status;
    Integer rent_price;
    public Car() {
    }

    public Car(String id, Integer rent_price, String car_name, String car_image, String car_no_plate, String body_type, String transmission_type, String seat_capacity, String fuel_type, String booking_status, String car_status) {
        this.id = id;
        this.rent_price = rent_price;
        this.car_name = car_name;
        this.car_image = car_image;
        this.car_no_plate = car_no_plate;
        this.body_type = body_type;
        this.transmission_type = transmission_type;
        this.seat_capacity = seat_capacity;
        this.fuel_type = fuel_type;
        this.booking_status = booking_status;
        this.car_status = car_status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getRent_price() {
        return rent_price;
    }

    public void setRent_price(Integer rent_price) {
        this.rent_price = rent_price;
    }

    public String getCar_name() {
        return car_name;
    }

    public void setCar_name(String car_name) {
        this.car_name = car_name;
    }

    public String getCar_image() {
        return car_image;
    }

    public void setCar_image(String car_image) {
        this.car_image = car_image;
    }

    public String getCar_no_plate() {
        return car_no_plate;
    }

    public void setCar_no_plate(String car_no_plate) {
        this.car_no_plate = car_no_plate;
    }

    public String getBody_type() {
        return body_type;
    }

    public void setBody_type(String body_type) {
        this.body_type = body_type;
    }

    public String getTransmission_type() {
        return transmission_type;
    }

    public void setTransmission_type(String transmission_type) {
        this.transmission_type = transmission_type;
    }

    public String getSeat_capacity() {
        return seat_capacity;
    }

    public void setSeat_capacity(String seat_capacity) {
        this.seat_capacity = seat_capacity;
    }

    public String getFuel_type() {
        return fuel_type;
    }

    public void setFuel_type(String fuel_type) {
        this.fuel_type = fuel_type;
    }

    public String getBooking_status() {
        return booking_status;
    }

    public void setBooking_status(String booking_status) {
        this.booking_status = booking_status;
    }

    public String getCar_status() {
        return car_status;
    }

    public void setCar_status(String car_status) {
        this.car_status = car_status;
    }
}