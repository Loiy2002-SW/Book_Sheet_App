package com.loiy.booksheet.apis;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitGetProduct_fav {

    //declaring an instance of RetrofitGetProduct_fav.
    private static RetrofitGetProduct_fav instance = null;

    //declaring an instance of ApiServices.
    private ApiServices myAPIServices;

    //Zero-parameter constructor to initialize the ApiServices instance.
    private RetrofitGetProduct_fav() {

        Retrofit retrofit = new Retrofit.Builder().baseUrl(ApiUrl.GET_FAV)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        myAPIServices = retrofit.create(ApiServices.class);

    }

    //getInstance to provide a singleton pattern(that's why we define the constructor as private).
    public static synchronized RetrofitGetProduct_fav getInstance() {
        if (instance == null) {
            instance = new RetrofitGetProduct_fav();
        }
        return instance;
    }

    //getMyApi method to get the  ApiServices instance that we initialized in the constructor.
    public ApiServices getMyApi() {
        return myAPIServices;
    }



}//end of RetrofitGetProduct_fav class.
