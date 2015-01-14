package com.erz.mychart.charts.bar;

import com.erz.mychart.charts.DataSet;

/**
 * Created by edgarramirez on 1/9/15.
 */
public class BarSet extends DataSet {
    public BarSet(String title, float x, float y, int color) {
        setTitle(title);
        setX(x);
        setY(y);
        setColor(color);
    }

    @Override
    public int compareTo(DataSet dataSet) {
        return 0;
    }
}
