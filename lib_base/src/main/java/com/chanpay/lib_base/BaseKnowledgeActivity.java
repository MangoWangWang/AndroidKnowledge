package com.chanpay.lib_base;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.chanpay.lib_base.Animation.AnimationActivity;
import com.chanpay.lib_base.FourComponents.FourComponentsActivity;
import com.chanpay.lib_base.LayoutUse.LayoutActivity;
import com.chanpay.lib_base.Thread.ThreadActivity;


    /**
         * 文件名 : BaseKnowledgeActivity.java
         * 创建者 : TSW
         * 创建日期 : 2019/6/19 16:26
         * 微信 : MangoWaWang
         * 邮箱 : 763482205@qq.com
         * 描述 : TODO
         */

public class BaseKnowledgeActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_knowledge);
        ActionBar bar = getSupportActionBar();
        bar.setTitle("基础知识");
    }


    public void StartFourComponentsActivity(View view) {
        startActivity(new Intent(BaseKnowledgeActivity.this,FourComponentsActivity.class));
    }

    public void StartLayoutActivity(View view) {
        startActivity(new Intent(BaseKnowledgeActivity.this,LayoutActivity.class));
    }

    public void StartThreadActivity(View view) {
        startActivity(new Intent(BaseKnowledgeActivity.this, ThreadActivity.class));
    }

    public void StartAnimationActivity(View view) {
        startActivity(new Intent(BaseKnowledgeActivity.this, AnimationActivity.class));
    }
}
