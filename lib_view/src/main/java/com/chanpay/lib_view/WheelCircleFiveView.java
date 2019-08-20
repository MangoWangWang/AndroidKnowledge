package com.chanpay.lib_view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class WheelCircleFiveView extends View {
    private final String TAG = getClass().getSimpleName();

    private int mWidth = dp2Px(48), mHeight = dp2Px(48), mCenterX, mCenterY;

    // 默认刻度画笔、指针画笔、文字画笔；
    private Paint mDefaultPaint;
    private Paint mTextPaint;

    private CircleBarAnim circleBarAnim;

    private float mRadius, mPointRadius,
            mDefaultScaleLength, mDefaultScaleWidth;

    private int mOneColor, twoColor, mTextColor;
    private int mThreeColor, mFourColor, mFiveColor;
    private float sweepTime = 0;
    private int times;
    private int precentOne = 8, precentTwo = 15;
    private int precentThree = 22, precentFour = 29;
    private int precentFive = 36;
    private String PrecentText = "0%";
    private float textSize = 0;
    private boolean hasText;


    public WheelCircleFiveView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        // 获取自定义属性值
        getAttrs(context, attrs);
        // 初始化
        init();
    }

    private void getAttrs(Context context, AttributeSet attrs) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.wheelFiveView);
        mOneColor = a.getColor(R.styleable.wheelFiveView_OneCircleColor, Color.BLACK);
        twoColor = a.getColor(R.styleable.wheelFiveView_TwoCircleColor, mOneColor);
        mThreeColor = a.getColor(R.styleable.wheelFiveView_ThreeCircleColor, mOneColor);
        mFourColor = a.getColor(R.styleable.wheelFiveView_FourCircleColor, mOneColor);
        mFiveColor = a.getColor(R.styleable.wheelFiveView_FiveCircleColor, mOneColor);
        mTextColor = a.getColor(R.styleable.wheelFiveView_clockViewTextColor, mOneColor);
        hasText = a.getBoolean(R.styleable.wheelFiveView_hasText, false);
        times = a.getInt(R.styleable.wheelFiveView_animationTime, 3000);
        textSize = a.getDimension(R.styleable.wheelFiveView_clockViewTextSize, dp2Px(15));
        a.recycle();//不要忘记回收！
    }

    private void init() {
        circleBarAnim = new CircleBarAnim();
        circleBarAnim.setDuration(times);


        mDefaultPaint = new Paint();
        mDefaultPaint.setAntiAlias(true);
        mDefaultPaint.setStyle(Paint.Style.STROKE);
        mDefaultPaint.setStrokeCap(Paint.Cap.ROUND);

        mTextPaint = new Paint();
        mTextPaint.setAntiAlias(true);
        mTextPaint.setColor(mTextColor);
        mTextPaint.setStyle(Paint.Style.STROKE);
        mTextPaint.setTextSize(textSize);

    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
        mCenterX = w / 2;
        mCenterY = h / 2;
        mRadius = (float) (w / 2 * 0.8);

        initClockPointerLength();
    }

    /**
     * 根据控件的大小，初始化时钟刻度的长度和宽度、指针的长度和宽度、时钟中心点的半径
     */
    private void initClockPointerLength() {


        mDefaultScaleLength = mRadius / 3 + 5;
        mDefaultScaleWidth = mDefaultScaleLength / 5;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = getMeasureSize(true, widthMeasureSpec);
        int height = getMeasureSize(false, heightMeasureSpec);
        setMeasuredDimension(width, height);
    }

    /**
     * 获取View尺寸
     * 基本上算是标准写法
     *
     * @param isWidth 是否是width，不是的话，是height
     */
    private int getMeasureSize(boolean isWidth, int measureSpec) {

        int result = 0;

        int specSize = MeasureSpec.getSize(measureSpec);
        int specMode = MeasureSpec.getMode(measureSpec);

        switch (specMode) {
            case MeasureSpec.UNSPECIFIED:
                if (isWidth) {
                    result = getSuggestedMinimumWidth();
                } else {
                    result = getSuggestedMinimumHeight();
                }
                break;
            case MeasureSpec.AT_MOST:   // at_most中的specsize为子view可以拿到的最大值
                if (isWidth)
                    result = Math.min(specSize, mWidth);
                else
                    result = Math.min(specSize, mHeight);
                break;
            case MeasureSpec.EXACTLY:
                result = specSize;
                break;
        }
        return result;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // 坐标原点移动到View 中心
        canvas.translate(mCenterX, mCenterY);
        drawCircle(canvas);
    }

    /**
     * 绘制时钟的圆形和刻度
     */
    private void drawCircle(Canvas canvas) {
        if (hasText) {
            float textWidth = mTextPaint.measureText(PrecentText);
            float Textx = 0 - textWidth / 2;
            Paint.FontMetrics fontMetrics = mTextPaint.getFontMetrics();
            float dy = (fontMetrics.descent - fontMetrics.ascent) / 2 - fontMetrics.descent;
            float Texty = 0 + dy;
            canvas.drawText(PrecentText, Textx, Texty, mTextPaint);
        }
        mDefaultPaint.setStrokeWidth(mDefaultScaleWidth);
        for (int i = 0; i < sweepTime; i++) {
            if (i < precentOne) {
                mDefaultPaint.setColor(mOneColor);
                canvas.drawLine(0, -mRadius, 0, -mRadius + mDefaultScaleLength, mDefaultPaint);
                canvas.rotate(10);
            } else if (i < precentTwo) {
                mDefaultPaint.setColor(twoColor);
                canvas.drawLine(0, -mRadius, 0, -mRadius + mDefaultScaleLength, mDefaultPaint);
                canvas.rotate(10);
            } else if (i < precentThree) {
                mDefaultPaint.setColor(mThreeColor);
                canvas.drawLine(0, -mRadius, 0, -mRadius + mDefaultScaleLength, mDefaultPaint);
                canvas.rotate(10);
            } else if (i < precentFour) {
                mDefaultPaint.setColor(mFourColor);
                canvas.drawLine(0, -mRadius, 0, -mRadius + mDefaultScaleLength, mDefaultPaint);
                canvas.rotate(10);
            } else if (i < precentFive) {
                mDefaultPaint.setColor(mFiveColor);
                canvas.drawLine(0, -mRadius, 0, -mRadius + mDefaultScaleLength, mDefaultPaint);
                canvas.rotate(10);
            }
        }

    }


    private int dp2Px(int dp) {
        final float scale = getContext().getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }


    public class CircleBarAnim extends Animation {
        public CircleBarAnim() {
        }

        @Override
        protected void applyTransformation(float interpolatedTime, Transformation t) {
            super.applyTransformation(interpolatedTime, t);
            sweepTime = interpolatedTime * 36;
            postInvalidate();
        }
    }

    public void starAnim() {
        sweepTime = 0;
        this.startAnimation(circleBarAnim);
    }


    public void setDataDan(int one, int two, int three, int four, int five, int total) {
        if (total != 0) {

            double fone = (double) one / total * 36;
            double ftwo = (double) two / total * 36;
            double fthree = (double) three / total * 36;
            double ffour = (double) four / total * 36;
            double ffive = (double) five / total * 36;


            int done = (int) Math.floor(fone);
            int dtwo = (int) Math.floor(ftwo);
            int dthree = (int) Math.floor(fthree);
            int dfour = (int) Math.floor(ffour);
            int dfive = (int) Math.floor(ffive);

            int chazhi = 36 - (done + dtwo + dthree + dfour + dfive);

            if (chazhi > 0) {
                List<Integer> integers = new ArrayList<>();
                integers.add(done);
                integers.add(dtwo);
                integers.add(dthree);
                integers.add(dfour);
                integers.add(dfive);
                int max = getMaxNum(integers);
                if (max == done) {
                    done = done + chazhi;
                } else if (max == dtwo) {
                    dtwo = dtwo + chazhi;
                } else if (max == dthree) {
                    dthree = dtwo + chazhi;
                } else if (max == dfour) {
                    dfour = dfour + chazhi;
                } else if (max == dfive) {
                    dfive = dfive + chazhi;
                }
            }


            precentOne = done;
            precentTwo = dtwo + done;
            precentThree = dtwo + done + dthree;
            precentFour = dtwo + done + dthree + dfour;
            precentFive = dtwo + done + dthree + dfour+ dfive;
        }
        starAnim();
    }

    public Integer getMaxNum(List<Integer> list) {
        Collections.sort(list, new Comparator<Integer>() {
            public int compare(Integer num1, Integer num2) {
                if (num1 > num2)
                    return 1;
                else if (num1 < num2)
                    return -1;
                return 0;
            }
        });
        return list.get(0);
    }
}
