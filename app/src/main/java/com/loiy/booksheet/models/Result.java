package com.loiy.booksheet.models;

import com.google.gson.annotations.SerializedName;

public class Result {

    //declaring the variables that will store the data from the php file using @SerializedName http method.
    @SerializedName("error")
    boolean error;

    @SerializedName("message")
    String message;

    @SerializedName("user")
    User user;


    //3-parameter constructor to store the data in the declared variables (initialization process).
    public Result(Boolean error, String message, User user) {
        this.error = error;
        this.message = message;
        this.user = user;
    }



    //2-parameter constructor to store the data in the declared variables (initialization process).
    public Result(Boolean error, String message){
        this.error = error;
        this.message = message;

    }

    // getters for all data to get them from different fragments.
    public Boolean getError() {
        return error;
    }

    public String getMessage() {
        return message;
    }

    public User getUser() {
        return user;
    }

}
