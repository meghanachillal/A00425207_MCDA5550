package com.example.megha.bmiapp;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.ArrayList;

public class ListAdapterClass extends ArrayAdapter<BMIResult> {

    private LayoutInflater mInflater;
    private ArrayList<BMIResult> bmiResults;
    private int mViewResourceId;

    public ListAdapterClass(Context context, int textViewResourceId, ArrayList<BMIResult> bmiResults) {
        super(context, textViewResourceId, bmiResults);
        this.bmiResults = bmiResults;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mViewResourceId = textViewResourceId;
    }


    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = mInflater.inflate(mViewResourceId, null);

        BMIResult bmi = bmiResults.get(position);

        if (bmi != null) {
            TextView userName = (TextView) convertView.findViewById(R.id.txtUserName);
            TextView height = (TextView) convertView.findViewById(R.id.txtHeight);
            TextView weight = (TextView) convertView.findViewById(R.id.txtWeight);
            TextView bmiText = (TextView) convertView.findViewById(R.id.txtBMI);

            if (userName != null) {
                userName.setText(bmi.getUserName());
            }

            if (height != null) {
                height.setText(String.valueOf(bmi.getHeight()));
            }
            if (weight != null) {
                weight.setText(String.valueOf(bmi.getWeight()));
            }
            if (bmi != null){
                bmiText.setText(String.valueOf(bmi.getResult()));
            }
        }

        return convertView;

    }
}
