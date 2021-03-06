package com.firstapp.calculatorapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class SignUpActivity extends AppCompatActivity {

    FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();

    DocumentReference setUpRef;

    DocumentReference noteVaultRef;

    TextView signUpSign, signUpEmailSign, signUpPasswordSign;
    EditText signUpEmailEntry, signUpPasswordEntry;
    Button createAccount;
    String email, password;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_signup);

        mAuth = FirebaseAuth.getInstance();

        signUpSign = findViewById(R.id.signUpSign);
        signUpSign.setVisibility(View.VISIBLE);
        signUpEmailSign = findViewById(R.id.signUpEmailSign);
        signUpEmailSign.setVisibility(View.VISIBLE);
        signUpPasswordSign = findViewById(R.id.signUpPasswordSign);
        signUpPasswordSign.setVisibility(View.VISIBLE);

        signUpEmailEntry = findViewById(R.id.signUpEmailEntry);
        signUpEmailEntry.setVisibility(View.VISIBLE);
        signUpPasswordEntry = findViewById(R.id.signUpPasswordEntry);
        signUpPasswordEntry.setVisibility(View.VISIBLE);

        createAccount = findViewById(R.id.createAccountButton);
        createAccount.setVisibility(View.VISIBLE);
        createAccount.setOnClickListener(v -> {
            email = signUpEmailEntry.getText().toString();
            password = signUpPasswordEntry.getText().toString();
            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                setUpRef = firebaseFirestore.collection(FirebaseAuth.getInstance().getCurrentUser().getUid() + "'s setup")
                                        .document("setUp");
                                Map<String, String> mapToSend = new HashMap<>();
                                mapToSend.put("IsSetUp", "no");
                                mapToSend.put("passkey", "none");
                                noteVaultRef = firebaseFirestore.collection(FirebaseAuth.getInstance().getCurrentUser().getUid() + "'s notes")
                                        .document("Initial Note");
                                Map<String, String> notestart = new HashMap<>();
                                notestart.put("title", "noshow");
                                notestart.put("note", "noshow");
                                notestart.put("date", "noshow");
                                notestart.put("toShow", "no");
                                noteVaultRef.set(notestart);
                                setUpRef.set(mapToSend).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        loginUI();
                                    }
                                });
                            } else {
                                Toast.makeText(SignUpActivity.this, "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        });
    }
    public void loginUI() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
}
