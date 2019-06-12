package com.chanpay.lib_view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;

public class ProgressView extends BaseView {


    private Path mPath;
    private Paint mPaint;

    private ValueAnimator mAnimator;

    private float unit;
    private float strokeWith;
    private float space;


    public ProgressView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void init(Context context) {

        // 控件默认大小
        mWidth = dpToPx(414);
        mHeight = dpToPx(243);
        unit = (float) (mHeight * 0.086);
        strokeWith = (float) (mHeight * 0.040);
        space = (float) (mHeight * 0.100);


        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(strokeWith);
        mPath = new Path();

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawCoordinate(canvas);
        canvas.translate(mCenterX, mCenterY);
        drawBackGround(canvas, unit);
        drawBackGround(canvas, unit + space * 1);
        drawBackGround(canvas, unit + space * 2);
        drawBackGround(canvas, unit + space * 3);

        drawShapeColor(canvas,unit,"#7ECEF4",0.25);
        drawShapeColor(canvas,unit + space * 1,"#F9A3BD",0.50);
        drawShapeColor(canvas,unit + space * 2,"#84CCC9",0.75);
        drawShapeColor(canvas,unit + space * 3,"#F6B37F",1.00);

    }


    private void drawBackGround(Canvas canvas, float units) {
        Path path = new Path();
        path.moveTo(0, -units);
        path.lineTo(units, 0);
        path.lineTo(0, units);
        path.lineTo(-units, 0);
        path.close();
        mPaint.setColor(Color.parseColor("#E5E5E5"));
        canvas.drawPath(path, mPaint);
    }

    private void drawShapeColor(Canvas canvas, float units, String color, double present) {
        Path path = new Path();
        path.moveTo(0, -units);
        path.lineTo(units, 0);
        path.lineTo(0, units);
        path.lineTo(-units, 0);
        path.close();
        PathMeasure pathMeasure = new PathMeasure(path, false);
        float length = pathMeasure.getLength();
        Path shapePath = new Path();
        pathMeasure.getSegment(0, (float) (length * present), shapePath, true);
        mPaint.setColor(Color.parseColor(color));
        canvas.drawPath(shapePath, mPaint);

    }


}
