package com.chanpay.lib_base.Animation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.chanpay.lib_base.R;


public class AnimationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);
    }

    // 补间动画
    public void StartTweenAnimationActivity(View view) {

        Intent intent = new Intent(AnimationActivity.this, TweenAnimationActivity.class);
        startActivity(intent);
        // 淡入淡出的动画效果
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);

        // 从左向右滑动的效果
//        overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);


//        startActivity(new Intent(AnimationActivity.this,TweenAnimationActivity.class));
    }

    // 属性动画
    public void StartPropertyAnimationActivity(View view) {
    }

    // 帧动画
    public void StartFrameAnimationActivity(View view) {
    }

    // 插值器和估值器
    public void StartInterpolatorAndTypeEvaluatornActivity(View view) {
    }

    public void StartViewGroupAnimationActivity(View view) {
        startActivity(new Intent(AnimationActivity.this,ViewGroupAnimationActivity.class));
    }
}
