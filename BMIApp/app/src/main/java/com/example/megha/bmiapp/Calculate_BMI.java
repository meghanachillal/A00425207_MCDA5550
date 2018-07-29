package com.example.megha.bmiapp;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Calculate_BMI extends AppCompatActivity {

    InClassDatabaseHelper helper;
    Button calculateBMIButton;
    Button btnViewBMIHistory;
    String userName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculate__bmi);
        Intent intent = getIntent();
        userName =intent.getStringExtra("NAME");

        helper = new InClassDatabaseHelper(this);
        btnViewBMIHistory = (Button) findViewById(R.id.btnViewBMIHistory);
        btnViewBMIHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent bmiListIntent = new Intent(getApplicationContext(),BMIListActivity.class);
                Bundle b = new Bundle();
                b.putString("NAME",userName);
                bmiListIntent.putExtras(b);
                startActivity(bmiListIntent);
              //  Intent intent = new Intent(Calculate_BMI.this, BMIListActivity.class);
                startActivity(bmiListIntent);
            }
        });
    }

    public void calculateBMI(View view)
    {

        EditText height = (EditText) findViewById(R.id.textHeight);
        EditText weight = (EditText) findViewById(R.id.textWeight);
        EditText result = (EditText) findViewById(R.id.resultText);

        String heightvalue = height.getText().toString();
        String weightvalue = weight.getText().toString();

        if (!heightvalue.equals("") && !weightvalue.equals("") ) {
            Double heightVal = Double.parseDouble(heightvalue);
            Double weightVal = Double.parseDouble(weightvalue);

            if (heightVal <= 0 && weightVal <= 0) {
                Toast.makeText(Calculate_BMI.this, "Please enter valid height and weight.", Toast.LENGTH_LONG).show();
            } else {
                //Calculate the BMI
                Double calc = (double) Math.round(weightVal / (heightVal * heightVal));

                //use DecimalFormat to limit to 2 Decimal Places


                helper.saveBMIData(heightVal, weightVal, calc);

                if (calc <= 18.5) {
                    result.setTextColor(Color.rgb(0, 0, 255));
                    result.setText("UnderWeight" + calc.toString());
                } else if (calc > 18.5 && calc <= 24.9) {
                    result.setTextColor(Color.rgb(51, 255, 0));
                    result.setText("Normal"+ calc.toString());
                } else if (calc >= 25 && calc <= 29.9) {
                    result.setTextColor(Color.rgb(255, 255, 102));
                    result.setText("OverWeight"+ calc.toString());
                } else {
                    result.setTextColor(Color.rgb(255, 51, 51));
                    result.setText("Obese"+ calc.toString());
                }
            }

        }

        else
        {
            Toast.makeText(Calculate_BMI.this, "Please fill the height and weight to proceed further.", Toast.LENGTH_LONG).show();
        }

    }

}
