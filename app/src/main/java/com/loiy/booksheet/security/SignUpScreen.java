package com.loiy.booksheet.security;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;
import com.loiy.booksheet.R;
import com.loiy.booksheet.apis.RetrofitSignUp;
import com.loiy.booksheet.models.Result;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpScreen extends AppCompatActivity {

    //declaring views
    TextInputLayout userName_textInputLayout, emailAddress_textInputLayout,
                    password_textInputLayout, reEnterPassword_textInputLayout, phoneNumber_textInputLayout;
    MaterialButton Create_button;

    //an instance of CheckInputs to make validations
    CheckInputs checker = CheckInputs.getInstance();

    //declaring variables.
    String name, email, password, re_password, phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up_screen);

        //initializing views
        userName_textInputLayout = findViewById(R.id.userName_textInputLayout);
        emailAddress_textInputLayout = findViewById(R.id.emailAddress_textInputLayout);
        password_textInputLayout = findViewById(R.id.password_textInputLayout);
        reEnterPassword_textInputLayout = findViewById(R.id.reEnterPassword_textInputLayout);
        phoneNumber_textInputLayout = findViewById(R.id.phoneNumber_textInputLayout);

        Create_button = findViewById(R.id.Create_button);

         //set on click action
        Create_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                //storing the inputs in string variables to reuse them
                name = userName_textInputLayout.getEditText().getText().toString();
                email = emailAddress_textInputLayout.getEditText().getText().toString();
                password = password_textInputLayout.getEditText().getText().toString();
                re_password = reEnterPassword_textInputLayout.getEditText().getText().toString();
                phone = phoneNumber_textInputLayout.getEditText().getText().toString();

                //check if the email address field is empty and throw an error if so
                if(checker.checkEmpty(name)){
                    userName_textInputLayout.setError(getResources().getString(R.string.emptyName_errorMessage));
                }else {
                    userName_textInputLayout.setErrorEnabled(false);

                     //check if the email address field is empty and throw an error if so
                     if(checker.checkEmpty(email)){
                         emailAddress_textInputLayout.setError(getResources().getString(R.string.emptyEmail_errorMessage));
                     }else {
                         emailAddress_textInputLayout.setErrorEnabled(false);

                         //check if the password field is empty and throw an error if so
                         if (checker.checkEmpty(password)) {
                             password_textInputLayout.setError(getResources().getString(R.string.emptyPass_errorMessage));
                         } else {
                             password_textInputLayout.setErrorEnabled(false);

                             //check if the re-enter password field is empty and throw an error if so
                             if (checker.checkEmpty(re_password)) {
                                 reEnterPassword_textInputLayout.setError(getResources().getString(R.string.emptyPass_errorMessage));
                             } else {
                                 reEnterPassword_textInputLayout.setErrorEnabled(false);

                                 //check if the phone number field is empty and throw an error if so
                                 if (checker.checkEmpty(phone)) {
                                     phoneNumber_textInputLayout.setError(getResources().getString(R.string.emptyPhone_errorMessage));
                                 } else {
                                     phoneNumber_textInputLayout.setErrorEnabled(false);

                                     //check the email address format and throw an error if it's wrong
                                     if (!checker.checkEmailFormat(email)) {
                                         emailAddress_textInputLayout.setError(getResources().getString(R.string.emailFormat_errorMessage));
                                     } else {
                                         emailAddress_textInputLayout.setErrorEnabled(false);

                                         //check the password length and throw an error if it's not 8
                                         if (!checker.checkPassLength(password)) {
                                             password_textInputLayout.setError(getResources().getString(R.string.passLength_errorMessage));
                                         } else {
                                             password_textInputLayout.setErrorEnabled(false);

                                             //check the password and re-enter password are matched and throw an error if not
                                             if (!checker.checkPassMatchesReEnteredPass(password, re_password)) {
                                                 reEnterPassword_textInputLayout.setError(getResources().getString(R.string.passNotMatch_errorMessage));
                                             } else {
                                                 reEnterPassword_textInputLayout.setErrorEnabled(false);

                                                 //check the phone number format and throw an error if it's wrong
                                                 if (!checker.checkPhoneFormat(phone)) {
                                                     phoneNumber_textInputLayout.setError(getResources().getString(R.string.phoneFormat_errorMessage));
                                                 } else {
                                                     phoneNumber_textInputLayout.setErrorEnabled(false);

                                                     //check the phone number length and throw an error if it's not 9
                                                     if (!checker.checkPhoneLength(phone)) {
                                                         phoneNumber_textInputLayout.setError(getResources().getString(R.string.phoneFormat_errorMessage));
                                                     } else {
                                                         phoneNumber_textInputLayout.setErrorEnabled(false);


                                                         //send the inputs to the database to add the account
                                                         Call<Result> signUpCall = RetrofitSignUp.getInstance().getMyApi().signInChecker(name, email, password, phone);

                                                         signUpCall.enqueue(new Callback<Result>() {
                                                             @Override
                                                             public void onResponse(Call<Result> call, Response<Result> response) {

                                                                 if(!response.body().getError()){

                                                                     Toast.makeText(getApplicationContext(),response.body().getMessage(),Toast.LENGTH_LONG).show();

                                                                     // Moving-back to Login activity so the user can enter his new account
                                                                     finish();

                                                                 }else {
                                                                     //this email already exists
                                                                     Toast.makeText(getApplicationContext(), response.body().getMessage().toString(), Toast.LENGTH_LONG).show();
                                                                 }

                                                             }

                                                             @Override
                                                             public void onFailure(Call<Result> call, Throwable t) {

                                                                 //if a connection problem occurs then this toast will show up
                                                                 Toast.makeText(getApplicationContext(),getResources().getString(R.string.onFailure_toast), Toast.LENGTH_LONG).show();

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
                }


            }
        });
    }
}