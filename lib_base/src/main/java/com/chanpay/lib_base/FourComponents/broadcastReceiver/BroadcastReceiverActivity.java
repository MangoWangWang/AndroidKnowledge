package com.chanpay.lib_base.FourComponents.broadcastReceiver;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;

import com.chanpay.lib_base.R;

public class BroadcastReceiverActivity extends AppCompatActivity {


    private LocalBroadcastManager mLocalBroadcastManager;
    private NormalBroadcastReceiver normalBroadcastReceiver;
    private SystemBroadcastReceiver systemBroadcastReceiver;
    private FirstOrderBroadcastReceiver firstOrderBroadcastReceiver;
    private SecondOrderBroadcastReceiver secondOrderBroadcastReceiver;
    private LocalBroadcastReceiver localBroadcastReceiver;
    private RadioGroup radioGroup;
    private Button button;
    private int type = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broadcaset_receiver);
        radioGroup = findViewById(R.id.rg_broadcast);
        button = findViewById(R.id.btn_start);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.rb_normal) {
                    type = 0;
                    button.setEnabled(true);
                } else if (checkedId == R.id.rb_system) {
                    type = 1;
                    button.setEnabled(false);


                } else if (checkedId == R.id.rb_ordered) {
                    type = 2;
                    button.setEnabled(true);


                } else if (checkedId == R.id.rb_sticky) {
                    type = 3;
                    button.setEnabled(false);

                } else if (checkedId == R.id.rb_location) {
                    type = 4;
                    button.setEnabled(true);
                }
            }
        });

        //注册广播
        normalBroadcastReceiver = new NormalBroadcastReceiver();
        systemBroadcastReceiver = new SystemBroadcastReceiver();
        firstOrderBroadcastReceiver = new FirstOrderBroadcastReceiver();
        secondOrderBroadcastReceiver = new SecondOrderBroadcastReceiver();
        localBroadcastReceiver = new LocalBroadcastReceiver();
        mLocalBroadcastManager = LocalBroadcastManager.getInstance(this);


        IntentFilter normalFilter = new IntentFilter();
        normalFilter.addAction(NormalBroadcastReceiver.ACTION_TYPE_NORMAL);


        IntentFilter systemFilter = new IntentFilter();
        systemFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");


        IntentFilter firstOrderFilter = new IntentFilter();
        firstOrderFilter.addAction(FirstOrderBroadcastReceiver.ACTION_TYPE_ORDER);
        firstOrderFilter.setPriority(1);

        IntentFilter secondOrderFilter = new IntentFilter();
        secondOrderFilter.addAction(FirstOrderBroadcastReceiver.ACTION_TYPE_ORDER);
        secondOrderFilter.setPriority(2);


        IntentFilter localFilter = new IntentFilter();
        localFilter.addAction(LocalBroadcastReceiver.ACTION_TYPE_LOCATION);

        registerReceiver(normalBroadcastReceiver, normalFilter);
        registerReceiver(systemBroadcastReceiver, systemFilter);
        registerReceiver(firstOrderBroadcastReceiver, firstOrderFilter);
        registerReceiver(secondOrderBroadcastReceiver, secondOrderFilter);
        mLocalBroadcastManager.registerReceiver(localBroadcastReceiver,localFilter);






    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        //注销广播
        mLocalBroadcastManager.unregisterReceiver(localBroadcastReceiver);
        unregisterReceiver(normalBroadcastReceiver);
        unregisterReceiver(systemBroadcastReceiver);
        unregisterReceiver(firstOrderBroadcastReceiver);
        unregisterReceiver(secondOrderBroadcastReceiver);
    }


    public void onClick(View view) {
        //对应BroadcastReceiver中intentFilter的action
        Intent intent = new Intent();
        intent.putExtra("name", "mango");
        if (type == 0) {
            intent.setAction(NormalBroadcastReceiver.ACTION_TYPE_NORMAL);
            sendBroadcast(intent);
        } else if (type == 2) {//发送广播
            intent.setAction(FirstOrderBroadcastReceiver.ACTION_TYPE_ORDER);
            sendOrderedBroadcast(intent, null);
        }else if (type ==4)
        {
            intent.setAction(LocalBroadcastReceiver.ACTION_TYPE_LOCATION);
            mLocalBroadcastManager.sendBroadcast(intent);
        }


    }
}