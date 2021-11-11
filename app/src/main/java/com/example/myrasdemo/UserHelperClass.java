package com.example.myrasdemo;

public class UserHelperClass {
    String first_name, last_name, email, phoneno1, password, licence_no;

    public UserHelperClass(String first_name, String last_name, String email, String phoneno1, String password, String licence_no) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.phoneno1 = phoneno1;
        this.password = password;
        this.licence_no = licence_no;
    }

    public UserHelperClass() {
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneno1() {
        return phoneno1;
    }

    public void setPhoneno1(String phoneno1) {
        this.phoneno1 = phoneno1;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLicence_no() {
        return licence_no;
    }

    public void setLicence_no(String licence_no) {
        this.licence_no = licence_no;
    }
}
