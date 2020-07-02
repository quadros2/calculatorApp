package com.example.calculatorapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class CreateNoteActivity extends AppCompatActivity {

    FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();

    DocumentReference newNoteRef;

    Button postButton;

    EditText titleText, noteText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_note);

        titleText = findViewById(R.id.titleText);
        noteText = findViewById(R.id.noteText);

        postButton = findViewById(R.id.postButton);
        postButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String, String> postNote = new HashMap<>();
                String title = titleText.getText().toString();
                String text = noteText.getText().toString();
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM-dd-yyyy HH:mm:ss a");
                LocalDateTime now = LocalDateTime.now();
                String dateAndTime = dtf.format(now);
                postNote.put("title", title);
                postNote.put("text", text);
                postNote.put("date", dateAndTime);
                postNote.put("toShow", "yes");
                newNoteRef =  firebaseFirestore.collection(FirebaseAuth.getInstance().getCurrentUser().getUid() + "'s notes")
                        .document(dateAndTime);
                newNoteRef.set(postNote);
                changeUI();
            }
        });
    }

    public void changeUI() {
        Intent intent = new Intent(this, VaultActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        finish();
    }
}
