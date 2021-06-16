package com.example.alqudsapp.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.alqudsapp.R;
import com.example.alqudsapp.adapters.QuestionsAdapter;
import com.example.alqudsapp.models.Question;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AddQuestionActivity extends AppCompatActivity {
    private QuestionsAdapter questionsAdapter;
    private List<Question> questions = new ArrayList<>();
    private FirebaseFirestore firebaseFirestore;
    EditText ed_text;
    EditText ed_answer;
    Button add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_question);
        firebaseFirestore = FirebaseFirestore.getInstance();
        ed_text=findViewById(R.id.edText);
        ed_answer=findViewById(R.id.edAnswer);
        add=findViewById(R.id.addBtn);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddQuestion();
                Intent intent = new Intent(getApplicationContext(), InformationActivity.class);
                startActivity(intent);
            }
        });

    }
    private void AddQuestion() {
        Map<String, Object> question = new HashMap<>();
        question.put("text", ed_text.getText().toString());
        question.put("answer",ed_answer.getText().toString());


        firebaseFirestore.collection("questions")
                .add(question)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d("msg", "DocumentSnapshot added with ID: " + documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("msg", "Error adding document", e);
                    }
                });


    }
}