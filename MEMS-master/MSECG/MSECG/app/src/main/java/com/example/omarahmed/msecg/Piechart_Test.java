package com.example.omarahmed.msecg;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.animation.Animation;
import android.widget.Toast;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.listener.ChartTouchListener;
import com.github.mikephil.charting.listener.OnChartGestureListener;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.Random;

public class Piechart_Test extends AppCompatActivity implements OnChartValueSelectedListener,OnChartGestureListener
         {

    // TODO: Connect The Piechart to Line Chart to detuce the selected lead
    // TODO: COnnect the State OF Each Cycle
    // TODO: THe PieChart


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
        yvalues.add(new Entry(8.3f, 8));
        yvalues.add(new Entry(8.3f, 9));
        yvalues.add(new Entry(8.3f, 10));
        yvalues.add(new Entry(8.3f, 11));

        PieDataSet dataSet = new PieDataSet(yvalues, "Selected Lead");

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
        pieChart.setDrawHoleEnabled(true);                  // TODO : Turning on hole of draw
        pieChart.setTransparentCircleRadius(30f);
        pieChart.setHoleRadius(30f);
        pieChart.setOnChartValueSelectedListener(this);

        // TODO :   Line Chart

        mChart = (LineChart) findViewById(R.id.lead_line_chart);
        mChart.setOnChartGestureListener(this);
        mChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, int dataSetIndex, Highlight h) {
                Log.i("Entry selected", e.toString());
                Log.i("LOWHIGH", "low: " + mChart.getLowestVisibleXIndex()
                        + ", high: " + mChart.getHighestVisibleXIndex());

                Log.i("MIN MAX", "xmin: " + mChart.getXChartMin()
                        + ", xmax: " + mChart.getXChartMax()
                        + ", ymin: " + mChart.getYChartMin()
                        + ", ymax: " + mChart.getYChartMax());
            }

            @Override
            public void onNothingSelected() {

            }
        });
        mChart.setDrawGridBackground(false);

       // setData();
        Legend l = mChart.getLegend();

        // modify the legend ...
        // l.setPosition(LegendPosition.LEFT_OF_CHART);
        l.setForm(Legend.LegendForm.SQUARE);

        // no description text
        mChart.setDescription("Lead Chart");
        mChart.setNoDataTextDescription("select lead  from PieChart on Top");

        // enable touch gestures
        mChart.setTouchEnabled(true);

        // enable scaling and dragging
        mChart.setDragEnabled(true);
        mChart.setScaleEnabled(true);
        // mChart.setScaleXEnabled(true);
        // mChart.setScaleYEnabled(true);

       /* LimitLine upper_limit = new LimitLine(130f, "Upper Limit");
        upper_limit.setLineWidth(4f);
        upper_limit.enableDashedLine(10f, 10f, 0f);
        upper_limit.setLabelPosition(LimitLine.LimitLabelPosition.RIGHT_TOP);
        upper_limit.setTextSize(10f);

        LimitLine lower_limit = new LimitLine(-30f, "Lower Limit");
        lower_limit.setLineWidth(4f);
        lower_limit.enableDashedLine(10f, 10f, 0f);
        lower_limit.setLabelPosition(LimitLine.LimitLabelPosition.RIGHT_BOTTOM);
        lower_limit.setTextSize(10f);*/

        YAxis leftAxis = mChart.getAxisLeft();
        leftAxis.removeAllLimitLines(); // reset all limit lines to avoid overlapping lines
        leftAxis.setAxisMaxValue(50f);
        leftAxis.setAxisMinValue(-50f);
        //leftAxis.setYOffset(20f);
        leftAxis.enableGridDashedLine(0f, 0f, 0f);

        // limit lines are drawn behind data (and not on top)
        //leftAxis.setDrawLimitLinesBehindData(true);

        mChart.getAxisRight().setEnabled(false);
        mChart.getXAxis().setDrawLabels(false);          //Hide x values

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

    private ArrayList<String> setXAxisValues(){
        ArrayList<String> xVals = new ArrayList<String>();
        for(int i =0;i<100;i++)
        {
            xVals.add(String.valueOf(i));
        }


        return xVals;
    }

    private ArrayList<Entry> setYAxisValues(){
        ArrayList<Entry> yVals = new ArrayList<Entry>();
        Random r=new Random();
        for(int i =0;i<100;i++)
        {
            yVals.add(new Entry(r.nextInt(100)-50,i));
        }
       /* yVals.add(new Entry(50, 0));
        yVals.add(new Entry(20, 1));
        yVals.add(new Entry(10.5f, 2));
        yVals.add(new Entry(-5, 3));
        yVals.add(new Entry(-50.9f, 4));*/


        return yVals;
    }

    private void setData() {
        ArrayList<String> xVals = setXAxisValues();

        ArrayList<Entry> yVals = setYAxisValues();

        LineDataSet set1;

        // create a dataset and give it a type
        set1 = new LineDataSet(yVals, "Leads Gragh");
        set1.setFillAlpha(110);
        // set1.setFillColor(Color.RED);

        // set the line to be drawn like this "- - - - - -"
        //   set1.enableDashedLine(10f, 5f, 0f);
        // set1.enableDashedHighlightLine(10f, 5f, 0f);
        set1.setColor(Color.BLACK);
        set1.setCircleColor(Color.BLACK);
        set1.setDrawCircles(false);
        set1.setLineWidth(1f);
        set1.setDrawCircleHole(false);
        set1.setValueTextSize(9f);
        set1.setDrawFilled(true);                //TODO :  Fillied chart


        ArrayList<ILineDataSet> dataSets = new ArrayList<ILineDataSet>();
        dataSets.add(set1); // add the datasets

        // create a data object with the datasets
        LineData data = new LineData(xVals, dataSets);

        // set data
        mChart.setData(data);
        mChart.animateX(5000, Easing.EasingOption.EaseInOutQuart);
        mChart.invalidate();     // to refresh the chart   to update the chart

    }


    @Override
    public void onChartGestureStart(MotionEvent me,
                                    ChartTouchListener.ChartGesture
                                            lastPerformedGesture) {

        Log.i("Gesture", "START, x: " + me.getX() + ", y: " + me.getY());
    }

    @Override
    public void onChartGestureEnd(MotionEvent me,
                                  ChartTouchListener.ChartGesture
                                          lastPerformedGesture) {

        Log.i("Gesture", "END, lastGesture: " + lastPerformedGesture);

        // un-highlight values after the gesture is finished and no single-tap
        if(lastPerformedGesture != ChartTouchListener.ChartGesture.SINGLE_TAP)
            // or highlightTouch(null) for callback to onNothingSelected(...)
            mChart.highlightValues(null);
    }

    @Override
    public void onChartLongPressed(MotionEvent me) {
        Log.i("LongPress", "Chart longpressed.");
    }

    @Override
    public void onChartDoubleTapped(MotionEvent me) {
        Log.i("DoubleTap", "Chart double-tapped.");
    }

    @Override
    public void onChartSingleTapped(MotionEvent me) {
        Log.i("SingleTap", "Chart single-tapped.");
    }

    @Override
    public void onChartFling(MotionEvent me1, MotionEvent me2,
                             float velocityX, float velocityY) {
        Log.i("Fling", "Chart flinged. VeloX: "
                + velocityX + ", VeloY: " + velocityY);
    }

    @Override
    public void onChartScale(MotionEvent me, float scaleX, float scaleY) {
        Log.i("Scale / Zoom", "ScaleX: " + scaleX + ", ScaleY: " + scaleY);
    }

    @Override
    public void onChartTranslate(MotionEvent me, float dX, float dY) {
        Log.i("Translate / Move", "dX: " + dX + ", dY: " + dY);
    }




    @Override
    public void onNothingSelected() {
        Log.i("Nothing selected", "Nothing selected.");
        mChart.clear();
    }



    @Override
    public void onValueSelected(Entry e, int dataSetIndex, Highlight h) {

        Toast t=Toast.makeText(getApplicationContext(),"Selected Lead is : "+ leads_name[e.getXIndex()] ,Toast.LENGTH_SHORT);
        t.show();
        mChart.clear();
        setData();



    }


}
