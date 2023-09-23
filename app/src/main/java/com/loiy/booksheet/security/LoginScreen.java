package com.loiy.booksheet.security;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;
import com.loiy.booksheet.HomeScreen;
import com.loiy.booksheet.R;
import com.loiy.booksheet.SharedPref.SharedPrefManager;
import com.loiy.booksheet.apis.RetrofitLogIn;
import com.loiy.booksheet.models.Result;
import com.loiy.booksheet.models.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginScreen extends AppCompatActivity {

    //declaring views
    TextInputLayout emailAddress_textInputLayout, password_textInputLayout;
    MaterialButton forgetPass_button, login_button, goToSignUp_button;

    //an instance of CheckInputs to make validations
    CheckInputs checker = CheckInputs.getInstance();

    //declaring variables.
    String email;
    String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_screen);

        //initializing views
        emailAddress_textInputLayout = findViewById(R.id.emailAddress_textInputLayout);
        password_textInputLayout = findViewById(R.id.password_textInputLayout);
        login_button = findViewById(R.id.login_button);
        forgetPass_button = findViewById(R.id.forgetPass_button);
        goToSignUp_button = findViewById(R.id.goToSignUp_button);

        //set on click action
        login_button.setOnClickListener(this:: onButtonClicked);
        forgetPass_button.setOnClickListener(this:: onButtonClicked);
        goToSignUp_button.setOnClickListener(this:: onButtonClicked);

    }

    //if a button is clicked in login screen
    private void onButtonClicked(View v) {

        //storing the inputs in string variables to reuse them
        email = emailAddress_textInputLayout.getEditText().getText().toString();
        password = password_textInputLayout.getEditText().getText().toString();

        //switch to know which button is clicked
        switch (v.getId()){

            case R.id.login_button:
                //if the clicked button was login button this code will be executed
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

                                //send the username and password to the database to check the account existence
                                Call<Result> loginCall = RetrofitLogIn.getInstance().getMyApi().logInChecker(email, password);

                                loginCall.enqueue(new Callback<Result>() {
                                    @Override
                                    public void onResponse(Call<Result> call, Response<Result> response) {


                                            if(!response.body().getError()){

                                                //the entered data are ture so now HomeScreen will be opened
                                                Intent i = new Intent(LoginScreen.this, HomeScreen.class);

                                                startActivity(i);

                                                Toast.makeText(getApplicationContext(),getResources().getString(R.string.welcome_toast)+response.body().getUser().getName(),Toast.LENGTH_LONG).show();


                                                //store the user data in an object of User
                                                 User user = new User((int)response.body().getUser().getId(),response.body().getUser().getName(),response.body().getUser().getEmail(),response.body().getUser().getPassword(),(String)response.body().getUser().getPhone());

                                                //store the User object using SharedPreferences so the next time the user will directly access HomeScreen
                                                 SharedPrefManager.getInstance(getApplicationContext()).userLogin(user);

                                                //finish the LoginScreen activity
                                                finish();

                                            }else {
                                                //the entered data are false so this toast will tell the user that
                                                Toast.makeText(getApplicationContext(), getResources().getString(R.string.invalidEmailPass_toast), Toast.LENGTH_LONG).show();
                                            }


                                    }

                                    @Override
                                    public void onFailure(Call<Result> call, Throwable t) {

                                        //if a connection problem occurs then this toast will show up
                                        Toast.makeText(getApplicationContext(),t.getMessage(), Toast.LENGTH_LONG).show();


                                        //if a connection problem occurs then this toast will show up
                                        Toast.makeText(getApplicationContext(),getResources().getString(R.string.onFailure_toast), Toast.LENGTH_LONG).show();



                                    }
                                });

                            }

                        }
                    }
                }


                break;

            case R.id.forgetPass_button:

                //clear the fields so when the user back to Login activity he will enter his new data
                emailAddress_textInputLayout.getEditText().setText("");
                password_textInputLayout.getEditText().setText("");

                //clear errors(if there was) for better user experience
                emailAddress_textInputLayout.setErrorEnabled(false);
                password_textInputLayout.setErrorEnabled(false);

                // Moving to ForgetPassword activity so the user can change his password
                Intent goToForgetPasswordActivity = new Intent(getApplicationContext(),ForgetPassword.class);
                startActivity(goToForgetPasswordActivity);


                break;

            case R.id.goToSignUp_button:

                //clear the fields so when the user back to Login activity he will enter his new data
                emailAddress_textInputLayout.getEditText().setText("");
                password_textInputLayout.getEditText().setText("");

                //clear errors(if there was) for better user experience
                emailAddress_textInputLayout.setErrorEnabled(false);
                password_textInputLayout.setErrorEnabled(false);

                // Moving to SignUp activity so the user can create a new account
                Intent goToSignUpActivity = new Intent(getApplicationContext(),SignUpScreen.class);
                startActivity(goToSignUpActivity);


        }
    }
}