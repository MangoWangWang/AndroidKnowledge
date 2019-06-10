package com.chanpay.lib_base;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.chanpay.lib_base.lifecycle.LifecycleActivity;

public class BaseKnowActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_know);
        ActionBar bar = getSupportActionBar();
        bar.setTitle("基础部分");
    }



    public void StartLifecycleActivity(View view) {
        startActivity(new Intent(this,LifecycleActivity.class));
    }
}
