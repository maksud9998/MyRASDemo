package com.example.myrasdemo;

public class BookingHelperClass {
    String booking_id,car_name,car_image,car_no_plate,kms,start_date,start_time,end_date,end_time,phone_no1,status,trip_fare_price;

    public BookingHelperClass(String booking_id, String car_name, String car_image, String car_no_plate, String kms, String start_date, String start_time, String end_date, String end_time, String phone_no1, String status, String trip_fare_price) {
        this.booking_id = booking_id;
        this.car_name = car_name;
        this.car_image = car_image;
        this.car_no_plate = car_no_plate;
        this.kms = kms;
        this.start_date = start_date;
        this.start_time = start_time;
        this.end_date = end_date;
        this.end_time = end_time;
        this.phone_no1 = phone_no1;
        this.status = status;
        this.trip_fare_price = trip_fare_price;
    }

    public BookingHelperClass() {
    }

    public String getbooking_id() {
        return booking_id;
    }

    public void setbooking_id(String booking_id) {
        this.booking_id = booking_id;
    }

    public String getcar_name() {
        return car_name;
    }

    public void setcar_name(String car_name) {
        this.car_name = car_name;
    }

    public String getcar_image() {
        return car_image;
    }

    public void setcar_image(String car_image) {
        this.car_image = car_image;
    }

    public String getcar_no_plate() {
        return car_no_plate;
    }

    public void setcar_no_plate(String car_no_plate) {
        this.car_no_plate = car_no_plate;
    }

    public String getkms() {
        return kms;
    }

    public void setkms(String kms) {
        this.kms = kms;
    }

    public String getstart_date() {
        return start_date;
    }

    public void setstart_date(String start_date) {
        this.start_date = start_date;
    }

    public String getstart_time() {
        return start_time;
    }

    public void setstart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getend_date() {
        return end_date;
    }

    public void setend_date(String end_date) {
        this.end_date = end_date;
    }

    public String getend_time() {
        return end_time;
    }

    public void setend_time(String end_time) {
        this.end_time = end_time;
    }

    public String getphone_no1() {
        return phone_no1;
    }

    public void setphone_no1(String phone_no1) {
        this.phone_no1 = phone_no1;
    }

    public String getstatus() {
        return status;
    }

    public void setstatus(String status) {
        this.status = status;
    }

    public String gettrip_fare_price() {
        return trip_fare_price;
    }

    public void settrip_fare_price(String trip_fare_price) {
        this.trip_fare_price = trip_fare_price;
    }
}
