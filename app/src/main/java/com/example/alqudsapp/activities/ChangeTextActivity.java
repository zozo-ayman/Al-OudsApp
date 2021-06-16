package com.example.alqudsapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.alqudsapp.R;

public class ChangeTextActivity extends AppCompatActivity {

    private Button btn_increase, btn_decrease;
    private TextView tv_text;
    int OurFontSize = 14;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_text);



        btn_increase = (Button) findViewById(R.id.btn_increase);
        btn_decrease = (Button) findViewById(R.id.btn_decrease);
        tv_text = (TextView) findViewById(R.id.tv_text);


        btn_increase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OurFontSize +=4f;
                tv_text.setTextSize(TypedValue.COMPLEX_UNIT_SP,OurFontSize);

            }
        });


        btn_decrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OurFontSize -=4f;
                tv_text.setTextSize(TypedValue.COMPLEX_UNIT_SP ,OurFontSize);

            }
        });
    }
}
