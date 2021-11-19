package com.example.myrasdemo;

public class AddCarHelperClass {
    String body_type, car_name, car_no_plate, fuel_type, is_active, rent_price, seat_capacity, status, transmission_type;

    public AddCarHelperClass(String body_type, String car_name, String car_no_plate, String fuel_type, String is_active, String rent_price, String seat_capacity, String status, String transmission_type) {
        this.body_type = body_type;
        this.car_name = car_name;
        this.car_no_plate = car_no_plate;
        this.fuel_type = fuel_type;
        this.is_active = is_active;
        this.rent_price = rent_price;
        this.seat_capacity = seat_capacity;
        this.status = status;
        this.transmission_type = transmission_type;
    }

    public AddCarHelperClass() {
    }

    public String getBody_type() {
        return body_type;
    }

    public void setBody_type(String body_type) {
        this.body_type = body_type;
    }

    public String getCar_name() {
        return car_name;
    }

    public void setCar_name(String car_name) {
        this.car_name = car_name;
    }

    public String getCar_no_plate() {
        return car_no_plate;
    }

    public void setCar_no_plate(String car_no_plate) {
        this.car_no_plate = car_no_plate;
    }

    public String getFuel_type() {
        return fuel_type;
    }

    public void setFuel_type(String fuel_type) {
        this.fuel_type = fuel_type;
    }

    public String getIs_active() {
        return is_active;
    }

    public void setIs_active(String is_active) {
        this.is_active = is_active;
    }

    public String getRent_price() {
        return rent_price;
    }

    public void setRent_price(String rent_price) {
        this.rent_price = rent_price;
    }

    public String getSeat_capacity() {
        return seat_capacity;
    }

    public void setSeat_capacity(String seat_capacity) {
        this.seat_capacity = seat_capacity;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTransmission_type() {
        return transmission_type;
    }

    public void setTransmission_type(String transmission_type) {
        this.transmission_type = transmission_type;
    }
}
