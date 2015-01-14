package com.erz.mychart.charts.pie;

import com.erz.mychart.charts.DataSet;

/**
 * Created by edgarramirez on 1/9/15.
 */
public class PieSet extends DataSet {
    public PieSet(String title, float x, int color) {
        setTitle(title);
        setX(x);
        setColor(color);
    }

    @Override
    public int compareTo(DataSet dataSet) {
        return 0;
    }
}