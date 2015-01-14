package com.erz.mychart.charts.line;

import com.erz.mychart.charts.DataSet;

/**
 * Created by edgarramirez on 1/9/15.
 */
public class LineSet extends DataSet {
    public LineSet(String title, float xValue, float yValue, int color) {
        this.title = title;
        this.xValue = xValue;
        this.yValue = yValue;
        this.color = color;
    }

    @Override
    public int compareTo(DataSet dataSet) {
        return Float.compare(xValue, dataSet.xValue);
    }
}
