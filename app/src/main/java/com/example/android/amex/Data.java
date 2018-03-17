package com.example.android.amex;

import android.util.Log;

import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

/**
 * Created by Simra Afreen on 17-03-2018.
 */

public class Data {

    public static final int NUM_CATEGORIES = 5;
    public static final int HOUSE = 0;
    public static final int CAR = 1;
    public static final int CLOTHES = 2;
    public static final int COMMUNICATIONS = 3;
    public static final int FOOD = 4;
    public static final int ENTERTAINMENT = 5;
    public static final int TRANSPORT = 6;
    public static final int SPORTS = 7;
    public static final int HEALTH = 8;
    public static final int GIFTS = 9;

    public static final String[] categories = {"House","Car","Clothes","Communications","Food","Entertainment","Transport","Sports","Health","Gifts"};

    private float[] expense = new float[10];

    public Data(float expense[])
    {
        Log.i("Data","num: "+NUM_CATEGORIES);
        for(int i = 0; i<NUM_CATEGORIES; i++)
        {
            this.expense[i] = expense[i];
        }
    }

    public PieData getPieData()
    {

        ArrayList<Entry> yvalues = new ArrayList<Entry>();
        for(int i = 0; i<NUM_CATEGORIES; i++)
        {
            yvalues.add(new Entry(expense[i], i));

        }

        PieDataSet dataSet = new PieDataSet(yvalues, "Budget");
        dataSet.setColors(ColorTemplate.VORDIPLOM_COLORS);

        ArrayList<String> xVals = new ArrayList<String>();
        for(int i = 0; i<NUM_CATEGORIES ; i++)
        {
            xVals.add(categories[i]);
        }

        PieData data = new PieData(xVals, dataSet);
        // In percentage Term
        data.setValueFormatter(new PercentFormatter());

        return data;
    }


}
