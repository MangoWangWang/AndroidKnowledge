package com.chanpay.lib_base.Animation;

import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.chanpay.lib_base.R;

/**
 * 文件名 : FrameAnimationActivity.java
 * 创建者 : TSW
 * 创建日期 : 2019/6/21 11:15
 * 微信 : MangoWaWang
 * 邮箱 : 763482205@qq.com
 * 描述 : TODO
 */
public class FrameAnimationActivity extends AppCompatActivity {


    private ImageView iv;
    private AnimationDrawable animationDrawable;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frame_animation);

        iv = (ImageView) findViewById(R.id.imageView);

//          <-- 直接从drawable文件夹获取动画资源（图片） -->
        animationDrawable = new AnimationDrawable();
        for (int i = 1; i <= 8; i++) {
            int id = getResources().getIdentifier("toulan" + i, "drawable", getPackageName());
            Drawable drawable = getResources().getDrawable(id);
            animationDrawable.addFrame(drawable, 200);
        }

        animationDrawable.setOneShot(false);
        iv.setImageDrawable(animationDrawable);

    }

    public void StartAnimation(View view) {
        // 特别注意：在动画start()之前要先stop()，不然在第一次动画之后会停在最后一帧，这样动画就只会触发一次
        animationDrawable.start();
        // 启动动画
    }

    public void StopAnimation(View view) {

        animationDrawable.stop();
    }

}
