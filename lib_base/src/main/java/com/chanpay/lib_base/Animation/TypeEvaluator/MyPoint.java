package com.chanpay.lib_base.Animation.TypeEvaluator;

/**
 * 文件名 : MyPoint
 * 创建者 : MangoWang
 * 创建日期 : 2019/6/20 14:05
 * 微信 : MangoWaWang
 * 邮箱 : 763482205@qq.com
 * 描述 : TODO
 */
public class MyPoint {
    // 设置两个变量用于记录坐标的位置
    private float x;
    private float y;

    // 构造方法用于设置坐标
    public MyPoint(float x, float y) {
        this.x = x;
        this.y = y;
    }

    // get方法用于获取坐标
    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }
}
