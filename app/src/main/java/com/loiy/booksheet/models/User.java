package com.loiy.booksheet.models;

public class User {

    //declaring the variables.
    int id;
    String name, email, password, phone;


    //5-parameter constructor to store the data in the declared variables (initialization process).
    public User(int id, String name, String email, String password, String phone){

        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.phone = phone;

    }

    // getters for all data to get them from different fragments.
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getPhone() {
        return phone;
    }
}
