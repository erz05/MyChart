package com.erz.mychart;

import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;

import com.erz.mychart.charts.line.LineChart;
import com.erz.mychart.charts.line.LineData;
import com.erz.mychart.charts.line.LineSet;

public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LineChart lineChart = (LineChart) findViewById(R.id.lineChart);
        LineData data = new LineData();
        //data.add(new LineSet("title", 3, 5, Color.RED));
        data.add(new LineSet("title", 2, 2, Color.RED));
        //data.add(new LineSet("title", 1, 5, Color.RED));
        //data.add(new LineSet("title", 8, 5, Color.RED));
        data.add(new LineSet("title", 8, 10, Color.RED));
        //data.add(new LineSet("title", 1, 8, Color.RED));
        lineChart.setData(data);
    }
}
