package com.chanpay.lib_adv;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ShareClickActivity extends AppCompatActivity {


    /**
     * ViewGroup布局（myLayout）中有2个子View = 2个按钮
     */

    Button button1,buttom0;
    TextView  button2;
    ViewGroup myLayout;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_click);

        button1 = (Button) findViewById(R.id.button1);
        button2 =  findViewById(R.id.button2);
        buttom0 = (Button) findViewById(R.id.button);
        myLayout = (LinearLayout) findViewById(R.id.my_layout);

        // 1.为ViewGroup布局设置监听事件
        myLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("TAG", "点击了ViewGroup");
            }
        });

        // 2. 为按钮1设置监听事件
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("TAG", "点击了button1");
            }
        });


        // 3. 为按钮2设置监听事件
//        button2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Log.d("TAG", "点击了button2");
//            }
//        });

        /**
         * 结论验证1：在回调onTouch()里返回false
         */
        // 1. 通过OnTouchListener()复写onTouch()，从而手动设置返回false
        buttom0.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                System.out.println("执行了onTouch(), 动作是:" + event.getAction());

                return false;
            }
        });

        // 2. 通过 OnClickListener（）为控件设置点击事件，为mOnClickListener变量赋值（即不为空），从而往下回调onClick（）
        buttom0.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                System.out.println("执行了onClick()");
            }

        });

/**
 * 结论验证2：在回调onTouch()里返回true
 */
//        // 1. 通过OnTouchListener()复写onTouch()，从而手动设置返回true
//        buttom0.setOnTouchListener(new View.OnTouchListener() {
//
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                System.out.println("执行了onTouch(), 动作是:" + event.getAction());
//
//                return true;
//            }
//        });
//
//        // 2. 通过 OnClickListener（）为控件设置点击事件，为mOnClickListener变量赋值（即不为空）
//        // 但由于dispatchTouchEvent（）返回true，即事件不再向下传递，故不调用onClick()）
//        buttom0.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                System.out.println("执行了onClick()");
//            }
//
//        });


    }
}
