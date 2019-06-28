package com.chanpay.lib_adv;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.Button;

/**
 * 文件名 : CustomButtom
 * 创建者 : MangoWang
 * 创建日期 : 2019/6/28 15:05
 * 微信 : MangoWaWang
 * 邮箱 : 763482205@qq.com
 * 描述 : TODO
 */
public class CustomButton extends android.support.v7.widget.AppCompatButton {

    public CustomButton(Context context) {
        super(context);
    }

    public CustomButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.e("TAG", "测试Enable");
        return super.onTouchEvent(event);
    }
}
