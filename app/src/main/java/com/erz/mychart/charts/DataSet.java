package com.erz.mychart.charts;

/**
 * Created by edgarramirez on 1/9/15.
 */
public abstract class DataSet implements Comparable<DataSet> {
    public String title;
    public float xValue, yValue;
    public int color;
}