package com.example.calculatorapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SetUpActivity extends AppCompatActivity {

    Boolean isSetUp = false;

    TextView valueText;

    Button button1, button2, button3, button4, button5, button6, button7, button8, button9, button0,
            decimalButton, additionButton, subtractionButton, equalsButton, multiplicationButton, divideButton,
            clearButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup);

        if (isSetUp) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }

        valueText = findViewById(R.id.valuer);

        button1 = findViewById(R.id.numberr1);
        button2 = findViewById(R.id.numberr2);
        button3 = findViewById(R.id.numberr3);
        button4 = findViewById(R.id.numberr4);
        button5 = findViewById(R.id.numberr5);
        button6 = findViewById(R.id.numberr6);
        button7 = findViewById(R.id.numberr7);
        button8 = findViewById(R.id.numberr8);
        button9 = findViewById(R.id.numberr9);
        button0 = findViewById(R.id.numberr0);
        decimalButton = findViewById(R.id.decimalrButton);
        additionButton = findViewById(R.id.plusrSign);
        subtractionButton = findViewById(R.id.minusrSign);
        multiplicationButton = findViewById(R.id.multiplyrSign);
        equalsButton = findViewById(R.id.equalsrButton);
        divideButton = findViewById(R.id.dividerSign);
        clearButton = findViewById(R.id.clearrButton);

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
            }
        });
    }
}
