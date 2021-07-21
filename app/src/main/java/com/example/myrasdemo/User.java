package com.example.myrasdemo;

public class User {
    String phone_no1, phone_no2, last_name, first_name, password, address, area, city, state,pincode, license_no;

    public User() {
    }

    public User(String phone_no1, String phone_no2, String last_name, String first_name, String password, String address, String area, String city, String state, String pincode, String license_no) {
        this.phone_no1 = phone_no1;
        this.phone_no2 = phone_no2;
        this.last_name = last_name;
        this.first_name = first_name;
        this.password = password;
        this.address = address;
        this.area = area;
        this.city = city;
        this.state = state;
        this.pincode = pincode;
        this.license_no = license_no;
    }

    public String getPhone_no1() {
        return phone_no1;
    }

    public void setPhone_no1(String phone_no1) {
        this.phone_no1 = phone_no1;
    }

    public String getPhone_no2() {
        return phone_no2;
    }

    public void setPhone_no2(String phone_no2) {
        this.phone_no2 = phone_no2;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public String getLicense_no() {
        return license_no;
    }

    public void setLicense_no(String license_no) {
        this.license_no = license_no;
    }
}
