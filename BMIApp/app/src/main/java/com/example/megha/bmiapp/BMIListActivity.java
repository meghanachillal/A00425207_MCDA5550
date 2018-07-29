package com.example.megha.bmiapp;

import android.annotation.SuppressLint;
import android.app.ListActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class BMIListActivity extends ListActivity {

    InClassDatabaseHelper helper;
    ListView listBMIResults;
    //BMIResult[] results = {new BMIResult(5.5, 100), new BMIResult(4.3, 156)};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmilist);

         listBMIResults = (ListView) findViewById(android.R.id.list);
        helper = new InClassDatabaseHelper(this);
        ArrayList<String> bmiResults = new ArrayList<>();
        Cursor data = helper.getBMIHistoryData();

        if (data.getCount() == 0) {
            Toast.makeText(BMIListActivity.this, "No History of BMI Details stored : (.", Toast.LENGTH_LONG).show();

        } else {
            int i=0;
            while (data.moveToNext()) {
                bmiResults.add(data.getString(0));
                ListAdapter listAdapter = new ArrayAdapter<>(this,
                        android.R.layout.simple_list_item_1,
                        bmiResults
                );
                listBMIResults.setAdapter(listAdapter);
            }
        }


    }
}


