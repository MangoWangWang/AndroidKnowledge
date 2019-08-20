package com.chanpay.lib_view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;


/**
 * 文件名 : CricularProgressView
 * 创建者 : MangoWang
 * 创建日期 : 2019/7/5 14:08
 * 微信 : MangoWaWang
 * 邮箱 : 763482205@qq.com
 * 描述 : TODO
 */
public class CircularProgressPlusView extends BaseView {

    private Paint DecoratePaint;  // 装饰圆画笔
    private Paint FirstProgressPaint; // 第一进度条画笔
    private Paint SecondProgressPaint; // 第二进度条画笔

    private int ProgressFirstColor, ProgressSecondColor; //进度条颜色
    private int DecorateCircularColor; //装饰圆颜色

    private int sweepAngle = 180; // 默认角度为一半
    private int StartAngle = -90; // 默认角度为一半

    public CircularProgressPlusView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }


    private void initData(Context context, AttributeSet attrs) {


        // 默认颜色
        ProgressFirstColor = Color.parseColor("#FFBA00");
        ProgressSecondColor = Color.parseColor("#84CCC9");
        DecorateCircularColor = Color.parseColor("#F4EDDF");


        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.CircularProgressView);

        ProgressFirstColor = typedArray.getColor(R.styleable.CircularProgressView_ProgressFirstColor, ProgressFirstColor);
        ProgressSecondColor = typedArray.getColor(R.styleable.CircularProgressView_ProgressSecondColor, ProgressSecondColor);
        DecorateCircularColor = typedArray.getColor(R.styleable.CircularProgressView_DecorateCircularColor, DecorateCircularColor);

        typedArray.recycle();

    }

    @Override
    protected void init(Context context, AttributeSet attrs) {
        initData(context, attrs);
        mWidth = dpToPx(210);
        mHeight = dpToPx(210);


        // 初始化画笔
        DecoratePaint = new Paint();
        DecoratePaint.setAntiAlias(true);
        DecoratePaint.setStyle(Paint.Style.STROKE);
        DecoratePaint.setColor(DecorateCircularColor);


        // 第一进度条画笔
        FirstProgressPaint = new Paint();
        FirstProgressPaint.setAntiAlias(true);
        FirstProgressPaint.setStyle(Paint.Style.STROKE);
        FirstProgressPaint.setColor(ProgressFirstColor);

        // 第二进度条画笔
        SecondProgressPaint = new Paint();
        SecondProgressPaint.setAntiAlias(true);
        SecondProgressPaint.setStyle(Paint.Style.STROKE);
        SecondProgressPaint.setColor(ProgressSecondColor);


    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.translate(mCenterX, mCenterY);
        DecoratePaint.setStrokeWidth(mWidth * 0.01f);
        canvas.drawCircle(0, 0, mCenterX *0.92f, DecoratePaint);
//        DecoratePaint.setStrokeWidth(mWidth * 0.03f);
//        canvas.drawCircle(0, 0, mCenterX * 0.25f, DecoratePaint);


        SecondProgressPaint.setStrokeWidth(mWidth * 0.06f);
        canvas.drawCircle(0, 0, mCenterX * 0.75f, SecondProgressPaint);

        FirstProgressPaint.setStrokeWidth(mWidth * 0.1f);
        canvas.drawArc(-mCenterX * 0.75f, -mCenterY * 0.75f, mCenterX * 0.75f, mCenterY * 0.75f,
                StartAngle, sweepAngle, false, FirstProgressPaint);


    }

    public void setSweepAngle(int sweepAngle) {
        this.sweepAngle = sweepAngle;
        invalidate();
    }


    /**
     * 根据给定的宽和高进行拉伸
     *
     * @param origin 原图
     * @param scale  缩放比例
     * @return new Bitmap
     */
    private Bitmap scaleBitmap(Bitmap origin, float scale) {
        if (origin == null) {
            return null;
        }
        int height = origin.getHeight();
        int width = origin.getWidth();
        Matrix matrix = new Matrix();
        matrix.postScale(scale, scale);// 使用后乘
        Bitmap newBM = Bitmap.createBitmap(origin, 0, 0, width, height, matrix, false);
        if (!origin.isRecycled()) {
            origin.recycle();
        }
        return newBM;
    }
}
