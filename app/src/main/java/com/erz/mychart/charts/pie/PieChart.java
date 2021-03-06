package com.erz.mychart.charts.pie;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.util.AttributeSet;

import com.erz.mychart.charts.ChartView;

/**
 * Created by edgarramirez on 1/9/15.
 */
public class PieChart extends ChartView<PieData> {
    Paint paint;
    Paint textPaint;
    private RectF circleRect;

    public PieChart(Context context) {
        super(context);
        init();
    }

    public PieChart(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public void init(){
        paint = new Paint();
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.STROKE);
        paint.setAntiAlias(true);
        textPaint = new Paint();
        textPaint.setStyle(Paint.Style.STROKE);
        textPaint.setColor(Color.WHITE);
        textPaint.setTextAlign(Paint.Align.CENTER);
        textPaint.setAntiAlias(true);
        textPaint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.NORMAL));

        circleRect = new RectF();
    }

    @Override
    public void draw(Canvas canvas){
        if(canvas != null){
            int width = canvas.getWidth();
            int height = canvas.getHeight();
            int min = Math.min(width, height);
            int circleWidth = min/10;
            textPaint.setTextSize(min/10);
            paint.setStrokeWidth(circleWidth);

            paint.setColor(Color.BLACK);
            circleRect.set((width/2)-(min/2)+circleWidth, (height/2)-(min/2)+circleWidth, (width/2)+(min/2)-circleWidth, (height/2)+(min/2)-circleWidth);
            canvas.drawCircle(width/2, height/2, min/2-circleWidth, paint);

            if(isLoading()){
                canvas.drawText("Loading...", width/2, height/2, textPaint);
            }else if(getData() != null && getData().size() > 0) {
                getData().draw(canvas, paint, circleRect);

                //draw text inside center of circle
                canvas.drawText(getTitle(), width/2, height/2, textPaint);
            }
        }
    }
}
