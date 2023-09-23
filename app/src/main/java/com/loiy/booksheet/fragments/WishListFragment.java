package com.loiy.booksheet.fragments;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.loiy.booksheet.R;
import com.loiy.booksheet.SharedPref.SharedPrefManager;
import com.loiy.booksheet.adapters.BookAdapter;
import com.loiy.booksheet.adapters.FavoriteBookAdapter;
import com.loiy.booksheet.apis.RetrofitGetProduct;
import com.loiy.booksheet.apis.RetrofitGetProduct_fav;
import com.loiy.booksheet.models.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WishListFragment extends Fragment {

    //declaring listView.
    ListView listView;

    //onCreate method used to link the java file with the XML file.
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_wish_list_fragment,null);
    }


    //onViewCreated method used to make actions and changes to the views in the XML file associated with this java file.
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        //initializing listView.
        listView = getView().findViewById(R.id.listView);

        //make call to the database to get all favorite books (if there were)
        Call<List<Product>> getFavoriteBookCall = RetrofitGetProduct_fav.getInstance().getMyApi().getProduct_fav(SharedPrefManager.getInstance(getContext()).getUsers().getId());
        getFavoriteBookCall.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {

                List<Product> productList = response.body();
                FavoriteBookAdapter adapter = new FavoriteBookAdapter(getContext(), productList);
                listView.setAdapter(adapter);

            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {

                Toast.makeText(getContext(), "Something went wrong", Toast.LENGTH_SHORT).show();

            }
        });

    }



}