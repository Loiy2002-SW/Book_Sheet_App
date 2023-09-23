package com.loiy.booksheet;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.loiy.booksheet.SharedPref.SharedPrefManager;
import com.loiy.booksheet.security.LoginScreen;


public class SplashScreen extends AppCompatActivity {


    Animation zoom;
    ImageView circle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.splash_screen);

        zoom = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.zoom);
        circle = findViewById(R.id.circle);

        circle.startAnimation(zoom);

        Handler handler = new Handler();

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //check if the user logged in or not using SharedPreferences.
                if(!SharedPrefManager.getInstance(getApplicationContext()).isLoggedIn()){

                    finish();
                    Intent i = new Intent(getApplicationContext(), LoginScreen.class);
                    startActivity(i);

                }else {

                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                    finish();

                }


            }
        },4000);


    }
}