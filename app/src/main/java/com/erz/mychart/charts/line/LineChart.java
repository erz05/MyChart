package com.erz.mychart.charts.line;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.util.AttributeSet;

import com.erz.mychart.charts.ChartView;
import com.erz.mychart.charts.DataSet;

/**
 * Created by edgarramirez on 1/9/15.
 */
public class LineChart extends ChartView<LineData> {
    Paint paint;
    Paint linePaint;
    Paint textPaint;
    Paint gridPaint;
    private RectF circleRect;
    public LineChart(Context context) {
        super(context);
        init();
    }

    public LineChart(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public void init(){
        paint = new Paint();
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        paint.setDither(true);
        linePaint = new Paint();
        linePaint.setColor(Color.RED);
        linePaint.setStyle(Paint.Style.STROKE);
        linePaint.setAntiAlias(true);
        linePaint.setDither(true);
        gridPaint = new Paint();
        gridPaint.setColor(Color.BLUE);
        gridPaint.setStyle(Paint.Style.STROKE);
        gridPaint.setAntiAlias(true);
        gridPaint.setDither(true);
        textPaint = new Paint();
        textPaint.setStyle(Paint.Style.STROKE);
        textPaint.setColor(Color.BLACK);
        textPaint.setTextAlign(Paint.Align.CENTER);
        textPaint.setAntiAlias(true);
        textPaint.setDither(true);
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
            //paint.setStrokeWidth(circleWidth);
            gridPaint.setStrokeWidth(5);

            circleRect.set((width/2)-(min/2)+circleWidth, (height/2)-(min/2)+circleWidth, (width/2)+(min/2)-circleWidth, (height/2)+(min/2)-circleWidth);

            LineData data = getData();

            if(isLoading()){
                canvas.drawText("Loading ...", width/2, height/2, textPaint);
            }else if(data != null && data.size() > 0) {
                float segMin = Math.min(data.getxMax(), data.getyMax());
                int seg = 15;
                if(segMin < 15) seg = (int)segMin;

                float spacing = min/(seg+1);
                float xSpacing = width/(seg+1);
                float ySpacing = height/(seg+1);

                textPaint.setTextSize(spacing/2);
                canvas.drawLine(0, height-spacing, width, height-spacing, textPaint);
                canvas.drawLine(spacing, 0, spacing, height, textPaint);
                canvas.drawText("0", spacing/2, height-spacing/2+textPaint.getTextSize()/2, textPaint);

                float startX = xSpacing;
                float startY = height - ySpacing;

                float xIncrements = data.getxMax()/seg;
                float yIncrements = data.getyMax()/seg;

                float xCount = xIncrements;
                float yCount = yIncrements;
                for(int i=0; i<seg; i++){
                    canvas.drawText(""+(int)xCount, startX+spacing/2, height-spacing/2+textPaint.getTextSize()/2, textPaint);
                    canvas.drawText(""+(int)yCount, spacing/2, startY-spacing/2+textPaint.getTextSize()/2, textPaint);

                    canvas.drawLine(0, startY-spacing/2, width, startY-spacing/2, gridPaint);
                    canvas.drawLine(startX+spacing/2, 0, startX+spacing/2, height, gridPaint);

                    xCount += xIncrements;
                    startX += xSpacing;
                    yCount += yIncrements;
                    startY -= ySpacing;
                }

                float radius = spacing/4;
                DataSet previous = null;
                for(DataSet set: data){
                    float xValue = ((set.getX()*xSpacing)/xIncrements)+spacing - (radius*2);
                    float yValue = (height - spacing) - ((set.getY()*ySpacing)/yIncrements) + (radius*2);
                    if(xValue < spacing) xValue = spacing;
                    if(yValue > (height-spacing)) yValue = height-spacing;
                    canvas.drawCircle(xValue, yValue, radius, paint);

                    if(previous != null){
                        float xPrev = ((previous.getX()*xSpacing)/xIncrements)+spacing - (radius*2);
                        float yPrev = (height - spacing) - ((previous.getY()*ySpacing)/yIncrements) + (radius*2);
                        if(xPrev < spacing) xPrev = spacing;
                        if(yPrev > (height-spacing)) yPrev = height-spacing;
                        canvas.drawLine(xPrev, yPrev, xValue, yValue, paint);
                    }previous = set;
                }
            }else {
                canvas.drawText("Not Enough Data ...", width/2, height/2, textPaint);
            }
        }
    }
}
