package com.loiy.booksheet.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.loiy.booksheet.R;
import com.loiy.booksheet.SharedPref.SharedPrefManager;
import com.loiy.booksheet.apis.RetrofitCheckProduct_fav;
import com.loiy.booksheet.apis.RetrofitDeleteProduct_fav;
import com.loiy.booksheet.fragments.HomeFragment;
import com.loiy.booksheet.models.Product;
import com.loiy.booksheet.models.Result;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FavoriteBookAdapter extends ArrayAdapter<Product> {

    //declaring the list to store the favorite books info.
    private List<Product> productList;

    //declaring the context that we will deal with.
    private Context mCtx;



    // BookAdapter 2-parameter constructor to get the list and the context and store them globally.
    public FavoriteBookAdapter(Context context, List<Product> products){

        super(context, R.layout.list_item,products);
        this.productList = products;
        this.mCtx = context;

    }


    //getView method that will loop [position] times.
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {


        // defining the XML that we will deal with (list_item_wish).
        if(convertView == null){

            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_wish,parent,false);
        }



        //declaring and initializing the list_item_wish views.
        CardView cardView = convertView.findViewById(R.id.cardView);
        TextView bookName = convertView.findViewById(R.id.bookName);
        ImageView bookImage = convertView.findViewById(R.id.bookImage);
        LinearLayout iconsLayout = convertView.findViewById(R.id.iconsLayout);
        ImageView delete_btn = convertView.findViewById(R.id.delete_btn);
        ImageView download_btn = convertView.findViewById(R.id.download_btn);
        TextView bookName_hideLinear = convertView.findViewById(R.id.bookName_hideLinear);
        TextView AuthorName_hideLinear = convertView.findViewById(R.id.AuthorName_hideLinear);
        TextView YearOfPublish_hideLinear = convertView.findViewById(R.id.YearOfPublish_hideLinear);
        ImageView bookImage_hideLinear = convertView.findViewById(R.id.bookImage_hideLinear);
        TextView description_hideLinear = convertView.findViewById(R.id.description_hideLinear);
        LinearLayout BookDetails = convertView.findViewById(R.id.BookDetails);


        //make the BookDetails layout gone by default.
        if (BookDetails.getVisibility() == View.VISIBLE)
            BookDetails.setVisibility(View.GONE);


        //applying the books information to the views in list_item.
        bookName.setText(productList.get(position).getBookName());

        Glide.with(getContext()).load("http://10.0.2.2/BookSheet/uploads/image/"+productList.get(position).getBookImage())
                .apply(new RequestOptions()
                        .override(800,800))
                .error(R.drawable.notfound)
                .into(bookImage);

        bookName_hideLinear.setText(productList.get(position).getBookName());
        AuthorName_hideLinear.setText(productList.get(position).getAuthorName());
        YearOfPublish_hideLinear.setText(productList.get(position).getPublishYear());

        Glide.with(getContext()).load("http://10.0.2.2/BookSheet/uploads/image/"+productList.get(position).getBookImage())
                .apply(new RequestOptions()
                        .override(800,800))
                .error(R.drawable.notfound)
                .into(bookImage_hideLinear);

        description_hideLinear.setText(productList.get(position).getDescription());


        //make actions on the views depending on what the user clicks.
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(BookDetails.getVisibility() == View.GONE)
                    BookDetails.setVisibility(View.VISIBLE);


            }
        });


        BookDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                BookDetails.setVisibility(View.GONE);


            }
        });



        delete_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Call<Result> deleteFavCall = RetrofitDeleteProduct_fav.getInstance().getMyApi().deleteProduct_fav(SharedPrefManager.getInstance(getContext()).getUsers().getId(), productList.get(position).getBookName());
                deleteFavCall.enqueue(new Callback<Result>() {
                    @Override
                    public void onResponse(Call<Result> call, Response<Result> response) {


                        Toast.makeText(mCtx, "Removed from Wish list", Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onFailure(Call<Result> call, Throwable t) {

                        Toast.makeText(getContext(), "Something went wrong", Toast.LENGTH_SHORT).show();

                    }
                });


            }
        });


        download_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                download_btn.setImageResource(R.drawable.file_download_blue);


            }
        });

        return convertView;
    }//end of getView method.


}//end of BookAdapter class.
