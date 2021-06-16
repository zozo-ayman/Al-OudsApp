package com.example.alqudsapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.alqudsapp.R;
import com.example.alqudsapp.adapters.MessagesAdapter;
import com.example.alqudsapp.models.Message;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ChatActivity extends AppCompatActivity {

    private TextView receiverNameTv;
    private EditText messageEd;
    private RecyclerView messagesRecyclerView;
    private MessagesAdapter messagesAdapter;
    private List<Message> messages = new ArrayList<>();
    private String receiverId;
    private String senderId;
    private DatabaseReference chatsReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        FirebaseDatabase parentReference = FirebaseDatabase.getInstance();
        chatsReference = parentReference.getReference("chats");

        messageEd = findViewById(R.id.messageEd);
        messagesRecyclerView = findViewById(R.id.messagesRecyclerView);
        receiverNameTv = findViewById(R.id.receiverNameTv);

        messagesRecyclerViewSetup();

        if (getIntent() != null) {
            Intent intent = getIntent();
            if (intent.hasExtra("receiverId") && intent.hasExtra("receiverName")) {
                receiverId = intent.getStringExtra("receiverId");
                String receiverName = intent.getStringExtra("receiverName");
                receiverNameTv.setText(receiverName);

                FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
                FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                if (firebaseUser != null) {
                    senderId = firebaseUser.getUid();
                }

              getMessages();

             listenToNewMessages();

            }
        }

        Button sendBtn = findViewById(R.id.sendBtn);
        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String messageText = messageEd.getText().toString().trim();

                if (!messageText.isEmpty()) {

                    Message message = new Message(messageText, senderId, receiverId);

                    sendMessage(message);
                    messageEd.setText("");

                }

            }
        });

    }

    private void listenToNewMessages() {

        chatsReference.orderByKey().limitToLast(1)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {

                            Message message = snapshot.getValue(Message.class);
                            if ((message.getSender().equals(senderId)
                                    && message.getReceiver().equals(receiverId))
                                    || (message.getSender().equals(receiverId)
                                    && message.getReceiver().equals(senderId))) {

                                messages.add(message);
                                messagesAdapter.notifyDataSetChanged();
                                messagesRecyclerView.scrollToPosition(messages.size() - 1);

                            }

                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

    }

    private void sendMessage(Message message) {
        chatsReference.push().setValue(message)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                       Log.d("toast"
                                , "فشلت عملية الإرسال"
                       );

                    }
                });
    }

    private void getMessages() {

        chatsReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {

                    Message message = snapshot.getValue(Message.class);

                    if ((message.getSender().equals(senderId)
                            && message.getReceiver().equals(receiverId))
                            || (message.getSender().equals(receiverId)
                            && message.getReceiver().equals(senderId))) {

                        messages.add(message);

                    }

                }

                messagesAdapter.notifyDataSetChanged();
               messagesRecyclerView.scrollToPosition(messages.size() - 1);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    private void messagesRecyclerViewSetup() {

        messagesAdapter = new MessagesAdapter(getApplicationContext(), messages);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext()
                , LinearLayoutManager.VERTICAL, false);
      //  linearLayoutManager.setStackFromEnd(true);
        messagesRecyclerView.setLayoutManager(linearLayoutManager);
        messagesRecyclerView.setAdapter(messagesAdapter);

    }


}
