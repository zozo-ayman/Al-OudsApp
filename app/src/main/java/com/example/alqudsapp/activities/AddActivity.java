package com.example.alqudsapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.alqudsapp.R;
import com.example.alqudsapp.helpers.*;

public class AddActivity extends AppCompatActivity {

    EditText cityName, cityDetails, cityNumber;
    Button add_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        cityName = findViewById(R.id.cityName);
        cityDetails = findViewById(R.id.cityDetails);
        cityNumber = findViewById(R.id.CityNumber);


        add_button = findViewById(R.id.add_button);



        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(AddActivity.this);
                myDB.addCity(cityName.getText().toString().trim(),
                        cityDetails.getText().toString().trim(),
                        Integer.valueOf(cityNumber.getText().toString().trim()));

                //////////////////////////////////////////
                //Refresh Activity
                Intent intent = new Intent( AddActivity.this, ShowRecycleCity.class);
                startActivity(intent);
                finish();

            }
        });






    }
}
