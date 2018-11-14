package com.example.omarahmed.msecg;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class Piechart_Test extends AppCompatActivity implements OnChartValueSelectedListener {

    // TODO: Connect The Piechart to Line Chart to detuce the selected lead
    // TODO: COnnect the State OF Each Cycle

    private String[] leads_name= new String[]{"Lead I","Lead II","Lead III","AVR","AVF","AVL","V1","V2","V3","V4","V5","V6"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_piechart__test);
       /* Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);*/


        PieChart pieChart= (PieChart) findViewById(R.id.piechart);
        ArrayList<Entry> yvalues = new ArrayList<Entry>();
        yvalues.add(new Entry(8.3f, 0));
        yvalues.add(new Entry(8.3f, 1));
        yvalues.add(new Entry(8.3f, 2));
        yvalues.add(new Entry(8.3f, 3));
        yvalues.add(new Entry(8.3f, 4));
        yvalues.add(new Entry(8.3f, 5));
        yvalues.add(new Entry(8.3f, 6));
        yvalues.add(new Entry(8.3f, 7));
        yvalues.add(new Entry(8.3f, 9));
        yvalues.add(new Entry(8.3f, 10));
        yvalues.add(new Entry(8.3f, 11));
        yvalues.add(new Entry(8.3f, 12));
        PieDataSet dataSet = new PieDataSet(yvalues, "Elected Lead");

        ArrayList<String> xVals = new ArrayList<String>();

        for(int i =0;i<12;i++)
        {
        xVals.add(String.valueOf(leads_name[i]));
        }


        PieData data = new PieData(xVals, dataSet);
        data.setValueFormatter(new PercentFormatter());
        data.setValueTextSize( 20f);
        dataSet.setDrawValues(false);
        pieChart.setData(data);
        dataSet.setColors(ColorTemplate.VORDIPLOM_COLORS);
        pieChart.setDescription("Leads Selection");
        pieChart.setDrawHoleEnabled(true);
        pieChart.setTransparentCircleRadius(30f);
        pieChart.setHoleRadius(30f);
        pieChart.setOnChartValueSelectedListener(this);

        // TODO :   Line Chart










    }


    @Override
    public void onValueSelected(Entry e, int dataSetIndex, Highlight h) {

        Toast t=Toast.makeText(getApplicationContext(),"Selected Lead is :"+leads_name[dataSetIndex] ,Toast.LENGTH_SHORT);
        t.show();


    }

    @Override
    public void onNothingSelected() {

    }
}
