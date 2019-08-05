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

import java.util.List;


/*四边形进度条*/
public class PlaygroundProgressView extends BaseView {

    private Paint mPaint;
    private Paint mCirclePaint;
    private Paint paintText;
    private Paint mLinePaint;

    private ValueAnimator mAnimator;

    private float unit;  // 默认起点
    private float strokeWith; // 线条宽度
    private float space;  // 线条间距
    private int ProgressBgColor, ProgressOneColor, ProgressTwoColor, ProgressThreeColor, ProgressFourColor; //进度颜色
    private int textColor, lineColor;  // 文字颜色，线条颜色
    private String typeOneName, typeTwoName, typeThreeName, typeFourName;  // 类型名称
    private String typeOneNumber, typeTwoNumber, typeThreeNumber, typeFourNumber;  // 类型数量
    private float textSize;
    private float lineSize;
    private double presentOne = 0.25, PresentTwo = 0.50, PresentThree = 0.75, PresentFour = 1.0;  // 默认进度
    private String type; // 默认类型为4个进度的类型  可以修改为3


    public PlaygroundProgressView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    private void initData(Context context, AttributeSet attrs) {

        ProgressBgColor = Color.parseColor("#E5E5E5");
        textColor = Color.BLACK;
        lineColor = Color.GRAY;

        ProgressOneColor = Color.parseColor("#7ECEF4");
        ProgressTwoColor = Color.parseColor("#F9A3BD");
        ProgressThreeColor = Color.parseColor("#84CCC9");
        ProgressFourColor = Color.parseColor("#F6B37F");


        typeOneNumber = "0.00";
        typeTwoNumber = "0.00";
        typeThreeNumber = "0.00";
        typeFourNumber = "0.00";

        textSize = spToPx(14);
        lineSize = dpToPx(1);

        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.PlaygroundProgressView);

        lineColor = typedArray.getColor(R.styleable.PlaygroundProgressView_pvlineColor, lineColor);
        ProgressBgColor = typedArray.getColor(R.styleable.PlaygroundProgressView_pvshapeBgColor, ProgressBgColor);
        textColor = typedArray.getColor(R.styleable.PlaygroundProgressView_pvtextColor, textColor);
        ProgressOneColor = typedArray.getColor(R.styleable.PlaygroundProgressView_pvprogressOneColor, ProgressOneColor);
        ProgressTwoColor = typedArray.getColor(R.styleable.PlaygroundProgressView_pvprogressTwoColor, ProgressTwoColor);
        ProgressThreeColor = typedArray.getColor(R.styleable.PlaygroundProgressView_pvprogressThreeColor, ProgressThreeColor);
        ProgressFourColor = typedArray.getColor(R.styleable.PlaygroundProgressView_pvprogressFourColor, ProgressFourColor);

        textSize = typedArray.getDimension(R.styleable.PlaygroundProgressView_pvtextSize, textSize);
        lineSize = typedArray.getDimension(R.styleable.PlaygroundProgressView_pvlineSize, lineSize);

        typeOneName = typedArray.getString(R.styleable.PlaygroundProgressView_pvtypeOneName);
        if (TextUtils.isEmpty(typeOneName)) typeOneName = "其他";

        typeTwoName = typedArray.getString(R.styleable.PlaygroundProgressView_pvtypeTwoName);
        if (TextUtils.isEmpty(typeTwoName)) typeTwoName = "个人激活";

        typeThreeName = typedArray.getString(R.styleable.PlaygroundProgressView_pvtypeThreeName);
        if (TextUtils.isEmpty(typeThreeName)) typeThreeName = "团队流水";

        typeFourName = typedArray.getString(R.styleable.PlaygroundProgressView_pvtypeFourName);
        if (TextUtils.isEmpty(typeFourName)) typeFourName = "个人流水";

        type = typedArray.getString(R.styleable.PlaygroundProgressView_pvtype);
        if (TextUtils.isEmpty(typeFourName)) typeFourName = "4";

        if (type.equals("3"))
        {
            typeThreeName = "其他";
        }

        typedArray.recycle();

    }

    @Override
    protected void init(Context context, AttributeSet attrs) {

        initData(context, attrs);
        // 控件默认大小
        mWidth = dpToPx(414);
        mHeight = dpToPx(220);
        unit = (float) (mHeight * 0.1);
        strokeWith = (float) (mHeight * 0.03);
        space = (float) (mHeight * 0.100);

        // 初始化画笔
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(strokeWith);

        // 圆画笔
        mCirclePaint = new Paint();
        mCirclePaint.setAntiAlias(true);
        mCirclePaint.setStyle(Paint.Style.FILL);
        mCirclePaint.setColor(Color.WHITE);

        // 线画笔
        mLinePaint = new Paint();
        mLinePaint.setAntiAlias(true);
        mLinePaint.setStyle(Paint.Style.STROKE);
        mLinePaint.setStrokeWidth(lineSize);
        mLinePaint.setColor(lineColor);
        mLinePaint.setPathEffect(new DashPathEffect(new float[]{15, 5}, 0));

        // 文字画笔
        paintText = new Paint();
        paintText.setColor(textColor);
        paintText.setTextSize(textSize);
        paintText.setAntiAlias(true);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        drawCoordinate(canvas);
        canvas.translate(mCenterX, mCenterY);

        Log.e("mango",type+"");
        if (type.equals("3"))  // 类型三
        {
            // 进度条由内到外 0 到 3 依次增长
            drawBackGround(canvas, unit * 0.65f);
            drawBackGround(canvas, unit * 1.3f);
            drawBackGround(canvas, unit * 2.0f);
            drawBackGround(canvas, unit * 2.7f);


//            drawShapeColor(canvas,unit*0.65f, 0, ProgressOneColor, presentOne);
            drawShapeColor(canvas, unit * 1.3f, 1, ProgressTwoColor, PresentTwo);
            drawShapeColor(canvas, unit * 2.0f, 2, ProgressThreeColor, PresentThree);
            drawShapeColor(canvas, unit * 2.7f, 3, ProgressFourColor, PresentFour);

            drawWhiteCircle(canvas, unit * 0.65f, 0);
            drawWhiteCircle(canvas, unit * 1.3f, 1);
            drawWhiteCircle(canvas, unit * 2.0f, 2);
            drawWhiteCircle(canvas, unit * 2.7f, 3);

//            drawLine(canvas,unit*0.65f, 0);
            drawLine(canvas, unit * 1.3f, 1);
            drawLine(canvas, unit * 2.0f, 2);
            drawLine(canvas, unit * 2.7f, 3);

//            drawText(canvas, 0, typeOneName, typeOneNumber);
            drawText(canvas, 1, typeTwoName, typeTwoNumber);
            drawText(canvas, 2, typeThreeName, typeThreeNumber);
            drawText(canvas, 3, typeFourName, typeFourNumber);
        } else {
            // 进度条由内到外 0 到 3 依次增长
            drawBackGround(canvas, unit * 0.65f);
            drawBackGround(canvas, unit * 1.3f);
            drawBackGround(canvas, unit * 2.0f);
            drawBackGround(canvas, unit * 2.7f);


            drawShapeColor(canvas, unit * 0.65f, 0, ProgressOneColor, presentOne);
            drawShapeColor(canvas, unit * 1.3f, 1, ProgressTwoColor, PresentTwo);
            drawShapeColor(canvas, unit * 2.0f, 2, ProgressThreeColor, PresentThree);
            drawShapeColor(canvas, unit * 2.7f, 3, ProgressFourColor, PresentFour);

            drawWhiteCircle(canvas, unit * 0.65f, 0);
            drawWhiteCircle(canvas, unit * 1.3f, 1);
            drawWhiteCircle(canvas, unit * 2.0f, 2);
            drawWhiteCircle(canvas, unit * 2.7f, 3);

            drawLine(canvas, unit * 0.65f, 0);
            drawLine(canvas, unit * 1.3f, 1);
            drawLine(canvas, unit * 2.0f, 2);
            drawLine(canvas, unit * 2.7f, 3);

            drawText(canvas, 0, typeOneName, typeOneNumber);
            drawText(canvas, 1, typeTwoName, typeTwoNumber);
            drawText(canvas, 2, typeThreeName, typeThreeNumber);
            drawText(canvas, 3, typeFourName, typeFourNumber);
        }


    }


    /**
     * 画灰色进度背景
     */
    private void drawBackGround(Canvas canvas, float units) {
        Path path = new Path();
        float unitsY = units * 0.35f;
        path.moveTo(-units, -unitsY);
        RectF oval = new RectF(-units, -unitsY - units, units, -unitsY + units);
        path.addArc(oval, 180, 180);
        path.lineTo(units, unitsY);
        RectF oval_tow = new RectF(-units, unitsY - units, units, unitsY + units);
        path.addArc(oval_tow, 0, 180);
        path.lineTo(-units, -unitsY);
        mPaint.setColor(ProgressBgColor);
        canvas.drawPath(path, mPaint);
    }

    /**
     * 画进度条
     */
    private void drawShapeColor(Canvas canvas, float units, int type, int color, double present) {
        Path path = new Path();
        float unitsY = units * 0.35f;
        switch (type) {
            case 0:
                path.moveTo(-units, -unitsY);
                path.lineTo(-units, unitsY);
                RectF oval_tow = new RectF(-units, unitsY - units, units, unitsY + units);
                path.arcTo(oval_tow, 180, -180);
                path.lineTo(units, -unitsY);
                RectF oval = new RectF(-units, -unitsY - units, units, -unitsY + units);
                path.arcTo(oval, 0, -180);
                break;
            case 1:
                path.moveTo(units, unitsY);
                RectF oval_tow_2 = new RectF(-units, unitsY - units, units, unitsY + units);
                path.arcTo(oval_tow_2, 0, 180);
                path.lineTo(-units, -unitsY);
                RectF oval_2 = new RectF(-units, -unitsY - units, units, -unitsY + units);
                path.arcTo(oval_2, 180, 180);
                path.lineTo(units, unitsY);
                break;
            case 2:

                path.moveTo(-units, unitsY);
                RectF oval_tow_3 = new RectF(-units, unitsY - units, units, unitsY + units);
                path.arcTo(oval_tow_3, 180, -180);
                path.lineTo(units, -unitsY);
                RectF oval_3 = new RectF(-units, -unitsY - units, units, -unitsY + units);
                path.arcTo(oval_3, 0, -180);
                path.lineTo(-units, unitsY);
                break;
            case 3:
                path.moveTo(units, -unitsY);
                path.lineTo(units, unitsY);
                RectF oval_tow_4 = new RectF(-units, unitsY - units, units, unitsY + units);
                path.arcTo(oval_tow_4, 0, 180);
                path.lineTo(-units, -unitsY);
                RectF oval_4 = new RectF(-units, -unitsY - units, units, -unitsY + units);
                path.arcTo(oval_4, 180, 180);
                break;
        }
        PathMeasure pathMeasure = new PathMeasure(path, false);
        float length = pathMeasure.getLength();
        Path shapePath = new Path();
        pathMeasure.getSegment(0, (float) (length * present), shapePath, true);
        mPaint.setColor(color);
        canvas.drawPath(shapePath, mPaint);
    }

    /**
     * 画小圆
     */
    private void drawWhiteCircle(Canvas canvas, float units, int type) {
        float unitsY = units * 0.35f;
        switch (type) {
            case 0:
                canvas.drawCircle(-units, -unitsY, strokeWith / 2, mCirclePaint);
                break;
            case 1:
                canvas.drawCircle(units, unitsY, strokeWith / 2, mCirclePaint);
                break;
            case 2:
                canvas.drawCircle(-units, unitsY, strokeWith / 2, mCirclePaint);
                break;
            case 3:
                canvas.drawCircle(units, -unitsY, strokeWith / 2, mCirclePaint);
                break;
        }
    }

    /**
     * 画虚线
     */
    private void drawLine(Canvas canvas, float units, int type) {
        float unitsY = units * 0.35f;
        Path path = new Path();
        switch (type) {
            case 0:
                path.moveTo(-units, -unitsY);
                path.lineTo(-(unit + space * 2.5f), -(unit + space * 1f));
                path.lineTo(-(mCenterX - dpToPx(20)), -(unit + space * 1f));
                break;
            case 1:
                path.moveTo(units, unitsY);
                path.lineTo((unit + space * 2.5f), (unit + space * 2f));
                path.lineTo((mCenterX - dpToPx(20)), (unit + space * 2f));
                break;
            case 2:
                if (this.type.equals("3")) {
                    path.moveTo(-units, unitsY);
                    path.lineTo(-(unit + space * 2.5f), -unit);
                    path.lineTo(-(mCenterX - dpToPx(20)),-unit);
                    break;
                } else {
                    path.moveTo(-units, unitsY);
                    path.lineTo(-(unit + space * 2.5f), (unit + space * 2f));
                    path.lineTo(-(mCenterX - dpToPx(20)), (unit + space * 2f));
                    break;
                }


            case 3:
                path.moveTo(units, -unitsY);
                path.lineTo((unit + space * 2.5f), -(unit + space * 1f));
                path.lineTo((mCenterX - dpToPx(20)), -(unit + space * 1f));
                break;
        }
        canvas.drawPath(path, mLinePaint);
    }

    /**
     * 画文字
     */
    private void drawText(Canvas canvas, int type, String typeName, String number) {
        Rect rectName = new Rect();
        Rect rectNumber = new Rect();
        paintText.getTextBounds(typeName, 0, typeName.length(), rectName);
        paintText.getTextBounds(number, 0, number.length(), rectNumber);
        int nameHeight = rectName.height();
        int nameWidth = rectName.width();
        int numberWidth = rectNumber.width();
        int numberHeight = rectNumber.height();

        switch (type) {
            case 0:
                canvas.drawText(typeName, -(mCenterX - dpToPx(20)), -(unit + space * 0.9f) + nameHeight, paintText);
                canvas.drawText(number, -(mCenterX - dpToPx(20)), -(unit + space * 0.7f) - nameHeight, paintText);
                break;
            case 1:
                canvas.drawText(typeName, (mCenterX - dpToPx(20)) - nameWidth, (unit + space * 1.7f), paintText);
                canvas.drawText(number, (mCenterX - dpToPx(20)) - numberWidth, (unit + space * 2.1f) + numberHeight, paintText);
                break;
            case 2:

                if (this.type.equals("3")) {
                    canvas.drawText(typeName, -(mCenterX - dpToPx(20)), -unit-0.3f*space, paintText);
                    canvas.drawText(number, -(mCenterX - dpToPx(20)),  -unit+0.2f*space + numberHeight, paintText);
                    break;
                } else {
                    canvas.drawText(typeName, -(mCenterX - dpToPx(20)), unit + space * 1.7f, paintText);
                    canvas.drawText(number, -(mCenterX - dpToPx(20)), (unit + space * 2.2f) + numberHeight, paintText);
                }


                break;
            case 3:
                canvas.drawText(number, (mCenterX - dpToPx(20)) - numberWidth, -(unit + space * 1.3f), paintText);
                canvas.drawText(typeName, (mCenterX - dpToPx(20)) - nameWidth, -(unit + space * 0.8f) + numberHeight, paintText);
                break;
        }


    }

    /**
     * 设置数据
     */
    public void setData(List<Double> presentList, List<String> NumberList) {
        if (presentList.size() >= 4) {
            this.presentOne = presentList.get(0);
            this.PresentTwo = presentList.get(1);
            this.PresentThree = presentList.get(2);
            this.PresentFour = presentList.get(3);
        } else {
            Toast.makeText(getContext(), "presentList数据不能少于4个", Toast.LENGTH_SHORT);
        }

        if (NumberList.size() >= 4) {
            this.typeOneNumber = NumberList.get(0);
            this.typeTwoNumber = NumberList.get(1);
            this.typeThreeNumber = NumberList.get(2);
            this.typeFourNumber = NumberList.get(3);
        } else {
            Toast.makeText(getContext(), "NumberList数据不能少于4个", Toast.LENGTH_SHORT);
        }
        invalidate();
    }

    /**
     * 设置数据
     */
    public void setThreeData(List<Double> presentList, List<String> NumberList) {
        if (presentList.size() >= 3) {
            this.PresentTwo = presentList.get(0);
            this.PresentThree = presentList.get(1);
            this.PresentFour = presentList.get(2);
        } else {
            Toast.makeText(getContext(), "presentList数据不能少于3个", Toast.LENGTH_SHORT);
        }

        if (NumberList.size() >= 3) {
            this.typeTwoNumber = NumberList.get(0);
            this.typeThreeNumber = NumberList.get(1);
            this.typeFourNumber = NumberList.get(2);
        } else {
            Toast.makeText(getContext(), "NumberList数据不能少于3个", Toast.LENGTH_SHORT);
        }
        invalidate();
    }
}
