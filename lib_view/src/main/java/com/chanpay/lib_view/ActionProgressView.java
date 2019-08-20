package com.chanpay.lib_view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

/**
 * 创建者     gao hua
 * 创建时间   9/5 0005 15:51
 * 描述	      ${TODO}
 * <p>
 * 更新者     $Author$
 * 更新时间   $Date$
 * 更新描述   ${TODO}
 */

public class ActionProgressView extends RelativeLayout {


    private View view;
    private LinearLayout llContent;

    public ActionProgressView(Context context) {
        this(context, null);
    }

    public ActionProgressView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        view = View.inflate(context, R.layout.action_progress, this);
        llContent = view.findViewById(R.id.ll_content);
    }

    public void setDatas(List<RewardBean> datas) {
        llContent.removeAllViews();

        for (int i = 0; i < datas.size(); i++) {
            if (i == 0) {
                View tempView = new View(getContext());
                switch (datas.get(i).getState()) {
                    case "0":
                        tempView.setBackgroundResource(R.mipmap.icon_point_gray);
                        break;
                    case "1":
                        tempView.setBackgroundResource(R.mipmap.icon_point_green);
                        break;
                    case "2":
                        tempView.setBackgroundResource(R.mipmap.icon_point_red);
                        break;
                }
                LinearLayout.LayoutParams vlp = new LinearLayout.LayoutParams(
                        UIUtils.dip2px(getContext(), 11), UIUtils.dip2px(getContext(), 11));
                llContent.addView(tempView, vlp);
            } else {
                View tempViewPoint = new View(getContext());
                View tempViewLine = new View(getContext());
                switch (datas.get(i).getState()) {
                    case "0":
                            tempViewPoint.setBackgroundResource(R.mipmap.icon_point_gray);
                            tempViewLine.setBackgroundResource(R.mipmap.icon_line_gray);
                            break;
                        case "1":
                            tempViewPoint.setBackgroundResource(R.mipmap.icon_point_green);
                            tempViewLine.setBackgroundResource(R.mipmap.icon_line_green);
                            break;
                        case "2":
                            tempViewPoint.setBackgroundResource(R.mipmap.icon_point_red);
                            tempViewLine.setBackgroundResource(R.mipmap.icon_line_red);
                            break;
                }
                LinearLayout.LayoutParams vlp = new LinearLayout.LayoutParams(
                        UIUtils.dip2px(getContext(), 11), UIUtils.dip2px(getContext(), 11));
                LinearLayout.LayoutParams vlp2 = new LinearLayout.LayoutParams(
                        UIUtils.dip2px(getContext(), 22), UIUtils.dip2px(getContext(), 3));
                llContent.addView(tempViewLine, vlp2);
                llContent.addView(tempViewPoint, vlp);
            }
        }
    }
}
