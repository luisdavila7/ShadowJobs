package com.example.shadowjobs.model;

public class restoModel {

    String id, name, address, phone, email, password;

    public restoModel(String id, String name, String email, String password, String address, String phone) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.password = password;
        this.email = email;
        this.phone = phone;

    }

    restoModel() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}