package com.example.megha.bmiapp;

import android.annotation.SuppressLint;
import android.app.ListActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class BMIListActivity extends ListActivity {

    InClassDatabaseHelper helper;
    ArrayList<BMIResult> bmiList ;
    BMIResult bmiResults;
    ListView listBMIResults;
    private  String userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmilist);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            userName = bundle.getString("NAME");
        }

        helper = new InClassDatabaseHelper(this);
        bmiList = new ArrayList<>();
        Cursor historyData = helper.getBMIHistoryData();
        int numRows = historyData.getCount();
        if (numRows == 0) {
            Toast.makeText(BMIListActivity.this, "No History of BMI Details found : (.", Toast.LENGTH_LONG).show();
        }
        else {
            int i=0;
            while (historyData.moveToNext()) {
                bmiResults = new BMIResult(historyData.getDouble(1),historyData.getDouble(2));
                bmiResults.setUserName(userName);
                bmiList.add(i, bmiResults);
                i++;
            }

            ListAdapterClass listAdapter = new ListAdapterClass(this,R.layout.list_adapter_view, bmiList);

            listBMIResults = (ListView) findViewById(android.R.id.list);

            listBMIResults.setAdapter(listAdapter);
            }
        }


    }



