package com.erz.mychart.charts.pie;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;

import com.erz.mychart.charts.ChartData;
import com.erz.mychart.charts.DataSet;

/**
 * Created by edgarramirez on 1/9/15.
 */
public class PieData extends ChartData<PieSet> {
    public void draw(Canvas canvas, Paint paint, RectF rectF){
        float currentAngle = 0;
        for (DataSet set: this){
            float percentage = (set.xValue /totalXValues) * 100;
            float angle = (360 * percentage) / 100;
            paint.setColor(set.color);
            canvas.drawArc(rectF, 270 + currentAngle, angle, false, paint);
            currentAngle += angle;
        }
    }
}
