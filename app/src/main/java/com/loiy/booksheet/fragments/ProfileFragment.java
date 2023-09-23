package com.loiy.booksheet.fragments;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.android.material.textfield.TextInputLayout;
import com.loiy.booksheet.R;
import com.loiy.booksheet.SharedPref.SharedPrefManager;
import com.loiy.booksheet.apis.RetrofitSignUp;
import com.loiy.booksheet.apis.RetrofitUpdate;
import com.loiy.booksheet.models.Result;
import com.loiy.booksheet.security.CheckInputs;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileFragment extends Fragment {


    //declaring variables.
    CircleImageView userImage;
    TextInputLayout TextInputUsername, TextInputEmail,TextInputPassword,TextInputPhone;
    Button update_btn;
    String name, email, password, phone;
    int id;


    //an instance of CheckInputs to make validations
    CheckInputs checker = CheckInputs.getInstance();

    //onCreate method used to link the java file with the XML file.
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_profile_fragment,null);
    }


    //onViewCreated method used to make actions and changes to the views in the XML file associated with this java file.
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        //initializing variables.
        userImage = getView().findViewById(R.id.userImage);
        TextInputUsername = getView().findViewById(R.id.TextInputUsername);
        TextInputEmail = getView().findViewById(R.id.TextInputEmail);
        TextInputPassword = getView().findViewById(R.id.TextInputPassword);
        TextInputPhone = getView().findViewById(R.id.TextInputPhone);
        update_btn = getView().findViewById(R.id.update_btn);


        //fill in the editTexts by the main user info using SharedPreferences
        TextInputUsername.getEditText().setText(SharedPrefManager.getInstance(getContext()).getUsers().getName());
        TextInputEmail.getEditText().setText(SharedPrefManager.getInstance(getContext()).getUsers().getEmail());
        TextInputPassword.getEditText().setText(SharedPrefManager.getInstance(getContext()).getUsers().getPassword());
        TextInputPhone.getEditText().setText(SharedPrefManager.getInstance(getContext()).getUsers().getPhone());


        //set image profile
        userImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ImagePicker.with(ProfileFragment.this)
                        .compress(1024)            //Final image size will be less than 1 MB(Optional)
                        .maxResultSize(1080, 1080)    //Final image resolution will be less than 1080 x 1080(Optional)
                        .start();

            }
        });

        //when the update button is clicked these validation will be applied then update the database if the inputs are correct
        update_btn.setOnClickListener(new View.OnClickListener() {
                                          @Override
                                          public void onClick(View v) {

                                              //storing the inputs in string variables to reuse them
                                              id = SharedPrefManager.getInstance(getContext()).getUsers().getId();
                                              name = TextInputUsername.getEditText().getText().toString();
                                              email = TextInputEmail.getEditText().getText().toString();
                                              password = TextInputPassword.getEditText().getText().toString();
                                              phone = TextInputPhone.getEditText().getText().toString();

                                              //check if the email address field is empty and throw an error if so
                                              if (checker.checkEmpty(name)) {
                                                  TextInputUsername.setError(getResources().getString(R.string.emptyName_errorMessage));
                                              } else {
                                                  TextInputUsername.setErrorEnabled(false);

                                                  //check if the email address field is empty and throw an error if so
                                                  if (checker.checkEmpty(email)) {
                                                      TextInputEmail.setError(getResources().getString(R.string.emptyEmail_errorMessage));
                                                  } else {
                                                      TextInputEmail.setErrorEnabled(false);

                                                      //check if the password field is empty and throw an error if so
                                                      if (checker.checkEmpty(password)) {
                                                          TextInputPassword.setError(getResources().getString(R.string.emptyPass_errorMessage));
                                                      } else {
                                                          TextInputPassword.setErrorEnabled(false);

                                                          //check if the phone number field is empty and throw an error if so
                                                          if (checker.checkEmpty(phone)) {
                                                              TextInputPhone.setError(getResources().getString(R.string.emptyPhone_errorMessage));
                                                          } else {
                                                              TextInputPhone.setErrorEnabled(false);

                                                              //check the email address format and throw an error if it's wrong
                                                              if (!checker.checkEmailFormat(email)) {
                                                                  TextInputEmail.setError(getResources().getString(R.string.emailFormat_errorMessage));
                                                              } else {
                                                                  TextInputEmail.setErrorEnabled(false);

                                                                  //check the password length and throw an error if it's not 8
                                                                  if (!checker.checkPassLength(password)) {
                                                                      TextInputPassword.setError(getResources().getString(R.string.passLength_errorMessage));
                                                                  } else {
                                                                      TextInputPassword.setErrorEnabled(false);

                                                                      //check the phone number format and throw an error if it's wrong
                                                                      if (!checker.checkPhoneFormat(phone)) {
                                                                          TextInputPhone.setError(getResources().getString(R.string.phoneFormat_errorMessage));
                                                                      } else {
                                                                          TextInputPhone.setErrorEnabled(false);

                                                                          //check the phone number length and throw an error if it's not 9
                                                                          if (!checker.checkPhoneLength(phone)) {
                                                                              TextInputPhone.setError(getResources().getString(R.string.phoneFormat_errorMessage));
                                                                          } else {
                                                                              TextInputPhone.setErrorEnabled(false);


                                                                              //send the inputs to the database to update the user info
                                                                              Call<Result> updateCall = RetrofitUpdate.getInstance().getMyApi().updateUser(id,name, email, password, phone);

                                                                              updateCall.enqueue(new Callback<Result>() {
                                                                                  @Override
                                                                                  public void onResponse(Call<Result> call, Response<Result> response) {

                                                                                      if (!response.body().getError()) {

                                                                                          Toast.makeText(getContext(), response.body().getMessage(), Toast.LENGTH_LONG).show();



                                                                                      } else {

                                                                                          //something goes wrong - User Info not updated correctly!
                                                                                          Toast.makeText(getContext(), response.body().getMessage().toString(), Toast.LENGTH_LONG).show();
                                                                                      }

                                                                                  }

                                                                                  @Override
                                                                                  public void onFailure(Call<Result> call, Throwable t) {

                                                                                      //if a connection problem occurs then this toast will show up
                                                                                      Toast.makeText(getContext(), getResources().getString(R.string.onFailure_toast), Toast.LENGTH_LONG).show();

                                                                                  }
                                                                              });

                                                                          }
                                                                      }
                                                                  }
                                                              }
                                                          }
                                                      }
                                                  }
                                              }
                                          }
                                      });


    }
    @Override//for set imageProfile
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Uri uri = data.getData();
        userImage.setImageURI(uri);
    }

}