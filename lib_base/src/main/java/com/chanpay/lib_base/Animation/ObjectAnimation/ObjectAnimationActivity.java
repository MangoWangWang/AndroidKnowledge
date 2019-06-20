package com.chanpay.lib_base.Animation.ObjectAnimation;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.chanpay.lib_base.R;

public class ObjectAnimationActivity extends AppCompatActivity {

    Button mButton;
    MyView2 myView2;

    ViewWrapper wrapper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_object_animation);
        mButton = findViewById(R.id.btn_animation);
        myView2 = findViewById(R.id.change_color_view);
        wrapper = new ViewWrapper(mButton);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ObjectAnimator.ofInt(wrapper, "width", 500).setDuration(3000).start();
                // 设置动画的对象是包装类的对象
            }
        });
    }


    public void StartAlphaAnimal(View view) {
        // 创建动画作用对象：此处以Button为例

        ObjectAnimator animator = ObjectAnimator.ofFloat(mButton, "alpha", 1f, 0f, 1f);
        // 表示的是:
        // 动画作用对象是mButton
        // 动画作用的对象的属性是透明度alpha
        // 动画效果是:常规 - 全透明 - 常规
        animator.setDuration(5000);
        animator.start();
    }

    public void StartRotationAnimal(View view) {

        ObjectAnimator animator = ObjectAnimator.ofFloat(mButton, "rotation", 0f, 360f);

        // 表示的是:
        // 动画作用对象是mButton
        // 动画作用的对象的属性是旋转alpha
        // 动画效果是:0 - 360
        animator.setDuration(5000);
        animator.start();
    }

    public void StartTranslationXAnimal(View view) {

        // 创建动画作用对象：此处以Button为例

        float curTranslationX = mButton.getTranslationX();
        // 获得当前按钮的位置
        ObjectAnimator animator = ObjectAnimator.ofFloat(mButton, "translationX", curTranslationX, 300, curTranslationX);


        // 表示的是:
        // 动画作用对象是mButton
        // 动画作用的对象的属性是X轴平移（在Y轴上平移同理，采用属性"translationY"
        // 动画效果是:从当前位置平移到 x=1500 再平移到初始位置
        animator.setDuration(5000);
        animator.start();

    }

    public void StartScaleXAnimal(View view) {

        ObjectAnimator animator = ObjectAnimator.ofFloat(mButton, "scaleX", 1f, 3f, 1f);
        // 表示的是:
        // 动画作用对象是mButton
        // 动画作用的对象的属性是X轴缩放
        // 动画效果是:放大到3倍,再缩小到初始大小
        animator.setDuration(5000);
        animator.start();
    }

    public void StartCustomAnimation(View view) {
        ObjectAnimator anim = ObjectAnimator.ofObject(myView2, "color", new ColorEvaluator(),
                "#0000FF", "#FF0000");
        // 设置自定义View对象、背景颜色属性值 & 颜色估值器
        // 本质逻辑：
        // 步骤1：根据颜色估值器不断 改变 值
        // 步骤2：调用set（）设置背景颜色的属性值（实际上是通过画笔进行颜色设置）
        // 步骤3：调用invalidate()刷新视图，即调用onDraw（）重新绘制，从而实现动画效果

        anim.setDuration(8000);
        anim.start();
    }

    public void StartSetAnimation(View view) {
        float curTranslationX = mButton.getTranslationX();
        // 步骤1：设置需要组合的动画效果
        ObjectAnimator translation = ObjectAnimator.ofFloat(mButton, "translationX", curTranslationX, 300, curTranslationX);
// 平移动画
        ObjectAnimator rotate = ObjectAnimator.ofFloat(mButton, "rotation", 0f, 360f);
// 旋转动画
        ObjectAnimator alpha = ObjectAnimator.ofFloat(mButton, "alpha", 1f, 0f, 1f);
// 透明度动画

// 步骤2：创建组合动画的对象
        AnimatorSet animSet = new AnimatorSet();

// 步骤3：根据需求组合动画
        animSet.play(translation).with(rotate).before(alpha);
        animSet.setDuration(5000);

// 步骤4：启动动画
        animSet.start();
    }

    // 提供ViewWrapper类,用于包装View对象
    // 本例:包装Button对象
    private static class ViewWrapper {
        private View mTarget;

        // 构造方法:传入需要包装的对象
        public ViewWrapper(View target) {
            mTarget = target;
        }

        // 为宽度设置get（） & set（）
        public int getWidth() {
            return mTarget.getLayoutParams().width;
        }

        public void setWidth(int width) {
            mTarget.getLayoutParams().width = width;
            mTarget.requestLayout();
        }

    }

}
