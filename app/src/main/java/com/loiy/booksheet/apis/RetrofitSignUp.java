package com.loiy.booksheet.apis;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitSignUp {

    //declaring an instance of RetrofitSignUp.
    private static RetrofitSignUp instance = null;

    //declaring an instance of ApiServices.
    private ApiServices myAPIService;

    //Zero-parameter constructor to initialize the ApiServices instance.
    private RetrofitSignUp() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(ApiUrl.SIGN_UP)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        myAPIService = retrofit.create(ApiServices.class);

    }

    //getInstance to provide a singleton pattern(that's why we define the constructor as private).
    public static synchronized RetrofitSignUp getInstance() {
        if (instance == null) {
            instance = new RetrofitSignUp();
        }
        return instance;
    }

    //getMyApi method to get the  ApiServices instance that we initialized in the constructor.
    public ApiServices getMyApi() {
        return myAPIService;
    }

}//end of RetrofitSignUp class.
