package com.erz.mychart.charts.bar;

import com.erz.mychart.charts.DataSet;

/**
 * Created by edgarramirez on 1/9/15.
 */
public class BarSet extends DataSet {
    public BarSet(String title, float xValue, float yValue, int color) {
        this.title = title;
        this.xValue = xValue;
        this.yValue = yValue;
        this.color = color;
    }

    @Override
    public int compareTo(DataSet dataSet) {
        return 0;
    }
}
