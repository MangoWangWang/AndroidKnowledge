package com.chanpay.lib_base.broadcastReceiver;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.widget.Toast;

public class MyBroadcastReceiverTwo extends BroadcastReceiver {
    public final static String ACTION_TYPE_OUT = "action.type.out";


    // 默认情况下，广播接收器运行在 UI 线程，因此，onReceive()方法不能执行耗时操作，否则将导致ANR
    @Override
    public void onReceive(final Context context, Intent intent) {

        switch (intent.getAction()) {
            case ACTION_TYPE_OUT:
                AlertDialog alertDialog = new AlertDialog.Builder(context)
                        .setTitle("这是标题")
                        .setMessage("有多个按钮")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {//添加"Yes"按钮
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast.makeText(context, "这是确定按钮", Toast.LENGTH_SHORT).show();
                                ((Activity)context).finish();
                            }
                        })

                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {//添加取消
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast.makeText(context, "这是取消按钮", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .create();
                alertDialog.show();
                break;
        }
    }
}