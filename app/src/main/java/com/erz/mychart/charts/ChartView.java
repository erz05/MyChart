package com.erz.mychart.charts;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import java.util.Collections;

/**
 * Created by edgarramirez on 1/9/15.
 */
public abstract class ChartView<DataType extends ChartData> extends View {

    private String title = "";
    private DataType data;
    private boolean loading = true;

    public ChartView(Context context) {
        super(context);
    }

    public ChartView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public boolean isLoading() {
        return loading;
    }

    public void setLoading(boolean loading) {
        this.loading = loading;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public DataType getData() {
        return data;
    }

    public void setData(DataType data) {
        this.data = data;
        Collections.sort(this.data);
        loading = false;
        invalidate();
    }

    public void reset(){
        loading = true;
        data = null;
        invalidate();
    }
}
