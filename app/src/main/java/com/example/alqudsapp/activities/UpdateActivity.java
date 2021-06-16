package com.example.alqudsapp.activities;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.alqudsapp.R;
import com.example.alqudsapp.helpers.MyDatabaseHelper;

public class UpdateActivity extends AppCompatActivity {

    EditText nameCity, detailsCity, numberCity;
    Button update_button, delete_button;

    String id, myCity, myDetails, myNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        nameCity = findViewById(R.id.cityName2);
        detailsCity = findViewById(R.id.cityDetails2);
        numberCity = findViewById(R.id.cityNumber2);
        update_button = findViewById(R.id.update_button);
        delete_button = findViewById(R.id.delete_button);


        getAndSetIntentData();


        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setTitle(myCity);
        }


        update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                MyDatabaseHelper myDB = new MyDatabaseHelper(UpdateActivity.this);
                myCity = nameCity.getText().toString().trim();
                myDetails = detailsCity.getText().toString().trim();
                myNumber = numberCity.getText().toString().trim();
                myDB.updateData(id, myCity, myDetails, myNumber);
            }
        });



        delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmDialog();
            }
        });


    }

    void getAndSetIntentData(){
        if(getIntent().hasExtra("id") && getIntent().hasExtra("cityName") &&
                getIntent().hasExtra("cityDetails") && getIntent().hasExtra("cityNumber")){


            id = getIntent().getStringExtra("id");
            myCity = getIntent().getStringExtra("cityName");
            myDetails = getIntent().getStringExtra("cityDetails");
            myNumber = getIntent().getStringExtra("cityNumber");



            nameCity.setText(myCity);
            detailsCity.setText(myDetails);
            numberCity.setText(myNumber);


            Log.d("stev",  myCity+" "+myDetails+" "+myNumber);
        }else{
            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
        }
    }

    void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("حذف " + myCity + " ؟");
        builder.setMessage("هل أنت متأكد أنك تريد الحذف " + myCity + " ؟");
        builder.setPositiveButton("نعم", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(UpdateActivity.this);
                myDB.deleteOneRow(id);
                finish();
            }
        });
        builder.setNegativeButton("لا", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.create().show();
    }
}
