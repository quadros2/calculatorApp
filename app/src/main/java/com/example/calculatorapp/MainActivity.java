package com.example.calculatorapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

public class MainActivity extends AppCompatActivity {

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
                String getResult = valueText.getText().toString();
                getResult = getResult.replaceAll("×", "*");
                getResult = getResult.replaceAll("÷", "/");
                Context rhino = Context.enter();
                rhino.setOptimizationLevel(-1);
                String result = "";
                try{
                    Scriptable scope = rhino.initStandardObjects();
                    result = rhino.evaluateString(scope, getResult, "JavaScript", 1, null).toString();
                }catch (Exception e){
                    result = "Error";
                }

                calculatorText.setText(result);
            }
        });
    }
}
