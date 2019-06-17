package com.chanpay.lib_base.FourComponents.broadcastReceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class LocalBroadcastReceiver extends BroadcastReceiver {
    public final static String ACTION_TYPE_LOCATION = "action.type.local";
    // 默认情况下，广播接收器运行在 UI 线程，因此，onReceive()方法不能执行耗时操作，否则将导致ANR
    @Override
    public void onReceive(final Context context, Intent intent) {
//        AlertDialog alertDialog = new AlertDialog.Builder(context)
//                .setTitle("本地广播")
//                .setMessage("这是一条本地广播")
//                .setPositiveButton("确定", new DialogInterface.OnClickListener() {//添加"Yes"按钮
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(context, "本地广播", Toast.LENGTH_SHORT).show();
////                        ((Activity) context).finish();
//                    }
//                })
//
//                .setNegativeButton("取消", new DialogInterface.OnClickListener() {//添加取消
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//                        Toast.makeText(context, "点击取消按钮", Toast.LENGTH_SHORT).show();
//                    }
//                })
//                .create();
//        alertDialog.show();
    }
}