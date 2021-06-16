package com.example.alqudsapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.alqudsapp.R;
import com.example.alqudsapp.adapters.QuestionsAdapter;
import com.example.alqudsapp.models.Question;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.ArrayList;
import java.util.List;

public class InformationActivity extends AppCompatActivity {

    private RecyclerView questionsRecyclerView;
    private QuestionsAdapter questionsAdapter;
    private List<Question> questions = new ArrayList<>();
    private FirebaseFirestore firebaseFirestore;
    ImageView add ,change_text , map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        add =findViewById(R.id.add);
        map =findViewById(R.id.map);
        change_text =findViewById(R.id.change_text);
        questionsRecyclerView = findViewById(R.id.questionsRecyclerView);




        firebaseFirestore = FirebaseFirestore.getInstance();
        questionsRecyclerViewSetup();

        getQuestions();


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),AddQuestionActivity.class);
                startActivity(intent);
            }
        });

        change_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),ChangeTextActivity.class);
                startActivity(intent);
            }
        });


        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),MapActivity.class);
                startActivity(intent);
            }
        });


    }

    private void getQuestions() {

        firebaseFirestore.collection("questions")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {

                                Question question = document.toObject(Question.class);
                                questions.add(question);

                            }

                            questionsAdapter.notifyDataSetChanged();

                        } else {

                            Toast.makeText(InformationActivity.this, "فشل جلب البيانات", Toast.LENGTH_SHORT).show();

                        }
                    }
                });

    }

    private void questionsRecyclerViewSetup() {

        questionsAdapter = new QuestionsAdapter(InformationActivity.this, questions);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext()
                , LinearLayoutManager.VERTICAL, false);
        questionsRecyclerView.setLayoutManager(linearLayoutManager);
        questionsRecyclerView.setAdapter(questionsAdapter);

    }


}
