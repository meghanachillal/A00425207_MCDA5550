package com.example.megha.bmiapp;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Calculate_BMI extends AppCompatActivity {

    InClassDatabaseHelper helper;
    Button calculateBMIButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculate__bmi);
        helper = new InClassDatabaseHelper(this);

    }

    public void calculateBMI(View view)
    {
        //gets the height
        EditText height = (EditText) findViewById(R.id.textHeight);
        String heightvalue = height.getText().toString();
        Double heightVal = Double.parseDouble(heightvalue);
        System.out.println("Here is the height " + heightVal);

        //gets the weight
        EditText weight = (EditText) findViewById(R.id.textWeight);
        String weightvalue = weight.getText().toString();
        Double weightVal = Double.parseDouble(weightvalue);
        System.out.println("Here is the weight " + weightVal);

        //Calculate the BMI
        Double calc = (weightVal / (heightVal * heightVal));
        EditText result = (EditText) findViewById(R.id.resultText);


        //use DecimalFormat to limit to 2 Decimal Places
        result.setText(calc.toString());


        helper.saveBMIData(heightVal,weightVal,calc);


    }
}
