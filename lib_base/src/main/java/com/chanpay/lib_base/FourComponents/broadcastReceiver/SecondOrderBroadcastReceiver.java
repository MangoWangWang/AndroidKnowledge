package com.chanpay.lib_base.FourComponents.broadcastReceiver;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.widget.Toast;

public class SecondOrderBroadcastReceiver extends BroadcastReceiver {
    // 默认情况下，广播接收器运行在 UI 线程，因此，onReceive()方法不能执行耗时操作，否则将导致ANR
    @Override
    public void onReceive(final Context context, final Intent intent) {

        AlertDialog alertDialog = new AlertDialog.Builder(context)
                .setTitle("有序广播02")
                .setMessage("这是一条有序广播 : " + intent.getStringExtra("name"))
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {//添加"Yes"按钮
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(context, "点击确定按钮", Toast.LENGTH_SHORT).show();
//                        ((Activity) context).finish();
                    }
                })

                .setNegativeButton("取消", new DialogInterface.OnClickListener() {//添加取消
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(context, "点击取消按钮", Toast.LENGTH_SHORT).show();
                    }
                })
                .create();
        alertDialog.show();
    }
}