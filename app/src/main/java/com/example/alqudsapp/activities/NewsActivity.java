package com.example.alqudsapp.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.alqudsapp.R;
import com.example.alqudsapp.adapters.QuestionsAdapter;
import com.example.alqudsapp.helpers.AppController;
import com.example.alqudsapp.models.Question;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NewsActivity extends AppCompatActivity {
    private RecyclerView questionsRecyclerView;
    private QuestionsAdapter questionsAdapter;
    private List<Question> questions = new ArrayList<>();
    ImageView add , map, txt;
    TextView tv;
    TextView ques;
    TextView answer;
    RequestQueue queue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);


        questionsRecyclerView = findViewById(R.id.questionsRecyclerView);
        add =findViewById(R.id.add);
        map =findViewById(R.id.map);
        txt =findViewById(R.id.change_text);
        tv=findViewById(R.id.textView3);
        ques=findViewById(R.id.questionTv);
        answer=findViewById(R.id.answerTv);
        questionsRecyclerViewSetup();
        tv.setText("أخر أخبار القدس");
        getQuestions();
        add.setVisibility(View.GONE);
        map.setVisibility(View.GONE);
        txt.setVisibility(View.GONE);

    }

    private void getQuestions() {
        String apiLink = "https://newsapi.org/v2/everything?q=%D8%A7%D9%84%D9%82%D8%AF%D8%B3&from=2021-06-12&to=2021-07-08&sortBy=popularity&apiKey=6530f57e1a8748699b822a327dd60216";

        queue = Volley.newRequestQueue(this);
        final StringRequest request = new StringRequest(Request.Method.GET, apiLink, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {

                try {
                    JSONObject object=new JSONObject(response);
                    JSONArray array=object.getJSONArray("articles");
                    Log.d("error1",array.toString());
                    Log.d("error1",object.toString());

                    for(int i=0;i<array.length();i++) {
                        JSONObject object1=array.getJSONObject(i);
                        String title=object1.getString("title");
                        String desc=object1.getString("description");
                        Question question = new Question(title,desc);
                        questions.add(question);
                    }
                    questionsAdapter.notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("error","error.toString()");
            }
        }
        ) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String>  params = new HashMap<String, String>();
                params.put("User-Agent", "user-agent=Mozilla/5.0 ");

                return params;
            }
        };


        queue.add(request);

    }


    private void questionsRecyclerViewSetup(){

        questionsAdapter = new QuestionsAdapter(NewsActivity.this, questions);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext()
                , LinearLayoutManager.VERTICAL, false);
        questionsRecyclerView.setLayoutManager(linearLayoutManager);
        questionsRecyclerView.setAdapter(questionsAdapter);

    }



}

//
//
//
//            StringRequest stringRequest = new StringRequest(Request.Method.GET, apiLink,
//                    new Response.Listener<String>() {
//                        @Override
//                        public void onResponse(String response) {
//
//                            try {
//
//                                JSONArray jsonArray = new JSONArray(response);
//                                if (jsonArray.length() >= 1) {
//
//                                    JSONObject jsonObject = jsonArray.getJSONObject(jsonArray.length() - 1);
//                                    String country = jsonObject.getString("title");
//                                    String status = jsonObject.getString("description");
//
//                                    ques.setText(country);
//                                    answer.setText(status);
//
//                                }
//
//
//                            } catch (JSONException e) {
//
//                                Toast.makeText(NewsActivity.this
//                                        , "حدث خطأ ما", Toast.LENGTH_SHORT).show();
//
//                            }
//
//
//                        }
//                    }, new Response.ErrorListener() {
//                @Override
//                public void onErrorResponse(VolleyError error) {
//
//                    Toast.makeText(NewsActivity.this
//                            , "حدث خطأ ما", Toast.LENGTH_SHORT).show();
//
//                }
//            });
//
//            AppController.getInstance().addToRequestQueue(stringRequest);

