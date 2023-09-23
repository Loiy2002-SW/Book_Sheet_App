package com.loiy.booksheet.apis;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitDeleteProduct_fav {


    //declaring an instance of RetrofitDeleteProduct_fav.
    private static RetrofitDeleteProduct_fav instance = null;

    //declaring an instance of ApiServices.
    private ApiServices myAPIServices;

    //Zero-parameter constructor to initialize the ApiServices instance.
    private RetrofitDeleteProduct_fav() {

        Retrofit retrofit = new Retrofit.Builder().baseUrl(ApiUrl.DEL_FAV)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        myAPIServices = retrofit.create(ApiServices.class);

    }

    //getInstance to provide a singleton pattern(that's why we define the constructor as private).
    public static synchronized RetrofitDeleteProduct_fav getInstance() {
        if (instance == null) {
            instance = new RetrofitDeleteProduct_fav();
        }
        return instance;
    }

    //getMyApi method to get the  ApiServices instance that we initialized in the constructor.
    public ApiServices getMyApi() {
        return myAPIServices;
    }


}//end of RetrofitDeleteProduct_fav class.
