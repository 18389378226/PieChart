package com.idtk.smallchart.render;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;

import com.idtk.smallchart.data.PointData;

/**
 * Created by Idtk on 2016/6/8.
 * Blog : http://www.idtkm.com
 * GitHub : https://github.com/Idtk
 */
public class PointRender extends Render {

    private PointF mPointF = new PointF();
    private Paint mPaint = new Paint();

    public PointRender() {
        super();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setTextSize(30);
    }

    public void drawCirclePoint(Canvas canvas, PointF pointF, PointData pointData){
        mPointF.x = pointF.x;
        mPointF.y = -pointF.y;
        canvas.drawCircle(pointF.x,pointF.y,pointData.getOutRadius(),pointData.getOutPaint());
        canvas.drawCircle(pointF.x,pointF.y,pointData.getInRadius(),pointData.getInPaint());
//        canvas.save();
//        canvas.scale(1,-1);
//        drawGraph(canvas);
//        canvas.restore();
    }

    @Override
    public void drawGraph(Canvas canvas) {
        textCenter(new String[]{"("+mPointF.x+" , "+mPointF.y+")"},mPaint,canvas,mPointF, Paint.Align.CENTER);
    }

}
