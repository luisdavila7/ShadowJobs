package com.example.shadowjobs.model;

public class restoModel {

    String name, address, phone, email, password;

    public restoModel(String name, String email, String password, String address, String phone){

        this.name = name;
        this.address = address;
        this.password = password;
        this.email = email;
        this.phone = phone;

    }

    restoModel(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
