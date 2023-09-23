package com.loiy.booksheet.fragments;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.loiy.booksheet.MainActivity;
import com.loiy.booksheet.R;
import com.loiy.booksheet.adapters.BookAdapter;
import com.loiy.booksheet.apis.RetrofitGetProduct;
import com.loiy.booksheet.apis.RetrofitGetProduct_novel;
import com.loiy.booksheet.apis.RetrofitGetProduct_self;
import com.loiy.booksheet.apis.RetrofitGetProduct_tech;
import com.loiy.booksheet.models.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {

    //declaring variables.
    ListView listView;
    String filter;

    //one-parameter constructor the get the filter(which type of books the user want).
    public HomeFragment(String filter){

        this.filter = filter;

    }

    //onCreate method used to link the java file with the XML file.
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_home_fragment,null);
    }

    //onViewCreated method used to make actions and changes to the views in the XML file associated with this java file.
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        //initializing listView.
        listView = getView().findViewById(R.id.listView);



        //if the filter is "allBooks" then make call to the database and get all books
        if(filter.equals("allBooks")) {

            Call<List<Product>> getBookCall = RetrofitGetProduct.getInstance().getMyApi().getProduct();
            getBookCall.enqueue(new Callback<List<Product>>() {
                @Override
                public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {

                    List<Product> productList = response.body();
                    BookAdapter adapter = new BookAdapter(getContext(), productList);
                    listView.setAdapter(adapter);

                }

                @Override
                public void onFailure(Call<List<Product>> call, Throwable t) {

                    Toast.makeText(getContext(), "Something went wrong", Toast.LENGTH_SHORT).show();

                }
            });

            //if the filter is "Technology" then make call to the database and get only "Technology" books
        }else if(filter.equals("Technology")) {
            Call<List<Product>> getBookCall = RetrofitGetProduct_tech.getInstance().getMyApi().getProduct_tech();
            getBookCall.enqueue(new Callback<List<Product>>() {
                @Override
                public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {

                    List<Product> productList = response.body();
                    BookAdapter adapter = new BookAdapter(getContext(), productList);
                    listView.setAdapter(adapter);

                }

                @Override
                public void onFailure(Call<List<Product>> call, Throwable t) {

                    Toast.makeText(getContext(), "Something went wrong", Toast.LENGTH_SHORT).show();

                }
            });

            //if the filter is "novel" then make call to the database and get only "novel" books
        }else if(filter.equals("novel")) {

            Call<List<Product>> getBookCall = RetrofitGetProduct_novel.getInstance().getMyApi().getProduct_novel();
            getBookCall.enqueue(new Callback<List<Product>>() {
                @Override
                public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {

                    List<Product> productList = response.body();
                    BookAdapter adapter = new BookAdapter(getContext(), productList);
                    listView.setAdapter(adapter);

                }

                @Override
                public void onFailure(Call<List<Product>> call, Throwable t) {

                    Toast.makeText(getContext(), "Something went wrong", Toast.LENGTH_SHORT).show();

                }
            });

            //if the filter is "self development" then make call to the database and get only "self development" books
        }else if(filter.equals("self development")) {

            Call<List<Product>> getBookCall = RetrofitGetProduct_self.getInstance().getMyApi().getProduct_self();
            getBookCall.enqueue(new Callback<List<Product>>() {
                @Override
                public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {

                    List<Product> productList = response.body();
                    BookAdapter adapter = new BookAdapter(getContext(), productList);
                    listView.setAdapter(adapter);

                }

                @Override
                public void onFailure(Call<List<Product>> call, Throwable t) {

                    Toast.makeText(getContext(), "Something went wrong", Toast.LENGTH_SHORT).show();

                }
            });

        }


    }
}