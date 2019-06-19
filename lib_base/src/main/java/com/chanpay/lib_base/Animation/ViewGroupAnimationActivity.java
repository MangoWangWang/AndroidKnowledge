package com.chanpay.lib_base.Animation;

import android.animation.LayoutTransition;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chanpay.lib_base.R;

/**
 * 文件名 : ViewGroupAnimationActivity.java
 * 创建者 : TSW
 * 创建日期 : 2019/6/19 17:48
 * 微信 : MangoWaWang
 * 邮箱 : 763482205@qq.com
 * 描述 : TODO
 */
public class ViewGroupAnimationActivity extends AppCompatActivity {
    LinearLayout rootView;
    Button addBtn,removeBtn;
    private int count = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_group_animation);
        rootView = findViewById(R.id.root_view);
        addBtn = findViewById(R.id.btnAdd);
        removeBtn = findViewById(R.id.btnRemove);
        setTransitionAnimator();
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView textView = new TextView(ViewGroupAnimationActivity.this);
                count++;
                textView.setText("Text"+count);

                rootView.addView(textView);
            }
        });

        removeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (count>=0)
                {
                    rootView.removeViewAt(count);
                    count--;
                }
            }
        });

    }

    @SuppressLint("ObjectAnimatorBinding")
    private void setTransitionAnimator() {

        LayoutTransition transition = new LayoutTransition();
        // 为 ViewGroup 容器绑定 LayoutTransition 对象
        rootView.setLayoutTransition(transition);

        // 使用翻转进入的动画代替默认的 APPEARING 动画
        ObjectAnimator appearAnim = ObjectAnimator
                .ofFloat(null, "rotationY", 90f, 0f)
                .setDuration(transition.getDuration(LayoutTransition.APPEARING) * 10);
        transition.setAnimator(LayoutTransition.APPEARING, appearAnim);

        // 使用滑动动画代替默认布局改变的动画
        // 这个动画会让视图滑动进入并短暂地缩小一半，具有平滑和缩放的效果
        PropertyValuesHolder pvhSlide = PropertyValuesHolder.ofFloat("y", 0f, 1f);
        PropertyValuesHolder pvhScaleY = PropertyValuesHolder.ofFloat("scaleY", 1f, 0.5f, 1f);
        PropertyValuesHolder pvhScaleX = PropertyValuesHolder.ofFloat("scaleX", 1f, 0.5f, 1f);

        ObjectAnimator changingDisappearAnim = ObjectAnimator.ofPropertyValuesHolder(this, pvhSlide, pvhScaleY, pvhScaleX);
        changingDisappearAnim.setDuration(transition.getDuration(LayoutTransition.CHANGE_DISAPPEARING));
        transition.setAnimator(LayoutTransition.CHANGE_DISAPPEARING, changingDisappearAnim);
    }
}
