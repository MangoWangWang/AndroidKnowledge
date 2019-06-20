package com.chanpay.lib_base.Animation;

import android.animation.ValueAnimator;
import android.content.Intent;
import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.BounceInterpolator;
import android.widget.Button;

import com.chanpay.lib_base.Animation.ObjectAnimation.ObjectAnimationActivity;
import com.chanpay.lib_base.Animation.TypeEvaluator.TypeEvaluatorActivity;
import com.chanpay.lib_base.R;

/**
 * 文件名 : PropertyAnimationActivity.java
 * 创建者 : TSW
 * 创建日期 : 2019/6/20 10:40
 * 微信 : MangoWaWang
 * 邮箱 : 763482205@qq.com
 * 描述 : TODO
 */
public class PropertyAnimationActivity extends AppCompatActivity {

    Button mButton;
    private int width,height;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_property_animation);
        mButton = findViewById(R.id.btn_animation);
        width = mButton.getLayoutParams().width;
        height = mButton.getLayoutParams().height;
    }

    public void StartValueAnimationOfInt(View view) {

        ValueAnimator valueAnimator = ValueAnimator.ofInt(mButton.getLayoutParams().width, 500);
        // 初始值 = 当前按钮的宽度，此处在xml文件中设置为150
        // 结束值 = 500
        // ValueAnimator.ofInt()内置了整型估值器,直接采用默认的.不需要设置
        // 即默认设置了如何从初始值150 过渡到 结束值500

        // 步骤2：设置动画的播放各种属性
        valueAnimator.setDuration(2000);
        // 设置动画运行时长:1s

        // 步骤3：将属性数值手动赋值给对象的属性:此处是将 值 赋给 按钮的宽度
        // 设置更新监听器：即数值每次变化更新都会调用该方法
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animator) {

                int currentValue = (Integer) animator.getAnimatedValue();
                // 获得每次变化后的属性值
                System.out.println(currentValue);
                // 输出每次变化后的属性值进行查看

                mButton.getLayoutParams().width = currentValue;
                // 每次值变化时，将值手动赋值给对象的属性
                // 即将每次变化后的值 赋 给按钮的宽度，这样就实现了按钮宽度属性的动态变化

                // 步骤4：刷新视图，即重新绘制，从而实现动画效果
                mButton.requestLayout();

            }
        });

        valueAnimator.start();
        // 启动动画
    }

    public void StartValueAnimationOfFloat(View view) {
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(mButton.getLayoutParams().height, 500);
        // 初始值 = 当前按钮的宽度，此处在xml文件中设置为150
        // 结束值 = 500
        // ValueAnimator.ofInt()内置了整型估值器,直接采用默认的.不需要设置
        // 即默认设置了如何从初始值150 过渡到 结束值500

        // 步骤2：设置动画的播放各种属性
        valueAnimator.setDuration(2000);
        // 设置动画运行时长:1s

        // 步骤3：将属性数值手动赋值给对象的属性:此处是将 值 赋给 按钮的宽度
        // 设置更新监听器：即数值每次变化更新都会调用该方法
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animator) {

                float currentValue = (Float) animator.getAnimatedValue();
                // 获得每次变化后的属性值
                System.out.println(currentValue);
                // 输出每次变化后的属性值进行查看

                mButton.getLayoutParams().height = (int) currentValue;
                // 每次值变化时，将值手动赋值给对象的属性
                // 即将每次变化后的值 赋 给按钮的宽度，这样就实现了按钮宽度属性的动态变化

                // 步骤4：刷新视图，即重新绘制，从而实现动画效果
                mButton.requestLayout();

            }
        });

        valueAnimator.start();
        // 启动动画
    }

    public void StartValueAnimationOfObject(View view) {
        startActivity(new Intent(PropertyAnimationActivity.this, TypeEvaluatorActivity.class));
    }

    public void StartObjectAnimator(View view) {
        startActivity(new Intent(PropertyAnimationActivity.this, ObjectAnimationActivity.class));

    }



    public void ResetAnimation(View view) {
        mButton.getLayoutParams().width = width;
        mButton.getLayoutParams().height = height;
        mButton.requestLayout();

    }

    public void StartViewPropertyAnimator(View view) {
        mButton.animate().alpha(0f).setDuration(2000);
//        mButton.animate().alpha(1f).setDuration(2000);
    }
}
