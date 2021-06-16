package com.example.alqudsapp.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.alqudsapp.R;
import com.example.alqudsapp.activities.UpdateActivity;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private Context context;
    private Activity activity;
    private ArrayList city_id, city_name, city_details, city_number;
// ArrayList<String> city_id, city_name, city_details, city_number;

    public CustomAdapter(Activity activity, Context context, ArrayList city_id, ArrayList city_details, ArrayList city_number,
                         ArrayList book_pages){
        this.activity = activity;
        this.context = context;
        this.city_id = city_id;
        this.city_name = city_details;
        this.city_details = city_number;
        this.city_number = book_pages;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);

        return new MyViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {


        holder.id.setText(String.valueOf(city_id.get(position)));
        holder.cityName.setText(String.valueOf(city_name.get(position)));
        holder.cityDetails.setText(String.valueOf(city_details.get(position)));
        holder.cityNumber.setText(String.valueOf(city_number.get(position)));


        //Recyclerview onClickListener
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, UpdateActivity.class);
                intent.putExtra("id", String.valueOf(city_id.get(position)));
                intent.putExtra("cityName", String.valueOf(city_name.get(position)));
                intent.putExtra("cityDetails", String.valueOf(city_details.get(position)));
                intent.putExtra("cityNumber", String.valueOf(city_number.get(position)));
                activity.startActivityForResult(intent, 1);
            }
        });


    }

    @Override
    public int getItemCount() {
        return city_id.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView id, cityName, cityDetails, cityNumber;
        CardView mainLayout;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.idCity);
            cityName = itemView.findViewById(R.id.CityName);
            cityDetails = itemView.findViewById(R.id.detailsCity);
            cityNumber = itemView.findViewById(R.id.numberCity);
            mainLayout = itemView.findViewById(R.id.mainLayout);


            //Animate Recyclerview
            Animation translate_anim = AnimationUtils.loadAnimation(context, R.anim.translate_anim);
            mainLayout.setAnimation(translate_anim);

        }

    }

}
