package com.loiy.booksheet;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.loiy.booksheet.SharedPref.SharedPrefManager;
import com.loiy.booksheet.fragments.AboutUsFragment;
import com.loiy.booksheet.fragments.ContactUsFragment;
import com.loiy.booksheet.fragments.HomeFragment;
import com.loiy.booksheet.fragments.LogoutFragment;
import com.loiy.booksheet.fragments.ProfileFragment;
import com.loiy.booksheet.fragments.SettingFragment;
import com.loiy.booksheet.fragments.WishListFragment;

public class HomeScreen extends AppCompatActivity {


    //declaring variables.
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;

    BottomNavigationView bottomNavigationView;

    public static TextView navUsername,navEmail,navPhone;

    FragmentTransaction fragmentTransaction;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        //initializing views
        drawerLayout = findViewById(R.id.navLayout);
        navigationView =  findViewById(R.id.nav_view);
        bottomNavigationView = findViewById(R.id.bottomNavigationView);

        toolbar = findViewById(R.id.toolbar);

        //enabling the toolbar.
        setSupportActionBar(toolbar);

        //open the home fragment by default.
            fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.container, new HomeFragment("allBooks"));
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();



            // make actions to the navigationView items (open fragment depending on the clicked item).
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){

                    case R.id.nav_home:
                        fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.container, new HomeFragment("allBooks"));
                        fragmentTransaction.addToBackStack(null);
                        fragmentTransaction.commit();
                        toolbar.setTitle(getResources().getString(R.string.homeScreen));

                        break;
                    case R.id.nav_fav:
                        fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.container, new WishListFragment());
                        fragmentTransaction.addToBackStack(null);
                        fragmentTransaction.commit();
                        toolbar.setTitle(getResources().getString(R.string.wishList));

                        break;
                    case R.id.nav_settings:
                        fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.container, new SettingFragment());
                        fragmentTransaction.addToBackStack(null);
                        fragmentTransaction.commit();
                        toolbar.setTitle(getResources().getString(R.string.setting));

                        break;

                        case R.id.nav_profile:
                        fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.container, new ProfileFragment());
                        fragmentTransaction.addToBackStack(null);
                        fragmentTransaction.commit();
                        toolbar.setTitle(getResources().getString(R.string.profile));

                        break;

                    case R.id.nav_contact_us:
                        fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.container, new ContactUsFragment());
                        fragmentTransaction.addToBackStack(null);
                        fragmentTransaction.commit();
                        toolbar.setTitle(getResources().getString(R.string.contactUs));

                        break;

                    case R.id.nav_about_us:
                        fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.container, new AboutUsFragment());
                        fragmentTransaction.addToBackStack(null);
                        fragmentTransaction.commit();
                        toolbar.setTitle(getResources().getString(R.string.aboutUs));

                        break;

                    case R.id.nav_log_out:
                        fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.container, new LogoutFragment());
                        fragmentTransaction.addToBackStack(null);
                        fragmentTransaction.commit();
                        toolbar.setTitle(getResources().getString(R.string.logout));

                        break;




                }
                return true;
            }
        });




        // make actions to the bottomNavigationView items (filter the home screen books depending on the clicked item).
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){

                    case R.id.tech:
                        fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.container, new HomeFragment("Technology"));
                        fragmentTransaction.addToBackStack(null);
                        fragmentTransaction.commit();
                        toolbar.setTitle(getResources().getString(R.string.tech_title));

                        break;

                    case R.id.self_development:

                        fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.container, new HomeFragment("self development"));
                        fragmentTransaction.addToBackStack(null);
                        fragmentTransaction.commit();
                        toolbar.setTitle(getResources().getString(R.string.self_development_title));

                        break;

                    case R.id.novel:

                        fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.container, new HomeFragment("novel"));
                        fragmentTransaction.addToBackStack(null);
                        fragmentTransaction.commit();
                        toolbar.setTitle(getResources().getString(R.string.novel_title));

                        break;

                }

                return true;
            }
        });





        //define the header of navigation view
        View headerView = navigationView.getHeaderView(0);
        navUsername =  headerView.findViewById(R.id.user_name_nav);
        navEmail =  headerView.findViewById(R.id.Email_nav);
        navPhone =  headerView.findViewById(R.id.Phone_nav);


        //invoking this method will set the user information to the navigation drawer using SharedPreferences.
        setHeaderInfo();


        //enabling the open and close processes to the drawerLayout.
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
    }





    //setHeaderInfo method will set the user information to the navigation drawer using SharedPreferences.
    public void setHeaderInfo() {

        String name = SharedPrefManager.getInstance(HomeScreen.this).getUsers().getName();
        String email = SharedPrefManager.getInstance(HomeScreen.this).getUsers().getEmail();
        String phone = SharedPrefManager.getInstance(HomeScreen.this).getUsers().getPhone();

        navUsername.setText(name);
        navEmail.setText(email);
        navPhone.setText(phone);
    }
}