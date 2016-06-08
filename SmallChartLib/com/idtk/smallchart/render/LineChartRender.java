package com.idtk.smallchart.render;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;

import com.idtk.smallchart.data.LineData;
import com.idtk.smallchart.data.PointData;
import com.idtk.smallchart.data.XAxisData;
import com.idtk.smallchart.data.YAxisData;

import java.util.ArrayList;

/**
 * Created by Idtk on 2016/6/6.
 * Blog : http://www.idtkm.com
 * GitHub : https://github.com/Idtk
 */
public class LineChartRender extends BarLineCurveRender<LineData> {

    private LineData mLineData;
    private Path mPath = new Path();
    private Paint linePaint = new Paint();
    private float animatedValue;
    private XAxisData xAxisData = new XAxisData();
    private YAxisData yAxisData = new YAxisData();
    private ArrayList<PointF> pointList = new ArrayList<>();
    private PointRender mPointRender = new PointRender();
    private PointData pointData;

    /**
     * 测试使用，点需分单独模块
     */
    private Paint outpointPaint = new Paint();
    private Paint inPointPaint = new Paint();

    public LineChartRender(XAxisData xAxisData, YAxisData yAxisData, PointData pointData) {
        this(null,0,xAxisData,yAxisData,pointData);
    }

    public LineChartRender(LineData lineData, float animatedValue, XAxisData xAxisData, YAxisData yAxisData, PointData pointData) {
        super();
        mLineData = lineData;
        this.animatedValue = animatedValue;
        this.xAxisData = xAxisData;
        this.yAxisData = yAxisData;
        this.pointData = pointData;
        linePaint.setStyle(Paint.Style.STROKE);
        linePaint.setAntiAlias(true);
        outpointPaint.setStyle(Paint.Style.FILL);
        outpointPaint.setAntiAlias(true);
        inPointPaint.setStyle(Paint.Style.FILL);
        inPointPaint.setAntiAlias(true);

    }


    @Override
    public void drawGraph(Canvas canvas) {
        if (mLineData != null)
        this.drawGraph(canvas,mLineData,animatedValue);
    }

    @Override
    public void drawGraph(Canvas canvas, LineData lineData, float animatedValue) {
//        canvas.drawText("LineChartRender",0,0,paint);
        linePaint.setStrokeWidth(lineData.getPaintWidth());
        outpointPaint.setStrokeWidth(linePaint.getStrokeWidth());
        inPointPaint.setStrokeWidth(linePaint.getStrokeWidth());

        mPath.incReserve(lineData.getValue().size());//为添加更多点准备路径,可以更有效地分配其存储的路径
        pointList.clear();
        for (int j=0; j< lineData.getValue().size(); j++){
            float currentYAxis;
            if (animatedValue<yAxisData.getAxisLength()/2){
                currentYAxis = animatedValue;
            }else {
                if ((lineData.getValue().get(j).y-yAxisData.getMinimum())*yAxisData.getAxisScale() >= yAxisData.getAxisLength()/2){
                    switch (lineData.getAnimatedMod()){
                        case SYNC://同时到达
                            currentYAxis = yAxisData.getAxisLength()/2+((lineData.getValue().get(j).y-yAxisData.getMinimum())*yAxisData.getAxisScale()
                                    -yAxisData.getAxisLength()/2)/yAxisData.getAxisLength()*2*(animatedValue-yAxisData.getAxisLength()/2);
                            break;
                        default://先后到达
                            currentYAxis = Math.min(animatedValue,
                                    (lineData.getValue().get(j).y-yAxisData.getMinimum())*yAxisData.getAxisScale());
                            break;
                    }
                }else {
                    switch (lineData.getAnimatedMod()){
                        case SYNC:
                            currentYAxis = yAxisData.getAxisLength()/2-(yAxisData.getAxisLength()/2-
                                    (lineData.getValue().get(j).y-yAxisData.getMinimum())*yAxisData.getAxisScale())/
                                    yAxisData.getAxisLength()*2*(animatedValue-yAxisData.getAxisLength()/2);
                            break;
                        default:
                            currentYAxis = Math.max(yAxisData.getAxisLength()-animatedValue,
                                    (lineData.getValue().get(j).y-yAxisData.getMinimum())*yAxisData.getAxisScale());
                            break;
                    }
                }
            }
            if (j==0){
                mPath.moveTo((lineData.getValue().get(j).x-xAxisData.getMinimum())*xAxisData.getAxisScale(), currentYAxis);
            }else {
                mPath.lineTo((lineData.getValue().get(j).x-xAxisData.getMinimum())*xAxisData.getAxisScale(), currentYAxis);
            }
            /**
             * 保存
             */
            pointList.add(new PointF((lineData.getValue().get(j).x-xAxisData.getMinimum())*xAxisData.getAxisScale(),currentYAxis));
        }
        linePaint.setColor(lineData.getColor());
        canvas.drawPath(mPath,linePaint);
        mPath.rewind();//清除

        outpointPaint.setColor(lineData.getColor());
        inPointPaint.setColor(Color.WHITE);
        pointData.setInPaint(inPointPaint);
        pointData.setOutPaint(outpointPaint);

        for (int j=0; j<pointList.size(); j++) {
            mPointRender.drawCirclePoint(canvas, pointList.get(j),pointData);
        }
    }
}
