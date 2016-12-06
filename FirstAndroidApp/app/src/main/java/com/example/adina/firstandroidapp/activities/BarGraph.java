package com.example.adina.firstandroidapp.activities;

/**
 * Created by Adina on 12/6/2016.
 */

import org.achartengine.ChartFactory;
import org.achartengine.chart.BarChart.Type;
import org.achartengine.model.CategorySeries;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class BarGraph {
    ArrayList<String> titles;
    ArrayList<String> ratings;

    public BarGraph(ArrayList<String> titles, ArrayList<String> ratings){
        this.titles=titles;
        this.ratings = ratings;
    }
    public Intent getIntent(Context context){

        int y[] = new int[ratings.size()];// = {25,10,15,20};
        for(int i=0;i<ratings.size();i++){
            y[i] =  Integer.parseInt(ratings.get(i));
        }
        CategorySeries series = new CategorySeries("Bar1");
        for(int i=0; i < y.length; i++){
            series.add("Bar"+(i+1),y[i]);
        }

        XYMultipleSeriesDataset dataSet = new XYMultipleSeriesDataset();  // collection of series under one object.,there could any
        dataSet.addSeries(series.toXYSeries());                            // number of series

        //customization of the chart

        XYSeriesRenderer renderer = new XYSeriesRenderer();     // one renderer for one series
        renderer.setColor(Color.RED);
        renderer.setLineWidth((float) 10.5d);
        
        XYMultipleSeriesRenderer mRenderer = new XYMultipleSeriesRenderer();   // collection multiple values for one renderer or series
        mRenderer.addSeriesRenderer(renderer);
        mRenderer.setChartTitle("Movies");
        mRenderer.setYTitle("Rating");
        mRenderer.setZoomButtonsVisible(true);    mRenderer.setShowLegend(true);

        mRenderer.setBarSpacing(.5);   // adding spacing between the line or stacks
        mRenderer.setApplyBackgroundColor(true);
        mRenderer.setBackgroundColor(Color.BLACK);
        mRenderer.setXAxisMin(0);
        mRenderer.setXAxisMax(5);
        mRenderer.setYAxisMax(50);

        mRenderer.setXLabels(0);
        for(int i=0;i<titles.size();i++){
            mRenderer.addXTextLabel(i+1,titles.get(i));
        }

        mRenderer.setPanEnabled(true, true);    // will fix the chart position
        Intent intent = ChartFactory.getBarChartIntent(context, dataSet, mRenderer,Type.DEFAULT);

        return intent;
    }
}
