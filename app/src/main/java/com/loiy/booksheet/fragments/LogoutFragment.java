package com.loiy.booksheet.fragments;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.loiy.booksheet.R;
import com.loiy.booksheet.SharedPref.SharedPrefManager;

public class LogoutFragment extends Fragment {

 //declaring the logout button.
 Button logout_btn;

 //onCreate method used to link the java file with the XML file.
 @Nullable
 @Override
 public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
  return inflater.inflate(R.layout.activity_logout_fragment,null);
 }


 //onViewCreated method used to make actions and changes to the views in the XML file associated with this java file.
 @Override
 public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {


  //initializing the logout button.
  logout_btn = getView().findViewById(R.id.logout_btn);

  //when the logout button is clicked the app will exit the home screen and open the login screen using Logout method.
  logout_btn.setOnClickListener(new View.OnClickListener() {
   @Override
   public void onClick(View v) {

    getActivity().finish();
    SharedPrefManager.getInstance(getContext()).Logout();

   }
  });

 }
}