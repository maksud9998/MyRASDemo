package com.example.myrasdemo;

public class Users {
    String profile_image, first_name, last_name, email, licence_no, address_proof_no, phoneno1, phoneno2;

    public Users(String profile_image, String first_name, String last_name, String email, String licence_no, String address_proof_no, String phoneno1, String phoneno2) {
        this.profile_image = profile_image;
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.licence_no = licence_no;
        this.address_proof_no = address_proof_no;
        this.phoneno1 = phoneno1;
        this.phoneno2 = phoneno2;
    }

    public Users() {
    }

    public String getProfile_image() {
        return profile_image;
    }

    public void setProfile_image(String profile_image) {
        this.profile_image = profile_image;
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

    public String getLicence_no() {
        return licence_no;
    }

    public void setLicence_no(String licence_no) {
        this.licence_no = licence_no;
    }

    public String getAddress_proof_no() {
        return address_proof_no;
    }

    public void setAddress_proof_no(String address_proof_no) {
        this.address_proof_no = address_proof_no;
    }

    public String getPhoneno1() {
        return phoneno1;
    }

    public void setPhoneno1(String phoneno1) {
        this.phoneno1 = phoneno1;
    }

    public String getPhoneno2() {
        return phoneno2;
    }

    public void setPhoneno2(String phoneno2) {
        this.phoneno2 = phoneno2;
    }
}
