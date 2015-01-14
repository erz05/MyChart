package com.erz.mychart.charts;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;

import java.util.Vector;

/**
 * Created by edgarramirez on 1/9/15.
 */
public abstract class ChartData<SetType extends DataSet> extends Vector<SetType> {

    public float totalXValues, totalYValues, xMax, yMax, xMin, yMin;

    public abstract void draw(Canvas canvas, Paint paint, RectF rectF);

    @Override
    public boolean add(SetType set){
        totalXValues += set.xValue;
        totalYValues += set.yValue;
        if(set.xValue > xMax) xMax = set.xValue;
        if(set.xValue < xMin) xMin = set.xValue;
        if(set.yValue > yMax) yMax = set.yValue;
        if(set.yValue < yMin) yMin = set.yValue;
        return super.add(set);
    }
}