package com.erz.mychart.charts.line;

import com.erz.mychart.charts.DataSet;

/**
 * Created by edgarramirez on 1/9/15.
 */
public class LineSet extends DataSet {
    public LineSet(String title, float x, float y, int color) {
        setTitle(title);
        setX(x);
        setY(y);
        setColor(color);
    }

    @Override
    public int compareTo(DataSet dataSet) {
        return Float.compare(getX(), dataSet.getX());
    }
}
