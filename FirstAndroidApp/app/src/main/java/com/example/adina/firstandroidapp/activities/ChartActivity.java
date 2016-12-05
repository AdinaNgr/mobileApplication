package com.example.adina.firstandroidapp.activities;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import com.example.adina.firstandroidapp.R;
import com.example.adina.firstandroidapp.model.Movie;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;

/**
 * Created by Adina on 12/5/2016.
 */

public class ChartActivity extends AppCompatActivity {
    BarChart barChart;

    @Override
    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        barChart = (BarChart) findViewById(R.id.barchart);

        ArrayList<BarEntry> barEntries = new ArrayList<>();
        barEntries.add(new BarEntry(44f,0));
        BarDataSet barDataSet = new BarDataSet(barEntries, "Dates");

        Bundle extras = getIntent().getExtras();
        ArrayList<String> theDates = new ArrayList<>();
        if (extras != null) {
            String mTitle = extras.getString("movieTitle");
            theDates.add(mTitle);
        }

        BarData theData = new BarData(barDataSet);
        barChart.setData(theData);

        barChart.setTouchEnabled(true);
        barChart.setDragEnabled(true);
        barChart.setScaleEnabled(true);


    }
}

