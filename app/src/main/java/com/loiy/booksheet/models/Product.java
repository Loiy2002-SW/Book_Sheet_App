package com.loiy.booksheet.models;

import com.google.gson.annotations.SerializedName;

public class Product  {


    //declaring the variables that will store the data from the php file using @SerializedName http method.
    @SerializedName("bookName_App")
    String bookName;

    @SerializedName("authorName_App")
    String authorName;

    @SerializedName("bookImage_App")
    String bookImage;

    @SerializedName("publishYear_App")
    String publishYear;

    @SerializedName("description_App")
    String description;

    @SerializedName("pdf_App")
    String pdf;

    @SerializedName("type_App")
    String type;


    //7-parameter constructor to store the data in the declared variables (initialization process).
    public Product(String bookName, String authorName, String bookImage, String publishYear, String description, String pdf, String type) {

        this.bookName = bookName;
        this.authorName = authorName;
        this.bookImage = bookImage;
        this.publishYear = publishYear;
        this.description = description;
        this.pdf = pdf;
        this.type = type;
    }

    // getters for all data to get them from different fragments.
    public String getBookName() {
        return bookName;
    }

    public String getAuthorName() {
        return authorName;
    }

    public String getBookImage() {
        return bookImage;
    }

    public String getPublishYear() {
        return publishYear;
    }

    public String getDescription() {
        return description;
    }

    public String getPdf() {
        return pdf;
    }

    public String getType() {
        return type;
    }

}

