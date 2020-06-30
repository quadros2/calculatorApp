package com.example.calculatorapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();

    DocumentReference documentReference;

    private TextView welcome, emailSign, pwSign, signUpDirections;
    private EditText enterEmail, enterPassword;
    private Button signUpButton, loginButton;

    private String email;
    private String password;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //check if user was already signed in


        mAuth = FirebaseAuth.getInstance();

        if (mAuth.getCurrentUser() != null) {
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
        }

        //set the loginactivity views to visible
        welcome = findViewById(R.id.welcomeSign);
        welcome.setVisibility(View.VISIBLE);
        emailSign = findViewById(R.id.emailSign);
        emailSign.setVisibility(View.VISIBLE);
        pwSign = findViewById(R.id.pwSign);
        pwSign.setVisibility(View.VISIBLE);
        signUpDirections = findViewById(R.id.signUpDirections);
        signUpDirections.setVisibility(View.VISIBLE);

        //set edit texts to visible
        enterEmail = findViewById(R.id.enterEmail);
        enterEmail.setVisibility(View.VISIBLE);
        enterPassword = findViewById(R.id.enterPw);
        enterPassword.setVisibility(View.VISIBLE);

        //set buttons to visible and program them
        signUpButton = findViewById(R.id.signUpButton);
        signUpButton.setVisibility(View.VISIBLE);
        signUpButton.setOnClickListener(v -> {
            signUpUI();
        });
        loginButton = findViewById(R.id.loginButton);
        loginButton.setVisibility(View.VISIBLE);
        loginButton.setOnClickListener(v -> {
            email = enterEmail.getText().toString();
            password = enterPassword.getText().toString();
            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, task -> {
                        if (task.isSuccessful()) {
                            documentReference = firebaseFirestore.collection(FirebaseAuth.getInstance().getCurrentUser().getUid() + "'s setup")
                                    .document("setUp");
                            documentReference.update("IsSetUp", "no");
                            documentReference.update("passkey", "none");
                            Intent intent = new Intent(this, MainActivity.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(LoginActivity.this, "Incorrect email or password",
                                    Toast.LENGTH_SHORT).show();
                        }
                    });
        });
    }

    public void signUpUI() {
        Intent intent = new Intent(this, SignUpActivity.class);
        startActivity(intent);
    }
}
