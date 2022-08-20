package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private  Button plus ;
    private Button divide ;
    private Button multiplication ;
    private Button minus ;
    private TextView t;
    private  EditText number1;
    private EditText number2 ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    plus = (Button) findViewById(R.id.plus);
    divide =  (Button) findViewById(R.id.divide);
    multiplication = (Button) findViewById(R.id.multiplication);
    minus = (Button) findViewById(R.id.minus);
    t = (TextView) findViewById(R.id.textView);
    number1 = (EditText)findViewById(R.id.editTextNumber);
    number2 = (EditText)findViewById(R.id.editTextNumber2);

    plus.setOnClickListener(new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            double result;
        result=Double.parseDouble(number1.getText().toString())+Double.parseDouble(number2.getText().toString());
        t.setText(String.valueOf(result));
        }
    });
    minus.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            double result;
            result=Double.parseDouble(number1.getText().toString())-Double.parseDouble(number2.getText().toString());
            t.setText(String.valueOf(result));

        }
    });
    divide.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            double result;
            result=Double.parseDouble(number1.getText().toString())/Double.parseDouble(number2.getText().toString());
            t.setText(String.valueOf(result));
        }
    });
    multiplication.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            double result;
            result=Double.parseDouble(number1.getText().toString())*Double.parseDouble(number2.getText().toString());
            t.setText(String.valueOf(result));

        }
    });

    }
}