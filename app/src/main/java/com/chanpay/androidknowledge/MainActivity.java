package com.chanpay.androidknowledge;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.chanpay.lib_base.BaseKnowledgeActivity;
import com.chanpay.lib_base.FourComponents.FourComponentsActivity;
import com.chanpay.lib_view.CustomViewActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void startBaseKnowActivity(View view) {
        startActivity(new Intent(this, BaseKnowledgeActivity.class));
    }

    public void startCustomView(View view) {
        startActivity(new Intent(this, CustomViewActivity.class));
    }
}
