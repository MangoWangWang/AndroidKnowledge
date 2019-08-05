package com.chanpay.lib_view;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * 创建者     gao hua
 * 创建时间   9/5 0005 15:51
 * 描述	      ${TODO}
 * <p>
 * 更新者     $Author$
 * 更新时间   $Date$
 * 更新描述   ${TODO}
 */

public class CellProgressView extends RelativeLayout {


    private View view;
    private LinearLayout llContent;
    private TextView tvName;
    private TextView tvMoney;

    public CellProgressView(Context context) {
        this(context, null);
    }

    public CellProgressView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        view = View.inflate(context, R.layout.cell_progress_layout, this);
        tvName = view.findViewById(R.id.tv_name);
        tvMoney = view.findViewById(R.id.tv_money);
        llContent = view.findViewById(R.id.ll_content);


    }

    public void setTextAndProgress(String name, String money, int progress, int type) {
        tvMoney.setText(money);
        tvName.setText(name);
        for (int i = 1; i <= 10; i++) {
            View tempView = new View(getContext());

            if (i <= progress) {
                if (type == 0) {
                    tempView.setBackgroundResource(R.drawable.item_cell_one);
                } else {
                    tempView.setBackgroundResource(R.drawable.item_cell_two);
                }
            } else {
                tempView.setBackgroundResource(R.drawable.item_cell_nornal);
            }
            // 为子View设置布局参数
            LinearLayout.LayoutParams vlp = new LinearLayout.LayoutParams(
                    UIUtils.dip2px(35), UIUtils.dip2px(10));
            vlp.setMargins(UIUtils.dip2px(3), 0, 0, 0);
            llContent.addView(tempView, vlp);
        }
    }
}
