package com.example.alqudsapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.SwitchCompat;
import androidx.cardview.widget.CardView;

import com.example.alqudsapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.messaging.FirebaseMessaging;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        if (firebaseUser != null) {
            Log.d("toast", firebaseUser.getDisplayName()
                    + " " + firebaseUser.getPhoneNumber());
        } else {
            Toast.makeText(this, "حدث خطأ ما", Toast.LENGTH_SHORT).show();
        }


            SwitchCompat theme = findViewById(R.id.switch_theme);
       theme.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

         @Override
         public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
             if(buttonView.isPressed()){

                 if (isChecked) {
                     AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                 }else {
                     AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                 }

             }
         }
     }
        );



        CardView questionsCardView = findViewById(R.id.questionsCardView);
        questionsCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), InformationActivity.class);
                startActivity(intent);
            }
        });

        CardView chatCardView = findViewById(R.id.chatCardView);
        chatCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),UsersListActivity.class);
                startActivity(intent);
            }
        });

        CardView statisticsCardView = findViewById(R.id.statisticsCardView);
        statisticsCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), NewsActivity.class);
                startActivity(intent);
            }
        });

        CardView photosAlbumCardView = findViewById(R.id.photosAlbumCardView);
        photosAlbumCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), PhotoActivity.class);
                startActivity(intent);
            }
        });

        CardView videosCardView = findViewById(R.id.videosCardView);
        videosCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),VideoActivity.class);
                startActivity(intent);
            }
        });
        CardView sqlCardView = findViewById(R.id.sqlCardView);
        sqlCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),ShowRecycleCity.class);
                startActivity(intent);
            }
        });



    }





}
