package com.erz.mychart.charts;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by edgarramirez on 1/9/15.
 */
public abstract class ChartView<DataType extends ChartData> extends View {

    protected String title = "";
    protected DataType data;
    protected boolean loading = true;

    public ChartView(Context context) {
        super(context);
    }

    public ChartView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void reset(){
        loading = true;
        data = null;
        invalidate();
    }

    public void ready(DataType data){
        loading = false;

        this.data = data;

        invalidate();
    }

    public void setTitle(String title){
        this.title = title;
    }
}
