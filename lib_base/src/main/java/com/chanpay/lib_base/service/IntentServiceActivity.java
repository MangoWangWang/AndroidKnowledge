package com.chanpay.lib_base.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.chanpay.lib_base.R;

public class IntentServiceActivity extends AppCompatActivity {
    /**
     * 状态文字
     */
    TextView tv_status;

    /**
     * 进度文字
     */
    TextView tv_progress;

    /**
     * 进度条
     */
    ProgressBar progressbar;

    private LocalBroadcastManager mLocalBroadcastManager;
    private MyBroadcastReceiver mBroadcastReceiver;
    public final static String ACTION_TYPE_THREAD = "action.type.thread";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent_service);

        //注册广播
        mLocalBroadcastManager = LocalBroadcastManager.getInstance(this);
        mBroadcastReceiver = new MyBroadcastReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ACTION_TYPE_THREAD);
        mLocalBroadcastManager.registerReceiver(mBroadcastReceiver, intentFilter);

        initView();
    }

    public void initView() {
        tv_status = findViewById(R.id.tv_status);
        tv_progress = findViewById(R.id.tv_progress);
        progressbar = findViewById(R.id.progressbar);
        tv_status.setText("线程状态：未运行");
        progressbar.setMax(100);
        progressbar.setProgress(0);
        tv_progress.setText("0%");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //注销广播
        mLocalBroadcastManager.unregisterReceiver(mBroadcastReceiver);
    }

    public class MyBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            switch (intent.getAction()) {

                case ACTION_TYPE_THREAD:
                    //更改UI
                    int progress = intent.getIntExtra("progress", 0);
                    tv_status.setText("线程状态：" + intent.getStringExtra("status"));
                    progressbar.setProgress(progress);
                    tv_progress.setText(progress + "%");
                    if (progress >= 100) {
                        tv_status.setText("线程结束");
                    }
                    break;
            }
        }
    }

    public void onClick(View view) {
        Intent intent = new Intent(IntentServiceActivity.this, MyIntentService.class);
        intent.putExtra("name","mango");
        startService(intent);
    }
}