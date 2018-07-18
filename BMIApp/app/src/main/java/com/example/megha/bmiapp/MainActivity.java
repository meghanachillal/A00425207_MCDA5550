package com.example.megha.bmiapp;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import  android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private DatePickerDialog datePickerDialog;
    private SimpleDateFormat simpleDateFormat;

    InClassDatabaseHelper helper;
    EditText resultName;
    EditText resultPassword;
    EditText resultHealthCardNumber;
    EditText resultDate;
    Button personSubmitButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resultPassword = (EditText) findViewById(R.id.textPassword);
        resultHealthCardNumber = (EditText) findViewById(R.id.textHealthCardNumber);
        resultName = (EditText) findViewById(R.id.textName);
        resultDate = (EditText) findViewById(R.id.textDate);
        personSubmitButton = (Button) findViewById(R.id.buttonPersonSubmit);

        resultDate.setOnClickListener(this);
        personSubmitButton.setOnClickListener(this);

        simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());


        helper = new InClassDatabaseHelper(this);
        SQLiteDatabase db = helper.getWritableDatabase();

        //Run a query
        Cursor cursor = db.query(InClassDatabaseHelper.TABLE_NAME, new String[]{"NAME", "PASSWORD", "DATE", "HEALTH_CARD_NUMB"}, null, null, null, null, null);


        if (cursor.moveToFirst()) {
            Log.d("Cursor", String.valueOf(cursor.getCount()));
            String name = cursor.getString(0);
            String password = cursor.getString(1);
            String date = cursor.getString(2);
            String healthCardNumber = cursor.getString(3);
            resultName.setText(name);
            resultPassword.setText(password);
            resultDate.setText(date.toString());
            resultHealthCardNumber.setText(healthCardNumber);
        }

        cursor.close();  // cleanup
        db.close();

        setDateOfBirthField();
    }

//    public void onClickEnter(View view) {
//        Intent intent = new Intent(this, Calculate_BMI.class);
//
//        startActivity(intent);
//    }

    private void setDateOfBirthField() {
        Calendar calendar = Calendar.getInstance();
        resultDate.setFocusable(false);
        resultDate.setClickable(true);
        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int selectedYear, int selectedMonth, int selectedDay) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(selectedYear, selectedMonth, selectedDay);
                resultDate.setText(simpleDateFormat.format(newDate.getTime()));
            }
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));



    }


    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.textDate:
                datePickerDialog.setTitle("Select Date");
                datePickerDialog.show();
                break;

            case R.id.buttonPersonSubmit:
                resultName = (EditText) findViewById(R.id.textName);
                resultPassword = (EditText) findViewById(R.id.textPassword);
                resultDate = (EditText)findViewById(R.id.textDate);
                String name = resultName.getText().toString();
                String password = resultPassword.getText().toString();
                String healthCardNumber = resultHealthCardNumber.getText().toString();
                String dateOfBirth = resultDate.getText().toString();
                helper.savePersonData(name,password,healthCardNumber,dateOfBirth);
                Intent intent = new Intent(this, Calculate_BMI.class);
                startActivity(intent);
                break;
        }

    }
}