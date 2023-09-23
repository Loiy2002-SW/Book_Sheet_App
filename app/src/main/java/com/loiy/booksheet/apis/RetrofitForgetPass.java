package com.loiy.booksheet.apis;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitForgetPass {

    //declaring an instance of RetrofitForgetPass.
    private static RetrofitForgetPass instance = null;

    //declaring an instance of ApiServices.
    private ApiServices myAPIService;

    //Zero-parameter constructor to initialize the ApiServices instance.
    private RetrofitForgetPass() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(ApiUrl.FORGET_PASS)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        myAPIService = retrofit.create(ApiServices.class);

    }

    //getInstance to provide a singleton pattern(that's why we define the constructor as private).
    public static synchronized RetrofitForgetPass getInstance() {
        if (instance == null) {
            instance = new RetrofitForgetPass();
        }
        return instance;
    }

    //getMyApi method to get the  ApiServices instance that we initialized in the constructor.
    public ApiServices getMyApi() {
        return myAPIService;
    }

}//end of RetrofitForgetPass class.
