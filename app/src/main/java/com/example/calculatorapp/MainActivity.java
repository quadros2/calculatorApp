package com.example.calculatorapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

public class MainActivity extends AppCompatActivity {

    FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();

    DocumentReference documentReference = firebaseFirestore.collection(FirebaseAuth.getInstance().getCurrentUser().getUid() + "'s setup")
            .document("setUp");

    TextView calculatorText, valueText;

    Button button1, button2, button3, button4, button5, button6, button7, button8, button9, button0,
        decimalButton, additionButton, subtractionButton, equalsButton, multiplicationButton, divideButton,
            clearButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        calculatorText = findViewById(R.id.calculatorText);
        valueText = findViewById(R.id.value);

        button1 = findViewById(R.id.number1);
        button2 = findViewById(R.id.number2);
        button3 = findViewById(R.id.number3);
        button4 = findViewById(R.id.number4);
        button5 = findViewById(R.id.number5);
        button6 = findViewById(R.id.number6);
        button7 = findViewById(R.id.number7);
        button8 = findViewById(R.id.number8);
        button9 = findViewById(R.id.number9);
        button0 = findViewById(R.id.number0);
        decimalButton = findViewById(R.id.decimalButton);
        additionButton = findViewById(R.id.plusSign);
        subtractionButton = findViewById(R.id.minusSign);
        multiplicationButton = findViewById(R.id.multiplySign);
        equalsButton = findViewById(R.id.equalsButton);
        divideButton = findViewById(R.id.divideSign);
        clearButton = findViewById(R.id.clearButton);

        documentReference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                String isSetUp = documentSnapshot.getString("IsSetUp");
                if (isSetUp.equals("no")) {
                    introduce();
                }
            }
        });



        button0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                valueText.setText(valueText.getText().toString() + "0");
            }
        });

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                valueText.setText(valueText.getText().toString() + "1");
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                valueText.setText(valueText.getText().toString() + "2");
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                valueText.setText(valueText.getText().toString() + "3");
            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                valueText.setText(valueText.getText().toString() + "4");
            }
        });

        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                valueText.setText(valueText.getText().toString() + "5");
            }
        });

        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                valueText.setText(valueText.getText().toString() + "6");
            }
        });

        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                valueText.setText(valueText.getText().toString() + "7");
            }
        });

        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                valueText.setText(valueText.getText().toString() + "8");
            }
        });

        button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                valueText.setText(valueText.getText().toString() + "9");
            }
        });

        decimalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                valueText.setText(valueText.getText().toString() + ".");
            }
        });

        additionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                valueText.setText(valueText.getText().toString() + "+");
            }
        });

        subtractionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                valueText.setText(valueText.getText().toString() + "-");
            }
        });

        multiplicationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                valueText.setText(valueText.getText().toString() + "×");
            }
        });

        divideButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                valueText.setText(valueText.getText().toString() + "÷");
            }
        });

        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                valueText.setText("");
                calculatorText.setText("0");
            }
        });

        equalsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                process();
            }
        });
    }

    public void introduce() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Welcome!");
        builder.setMessage("This is your new calculator vault. Others will think this is just an" +
                "ordinary calculator, but you will know whats really behind this calculator. " +
                "Type in a password you will remember using the symbols and numbers on this calculator." +
                "Remember that this password is NOT RECOVERABLE, so make sure you remember it!" +
                "Press the equals key when done.");
        builder.setPositiveButton("Continue", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.create().show();
    }

    public void setUp() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        String newPasskey = valueText.getText().toString();
        builder.setTitle("Confirm Passkey");
        builder.setMessage("Your new passkey will be: " + newPasskey);
        builder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                documentReference.update("IsSetUp", "yes");
                documentReference.update("passkey", newPasskey);
                Intent intent = new Intent(getApplicationContext(), VaultActivity.class);
                startActivity(intent);
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.create().show();
    }

    public void accessVault() {
        Intent intent = new Intent(getApplicationContext(), VaultActivity.class);
        startActivity(intent);
    }

    public void calculate() {
        String getResult = valueText.getText().toString();
        getResult = getResult.replaceAll("×", "*");
        getResult = getResult.replaceAll("÷", "/");
        Context rhino = Context.enter();
        rhino.setOptimizationLevel(-1);
        String result = "";
        try{
            Scriptable scope = rhino.initStandardObjects();
            result = rhino.evaluateString(scope, getResult, "JavaScript",
                    1, null).toString();
        }catch (Exception e){
            result = "Error";
        }
        calculatorText.setText(result);
    }

    public void process() {
        documentReference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                String isSetUp = documentSnapshot.getString("IsSetUp");
                String StoredPasskey = documentSnapshot.getString("passkey");
                System.out.println(isSetUp);
                System.out.println(StoredPasskey);
                if (isSetUp.equals("no")) {
                    setUp();
                } else if (StoredPasskey.equals(valueText.getText().toString())) {
                    accessVault();
                } else {
                    calculate();
                }
            }
        });
    }
}
