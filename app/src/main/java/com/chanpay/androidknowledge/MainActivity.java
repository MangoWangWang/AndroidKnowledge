package com.chanpay.androidknowledge;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.chanpay.lib_base.BaseKnowActivity;
import com.chanpay.lib_view.CustomViewActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void startBaseKnowActivity(View view) {
        startActivity(new Intent(this, BaseKnowActivity.class));
    }

    public void startCustomView(View view) {
        startActivity(new Intent(this, CustomViewActivity.class));
    }
}
