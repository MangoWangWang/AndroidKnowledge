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
        startActivity(new Intent(AnimationActivity.this,TweenAnimationActivity.class));
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
}
