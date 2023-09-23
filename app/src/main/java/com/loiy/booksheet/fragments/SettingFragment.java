package com.loiy.booksheet.fragments;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.loiy.booksheet.R;
import com.loiy.booksheet.SharedPref.SharedPrefManager;
import com.loiy.booksheet.apis.RetrofitDelete;
import com.loiy.booksheet.models.Result;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SettingFragment extends Fragment {

    //declaring the deleteAccount button.
    Button deleteAccount_btn;

    //onCreate method used to link the java file with the XML file.
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_setting_fragment,null);
    }


    //onViewCreated method used to make actions and changes to the views in the XML file associated with this java file.
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        //initializing the deleteAccount button.
        deleteAccount_btn = getView().findViewById(R.id.deleteAccount_btn);


        // make call to the database and delete the user account then exit the home screen and open the login screen using Logout method.
        deleteAccount_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Call<Result> deleteCall = RetrofitDelete.getInstance().getMyApi().deleteUser(SharedPrefManager.getInstance(getContext()).getUsers().getId());
                deleteCall.enqueue(new Callback<Result>() {
                    @Override
                    public void onResponse(Call<Result> call, Response<Result> response) {

                       if(response.body().getError() == false){

                             getActivity().finish();
                            SharedPrefManager.getInstance(getContext()).Logout();

                        }else if (response.body().getError() == true){

                            Toast.makeText(getContext(),"Something went wrong ",Toast.LENGTH_LONG).show();

                        }

                    }

                    @Override
                    public void onFailure(Call<Result> call, Throwable t) {

                        Toast.makeText(getContext(),"Something went wrong ",Toast.LENGTH_LONG).show();
                    }
                });


            }
        });
    }
}