package com.example.alqudsapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import com.example.alqudsapp.R;

public class VideoActivity extends AppCompatActivity {
    TextView tv1 , tv2 , tv3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        Uri uri = Uri.parse("https://firebasestorage.googleapis.com/v0/b/projmcc-aa2b6.appspot.com/o/%D9%85%D8%A6%D8%A9%20%D8%AC%D8%B1%D9%8A%D8%AD%20%D9%81%D9%84%D8%B3%D8%B7%D9%8A%D9%86%D9%8A%20%D9%86%D8%AA%D9%8A%D8%AC%D8%A9%20%D8%A7%D9%84%D9%85%D9%88%D8%A7%D8%AC%D9%87%D8%A7%D8%AA%20%D9%85%D8%B9%20%D9%82%D9%88%D8%A7%D8%AA%20%D8%A7%D9%84%D8%A7%D8%AD%D8%AA%D9%84%D8%A7%D9%84%20%D8%A7%D9%84%D8%A5%D8%B3%D8%B1%D8%A7%D8%A6%D9%8A%D9%84%D9%8A.mp4?alt=media&token=28f2254f-ffce-419b-b0f9-9bddeda047f3");
        Uri uri1 = Uri.parse("https://firebasestorage.googleapis.com/v0/b/projmcc-aa2b6.appspot.com/o/%D8%A7%D9%84%D9%82%D8%AF%D8%B3..%20%D9%85%D8%B3%D8%AA%D9%88%D8%B7%D9%86%D9%88%D9%86%20%D9%8A%D8%B9%D8%AA%D8%AF%D9%88%D9%86%20%D8%B9%D9%84%D9%89%20%D9%85%D9%88%D8%A7%D8%A6%D8%AF%20%D8%A5%D9%81%D8%B7%D8%A7%D8%B1%20%D9%81%D9%8A%20_%D8%A7%D9%84%D8%B4%D9%8A%D8%AE%20%D8%AC%D8%B1%D8%A7%D8%AD_.mp4?alt=media&token=f35bfbf3-2e9d-4f1f-88b7-dc3ecccb89e2");
        Uri uri2 = Uri.parse("https://firebasestorage.googleapis.com/v0/b/projmcc-aa2b6.appspot.com/o/%D9%81%D9%8A%D8%AF%D9%8A%D9%88%20%D8%B5%D8%A7%D8%AF%D9%85%20%D9%84%D9%82%D9%88%D8%A7%D8%AA%20%D8%A7%D9%84%D8%A7%D8%AD%D8%AA%D9%84%D8%A7%D9%84%20%D8%AA%D8%B9%D8%AA%D8%AF%D9%8A%20%D8%B9%D9%84%D9%89%20%D9%81%D8%AA%D9%8A%D8%A7%D8%AA%20%D9%81%D9%84%D8%B3%D8%B7%D9%8A%D9%86%D9%8A%D8%A7%D8%AA%20%D9%88%D8%AA%D8%B9%D8%AA%D9%82%D9%84%20%D8%B9%D8%AF%D8%AF%D8%A7%20%D9%85%D9%86%D9%87%D9%86.mp4?alt=media&token=456996d8-dcea-4fdb-9c97-52d76fabc281");


        tv1 =findViewById(R.id.details1);
        tv2 =findViewById(R.id.details2);
        tv3 =findViewById(R.id.details3);



        tv1.setMovementMethod(LinkMovementMethod.getInstance());
        tv2.setMovementMethod(LinkMovementMethod.getInstance());
        tv3.setMovementMethod(LinkMovementMethod.getInstance());


        VideoView videoView1 = findViewById(R.id.videoView1);
        videoView1.setVideoURI(uri);
        MediaController mediaController1 = new MediaController(this);
        videoView1.setMediaController(mediaController1);
        mediaController1.setAnchorView(videoView1);


//        tv1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getApplicationContext(),DetailsVideoActivity.class);
//              startActivity(intent);
//            }
//        });




        VideoView videoView2 = findViewById(R.id.videoView2);
        videoView2.setVideoURI(uri1);
        MediaController mediaController2 = new MediaController(this);
        videoView2.setMediaController(mediaController2);
        mediaController2.setAnchorView(videoView2);


//        tv2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getApplicationContext(),DetailsVideoActivity.class);
//                startActivity(intent);
//            }
//        });


        VideoView videoView3 = findViewById(R.id.videoView3);
        videoView3.setVideoURI(uri2);
        MediaController mediaController3 = new MediaController(this);
        videoView3.setMediaController(mediaController3);
        mediaController3.setAnchorView(videoView3);


//        tv3.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getApplicationContext(),DetailsVideoActivity.class);
//                startActivity(intent);
//            }
//        });


    }
}





//        videoView1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getApplicationContext(),QuestionsActivity.class);
//                startActivity(intent);
//            }
//        });