package com.erz.mychart.charts;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;

import java.util.Vector;

/**
 * Created by edgarramirez on 1/9/15.
 */
public abstract class ChartData<SetType extends DataSet> extends Vector<SetType> {

    private float totalXValues, totalYValues, xMax, yMax, xMin, yMin;

    public abstract void draw(Canvas canvas, Paint paint, RectF rectF);

    public float getTotalXValues() {
        return totalXValues;
    }

    public void setTotalXValues(float totalXValues) {
        this.totalXValues = totalXValues;
    }

    public float getTotalYValues() {
        return totalYValues;
    }

    public void setTotalYValues(float totalYValues) {
        this.totalYValues = totalYValues;
    }

    public float getxMax() {
        return xMax;
    }

    public void setxMax(float xMax) {
        this.xMax = xMax;
    }

    public float getyMax() {
        return yMax;
    }

    public void setyMax(float yMax) {
        this.yMax = yMax;
    }

    public float getxMin() {
        return xMin;
    }

    public void setxMin(float xMin) {
        this.xMin = xMin;
    }

    public float getyMin() {
        return yMin;
    }

    public void setyMin(float yMin) {
        this.yMin = yMin;
    }

    @Override
    public boolean add(SetType set){
        totalXValues += set.getX();
        totalYValues += set.getY();
        if(set.getX() > xMax) xMax = set.getX();
        if(set.getX() < xMin) xMin = set.getX();
        if(set.getY() > yMax) yMax = set.getY();
        if(set.getY() < yMin) yMin = set.getY();
        return super.add(set);
    }
}