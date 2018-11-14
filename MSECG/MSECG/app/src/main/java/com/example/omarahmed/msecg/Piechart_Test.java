package com.example.omarahmed.msecg;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.widget.Toast;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartGestureListener;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class Piechart_Test extends AppCompatActivity implements OnChartValueSelectedListener,OnChartGestureListener,
        OnChartValueSelectedListener {

    // TODO: Connect The Piechart to Line Chart to detuce the selected lead
    // TODO: COnnect the State OF Each Cycle

    private String[] leads_name= new String[]{"Lead I","Lead II","Lead III","AVR","AVF","AVL","V1","V2","V3","V4","V5","V6"};
    private LineChart mChart;


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

        mChart = (LineChart) findViewById(R.id.line_chart);
        mChart.setOnChartGestureListener(this);
        mChart.setOnChartValueSelectedListener(this);
        mChart.setDrawGridBackground(false);

        setData();
        Legend l = mChart.getLegend();

        // modify the legend ...
        // l.setPosition(LegendPosition.LEFT_OF_CHART);
        l.setForm(Legend.LegendForm.LINE);

        // no description text
        mChart.setDescription("Lead Chart");
        mChart.setNoDataTextDescription("You need to provide data for the chart.");

        // enable touch gestures
        mChart.setTouchEnabled(true);

        // enable scaling and dragging
        mChart.setDragEnabled(true);
        mChart.setScaleEnabled(true);
        // mChart.setScaleXEnabled(true);
        // mChart.setScaleYEnabled(true);

        LimitLine upper_limit = new LimitLine(130f, "Upper Limit");
        upper_limit.setLineWidth(4f);
        upper_limit.enableDashedLine(10f, 10f, 0f);
        upper_limit.setLabelPosition(LimitLine.LimitLabelPosition.RIGHT_TOP);
        upper_limit.setTextSize(10f);

        LimitLine lower_limit = new LimitLine(-30f, "Lower Limit");
        lower_limit.setLineWidth(4f);
        lower_limit.enableDashedLine(10f, 10f, 0f);
        lower_limit.setLabelPosition(LimitLine.LimitLabelPosition.RIGHT_BOTTOM);
        lower_limit.setTextSize(10f);

        YAxis leftAxis = mChart.getAxisLeft();
        leftAxis.removeAllLimitLines(); // reset all limit lines to avoid overlapping lines
        leftAxis.addLimitLine(upper_limit);
        leftAxis.addLimitLine(lower_limit);
        leftAxis.setAxisMaxValue(220f);
        leftAxis.setAxisMinValue(-50f);
        //leftAxis.setYOffset(20f);
        leftAxis.enableGridDashedLine(10f, 10f, 0f);

        // limit lines are drawn behind data (and not on top)
        leftAxis.setDrawLimitLinesBehindData(true);

        mChart.getAxisRight().setEnabled(false);

        //mChart.getViewPortHandler().setMaximumScaleY(2f);
        //mChart.getViewPortHandler().setMaximumScaleX(2f);

        mChart.animateX(5000, Easing.EasingOption.EaseInOutQuart);
        mChart.setLayoutAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        //  dont forget to refresh the drawing
        mChart.invalidate();







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
