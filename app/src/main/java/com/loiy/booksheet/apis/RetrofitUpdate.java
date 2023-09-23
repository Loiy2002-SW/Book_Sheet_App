package com.loiy.booksheet.apis;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitUpdate {

    //declaring an instance of RetrofitUpdate.
    private static RetrofitUpdate instance = null;

    //declaring an instance of ApiServices.
    private ApiServices myAPIService;

    //Zero-parameter constructor to initialize the ApiServices instance.
    private RetrofitUpdate() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(ApiUrl.UPDATE_USER)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        myAPIService = retrofit.create(ApiServices.class);

    }

    //getInstance to provide a singleton pattern(that's why we define the constructor as private).
    public static synchronized RetrofitUpdate getInstance() {
        if (instance == null) {
            instance = new RetrofitUpdate();
        }
        return instance;
    }

    //getMyApi method to get the  ApiServices instance that we initialized in the constructor.
    public ApiServices getMyApi() {
        return myAPIService;
    }

}//end of RetrofitUpdate class.
