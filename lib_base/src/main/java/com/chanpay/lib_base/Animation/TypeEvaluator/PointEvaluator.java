package com.chanpay.lib_base.Animation.TypeEvaluator;

import android.animation.TypeEvaluator;
import android.util.Log;

/**
 * 文件名 : MyPointEvaluator
 * 创建者 : MangoWang
 * 创建日期 : 2019/6/20 14:03
 * 微信 : MangoWaWang
 * 邮箱 : 763482205@qq.com
 * 描述 : TODO
 */
public class PointEvaluator  implements TypeEvaluator {

    // 复写evaluate（）
    // 在evaluate（）里写入对象动画过渡的逻辑
    @Override
    public Object evaluate(float fraction, Object startValue, Object endValue) {

        Log.e("evaluate","fraction:"+fraction);  // 根据持续时间返回的值
        // 将动画初始值startValue 和 动画结束值endValue 强制类型转换成MyPoint对象
        MyPoint startMyPoint = (MyPoint) startValue;
        MyPoint endMyPoint = (MyPoint) endValue;

        // 根据fraction来计算当前动画的x和y的值
        float x = startMyPoint.getX() + fraction * (endMyPoint.getX() - startMyPoint.getX());
        float y = startMyPoint.getY() + fraction * (endMyPoint.getY() - startMyPoint.getY());

        // 将计算后的坐标封装到一个新的MyPoint对象中并返回
        MyPoint point = new MyPoint(x, y);
        return point;
    }

}