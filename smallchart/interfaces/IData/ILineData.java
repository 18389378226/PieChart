package com.idtk.smallchart.interfaces.IData;

import com.idtk.smallchart.data.LineData;

/**
 * Created by Idtk on 2016/6/7.
 * Blog : http://www.idtkm.com
 * GitHub : https://github.com/Idtk
 * 描述 : 折线图数据类接口
 */
public interface ILineData extends IBarLineCurveData {

    /**
     * 设置折线图动画模式
     * @param animatedMod 动画模式
     */
    void setAnimatedMod(LineData.AnimatedMod animatedMod);

    /**
     * 获取折线图动画模式
     * @return 动画模式
     */
    LineData.AnimatedMod getAnimatedMod();
}
