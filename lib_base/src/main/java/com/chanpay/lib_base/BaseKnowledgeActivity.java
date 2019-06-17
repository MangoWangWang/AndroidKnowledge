package com.chanpay.lib_base;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.chanpay.lib_base.FourComponents.FourComponentsActivity;
import com.chanpay.lib_base.FourComponents.activity.LifecycleActivity;
import com.chanpay.lib_base.FourComponents.broadcastReceiver.BroadcastReceiverActivity;
import com.chanpay.lib_base.FourComponents.service.IntentServiceActivity;
import com.chanpay.lib_base.FourComponents.service.ServiceActivity;
import com.chanpay.lib_base.FourComponents.sqllite.SqlLiteActivity;
import com.chanpay.lib_base.LayoutUse.LayoutActivity;

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
}
