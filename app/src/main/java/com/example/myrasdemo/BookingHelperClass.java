package com.example.myrasdemo;

public class BookingHelperClass {
    String booking_id,car_name,car_image,car_no_plate,kms,start_date,start_time,end_date,end_time,phone_no1,status,trip_fare_price,utype;

    public BookingHelperClass(String booking_id, String car_name, String car_image, String car_no_plate, String kms, String start_date, String start_time, String end_date, String end_time, String phone_no1, String status, String trip_fare_price, String utype) {
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
        this.utype = utype;
    }

    public BookingHelperClass() {
    }

    public String getBooking_id() {
        return booking_id;
    }

    public void setBooking_id(String booking_id) {
        this.booking_id = booking_id;
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

    public String getKms() {
        return kms;
    }

    public void setKms(String kms) {
        this.kms = kms;
    }

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public String getPhone_no1() {
        return phone_no1;
    }

    public void setPhone_no1(String phone_no1) {
        this.phone_no1 = phone_no1;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTrip_fare_price() {
        return trip_fare_price;
    }

    public void setTrip_fare_price(String trip_fare_price) {
        this.trip_fare_price = trip_fare_price;
    }

    public String getUtype() {
        return utype;
    }

    public void setUtype(String utype) {
        this.utype = utype;
    }
}
