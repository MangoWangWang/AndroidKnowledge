package com.chanpay.lib_base.FourComponents;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.chanpay.lib_base.FourComponents.activity.LifecycleActivity;
import com.chanpay.lib_base.FourComponents.broadcastReceiver.BroadcastReceiverActivity;
import com.chanpay.lib_base.FourComponents.service.IntentServiceActivity;
import com.chanpay.lib_base.FourComponents.service.ServiceActivity;
import com.chanpay.lib_base.FourComponents.sqllite.SqlLiteActivity;
import com.chanpay.lib_base.R;

public class FourComponentsActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_four_components);
        ActionBar bar = getSupportActionBar();
        bar.setTitle("四大组件");
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

    public void StartSqlliteActivity(View view) {
        startActivity(new Intent(this, SqlLiteActivity.class));    }
}
