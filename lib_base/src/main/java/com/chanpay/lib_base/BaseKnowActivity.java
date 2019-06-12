package com.chanpay.lib_base;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.chanpay.lib_base.activity.LifecycleActivity;
import com.chanpay.lib_base.broadcastReceiver.BroadcastReceiverActivity;
import com.chanpay.lib_base.service.IntentServiceActivity;
import com.chanpay.lib_base.service.ServiceActivity;

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
    public void StartServiceActivity(View view) {

        startActivity(new Intent(this, ServiceActivity.class));
    }

    public void StartIntentServiceActivity(View view) {
        startActivity(new Intent(this, IntentServiceActivity.class));
    }

    public void StartBroadcastReceiverActivity(View view) {
        startActivity(new Intent(this, BroadcastReceiverActivity.class));
    }
}
