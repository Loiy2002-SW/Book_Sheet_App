package com.loiy.booksheet.apis;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitCheckProduct_fav {


    //declaring an instance of RetrofitCheckProduct_fav.
    private static RetrofitCheckProduct_fav instance = null;

    //declaring an instance of ApiServices.
    private ApiServices myAPIServices;

    //Zero-parameter constructor to initialize the ApiServices instance.
    private RetrofitCheckProduct_fav() {

        Retrofit retrofit = new Retrofit.Builder().baseUrl(ApiUrl.CHECK_FAV)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        myAPIServices = retrofit.create(ApiServices.class);

    }

    //getInstance to provide a singleton pattern(that's why we define the constructor as private).
    public static synchronized RetrofitCheckProduct_fav getInstance() {
        if (instance == null) {
            instance = new RetrofitCheckProduct_fav();
        }
        return instance;
    }

    //getMyApi method to get the  ApiServices instance that we initialized in the constructor.
    public ApiServices getMyApi() {
        return myAPIServices;
    }


}//end of RetrofitCheckProduct_fav class.
