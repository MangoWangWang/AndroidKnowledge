package com.chanpay.lib_base.broadcastReceiver;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.chanpay.lib_base.R;

public class BroadcastReceiverActivity extends AppCompatActivity {


    private LocalBroadcastManager mLocalBroadcastManager;
    private MyBroadcastReceiverTwo mBroadcastReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broadcaset_receiver);

        //注册广播
//        mLocalBroadcastManager = LocalBroadcastManager.getInstance(this);
        mBroadcastReceiver = new MyBroadcastReceiverTwo();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(MyBroadcastReceiverTwo.ACTION_TYPE_OUT);
        registerReceiver(mBroadcastReceiver, intentFilter);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        //注销广播
//        mLocalBroadcastManager.unregisterReceiver(mBroadcastReceiver);
        unregisterReceiver(mBroadcastReceiver);

    }


    public void onClick(View view) {
        Intent intent = new Intent(MyBroadcastReceiverTwo.ACTION_TYPE_OUT);
        intent.putExtra("name","mango");
        sendBroadcast(intent);
    }
}