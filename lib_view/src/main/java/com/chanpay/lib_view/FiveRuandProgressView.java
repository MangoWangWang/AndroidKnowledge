package com.chanpay.lib_view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.Toast;

import com.chanpay.lib_view.BaseView;
import com.chanpay.lib_view.R;

import java.util.List;


/*四边形进度条*/
public class FiveRuandProgressView extends BaseView {

    private Paint mPaint;
    private Paint mCirclePaint;
    private Paint paintText;
    private Paint mLinePaint;

    private ValueAnimator mAnimator;

    private float unit;  // 默认起点
    private float strokeWith; // 线条宽度
    private float space;  // 线条间距
    private int ProgressBgColor, ProgressOneColor, ProgressTwoColor, ProgressThreeColor, ProgressFourColor, ProgressFiveColor;//进度颜色
    private int textColor, lineColor;  // 文字颜色，线条颜色
    private String typeOneName, typeTwoName, typeThreeName, typeFourName;  // 类型名称
    private String typeOneNumber, typeTwoNumber, typeThreeNumber, typeFourNumber;  // 类型数量
    private float textSize;
    private float lineSize;
    private int presentOne = 1, PresentTwo = 1, PresentThree = 1, PresentFour = 1, PresentFive = 1;
    private int start_angle_one = 0, start_angle_two = 72, start_angle_three = 144, start_angle_four = 216, start_angle_five = 288;
    private int sweep_angle_one = 72, sweep_angle_two = 72, sweep_angle_three = 72, sweep_angle_four = 72, sweep_angle_five = 72;
    private String type; // 默认类型为4个进度的类型  可以修改为3


    public FiveRuandProgressView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    private void initData(Context context, AttributeSet attrs) {

        ProgressBgColor = Color.parseColor("#E5E5E5");
        textColor = Color.BLACK;
        lineColor = Color.GRAY;

        ProgressOneColor = Color.parseColor("#FABF13");
        ProgressTwoColor = Color.parseColor("#7ECEF4");
        ProgressThreeColor = Color.parseColor("#B3A1CC");
        ProgressFourColor = Color.parseColor("#F6B37F");
        ProgressFiveColor = Color.parseColor("#ADD597");


        textSize = spToPx(14);
        lineSize = dpToPx(1);


    }

    @Override
    protected void init(Context context, AttributeSet attrs) {

        initData(context, attrs);
        // 控件默认大小
        mWidth = dpToPx(260);
        mHeight = dpToPx(260);
        unit = (float) (mHeight * 0.1);
        strokeWith = (float) (mHeight * 0.03);

        // 初始化画笔
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(strokeWith);

        // 圆画笔
        mCirclePaint = new Paint();
        mCirclePaint.setAntiAlias(true);
        mCirclePaint.setStyle(Paint.Style.STROKE);

        mCirclePaint.setColor(Color.WHITE);


    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        drawCoordinate(canvas);
        canvas.translate(mCenterX, mCenterY);

        drawBackGround(canvas);
        drawShapeColor(canvas);


    }


    /**
     * 画灰色进度背景
     */
    private void drawBackGround(Canvas canvas) {
        mPaint.setColor(Color.parseColor("#EEEEEE"));
        mPaint.setStrokeWidth(dpToPx(3));
        canvas.drawCircle(0, 0, mCenterX / 3, mPaint);
        mPaint.setStrokeWidth(dpToPx(8));
        canvas.drawCircle(0, 0, mCenterX - dpToPx(6), mPaint);


    }

    /**
     * 画进度条
     */
    private void drawShapeColor(Canvas canvas) {
        float banjin = mCenterX * 0.65f;
        mCirclePaint.setColor(ProgressOneColor);
        mCirclePaint.setStrokeWidth(mCenterX / 2.5f);
        canvas.drawArc(-banjin, -banjin, banjin, banjin, start_angle_one, sweep_angle_one, false, mCirclePaint);
        mCirclePaint.setColor(ProgressTwoColor);
        canvas.drawArc(-banjin, -banjin, banjin, banjin, start_angle_two, sweep_angle_two, false, mCirclePaint);
        mCirclePaint.setColor(ProgressThreeColor);
        canvas.drawArc(-banjin, -banjin, banjin, banjin, start_angle_three, sweep_angle_three, false, mCirclePaint);
        mCirclePaint.setColor(ProgressFourColor);
        canvas.drawArc(-banjin, -banjin, banjin, banjin, start_angle_four, sweep_angle_four, false, mCirclePaint);
        mCirclePaint.setColor(ProgressFiveColor);
        canvas.drawArc(-banjin, -banjin, banjin, banjin, start_angle_five, sweep_angle_five, false, mCirclePaint);
    }

    /**
     * 画小圆
     */
    private void drawWhiteCircle(Canvas canvas, float units, int type) {

    }

    /**
     * 画虚线
     */
    private void drawLine(Canvas canvas, float units, int type) {

    }

    /**
     * 画文字
     */
    private void drawText(Canvas canvas, int type, String typeName, String number) {

    }

    /**
     * 设置数据
     */
    public void setData(List<Integer> presentList, int total) {
        if (total != 0) {
            this.start_angle_one = -90;
            sweep_angle_one = (int) (presentList.get(0) / (double) total * 360);


            this.start_angle_two = start_angle_one + sweep_angle_one;
            sweep_angle_two = (int) (presentList.get(1) / (double) total * 360);


            this.start_angle_three = start_angle_two + sweep_angle_two;
            sweep_angle_three = (int) (presentList.get(2) / (double) total * 360);


            this.start_angle_four = start_angle_three + sweep_angle_three;
            sweep_angle_four = (int) (presentList.get(3) / (double) total * 360);

            this.start_angle_five = start_angle_four + sweep_angle_four;
            sweep_angle_five = (int) (presentList.get(4) / (double) total * 360);

            if (sweep_angle_one > 0) {
                sweep_angle_one += 1;
                start_angle_one -= 1;
            }
            if (sweep_angle_two > 0) {
                sweep_angle_two += 1;
                start_angle_two -= 1;
            }

            if (sweep_angle_three > 0) {
                sweep_angle_three += 1;
                start_angle_three -= 1;
            }

            if (sweep_angle_four > 0) {
                sweep_angle_four += 1;
                start_angle_four -= 1;
            }

            if (sweep_angle_five > 0) {
                sweep_angle_five += 1;
                start_angle_five -= 1;
            }

            if (sweep_angle_one > 0) {
                sweep_angle_one += 1;
                start_angle_one -= 1;
            }



            invalidate();
        }

    }

}
