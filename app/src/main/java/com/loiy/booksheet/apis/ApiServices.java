package com.loiy.booksheet.apis;

import com.loiy.booksheet.models.Product;
import com.loiy.booksheet.models.Result;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Url;

public interface ApiServices {

    // get all books info
    @GET("getProduct.php")
    Call<List<Product>> getProduct();

    // get self-development books info
    @GET("getSelfBooks.php")
    Call<List<Product>> getProduct_self();

    // get technology books info
    @GET("getTechBooks.php")
    Call<List<Product>> getProduct_tech();


    // get novels info
    @GET("getNovelBooks.php")
    Call<List<Product>> getProduct_novel();



    // get favorite books (if there were) info
    @FormUrlEncoded
    @POST("getFavorite.php")
    Call<List<Product>> getProduct_fav(
            @Field("userId") int userId
    );

    // add book to favorite books (if not exist in DB)
    @FormUrlEncoded
    @POST("addToFavorite.php")
    Call<Result> addProduct_fav(
            @Field("userId") int userId,
            @Field("bookName") String bookName
    );

    // delete book from favorite books (if exist in DB)
    @FormUrlEncoded
    @POST("deleteFromFavorite.php")
    Call<Result> deleteProduct_fav(
            @Field("userId") int userId,
            @Field("bookName") String bookName
    );

    // check if the book is favorite to this user
    @FormUrlEncoded
    @POST("checkIfFavorite.php")
    Call<Result> checkProduct_fav(
            @Field("userId") int userId,
            @Field("bookName") String bookName
    );



    // send user info and check them then receive (user info, message and error[false or true])
    @FormUrlEncoded
    @POST("logIn.php")
    Call<Result> logInChecker(
            @Field("email") String email,
            @Field("password") String password
    );

    // send user info and check(if exist) then insert the info to the database
    @FormUrlEncoded
    @POST("signIn.php")
    Call<Result> signInChecker(
            @Field("name") String name,
            @Field("email") String email,
            @Field("password") String password,
            @Field("phone") String phone
    );

    // send user info(email, phone and pass) and check(if exist) then update the pass to the new one
    @FormUrlEncoded
    @POST("forgetPassword.php")
    Call<Result> forgetPasswordChecker(
            @Field("email") String email,
            @Field("phone") String phone,
            @Field("password") String password
    );

    // delete user info
    @FormUrlEncoded
    @POST("deleteUser.php")
    Call<Result> deleteUser(
            @Field("id") int id
    );

    // update user info
    @FormUrlEncoded
    @POST("updateUser.php")
    Call<Result> updateUser(
            @Field("id") int id,
            @Field("name") String name,
            @Field("email") String email,
            @Field("password") String password,
            @Field("phone") String phone
    );


    @GET
    Call<ResponseBody> downloadFileWithDynamicUrlSync(@Url String fileUrl);


}
