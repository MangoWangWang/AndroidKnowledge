package com.chanpay.lib_view;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * 文件名 : ImageNumberView
 * 创建者 : MangoWang
 * 创建日期 : 2019/6/13 15:30
 * 微信 : MangoWaWang
 * 邮箱 : 763482205@qq.com
 * 描述 : 将字符串转变成为图片数字
 */
public class ImageNumberView extends FrameLayout {
    private ImageView number_ge;
    private ImageView number_shi;
    private ImageView number_bai;
    private ImageView number_qian;
    private ImageView number_wan;
    private ImageView number_shi_wan;
    private ImageView number_bai_wan;
    private ImageView number_qian_wan;
    private ImageView number_yi;
    private ImageView number_shi_yi;
    private ImageView number_bai_yi;
    private ImageView number_point_one;
    private ImageView number_point_two;
    private List<ImageView> numberList = new ArrayList<>();
    private List<ImageView> numberPointList = new ArrayList<>();

    public ImageNumberView(Context context) {
        super(context);
    }

    public ImageNumberView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    private void initView(Context context) {
        View.inflate(context, R.layout.image_number, this);
        number_ge = findViewById(R.id.number_ge);
        number_shi = findViewById(R.id.number_shi);
        number_bai = findViewById(R.id.number_bai);
        number_qian = findViewById(R.id.number_qian);
        number_wan = findViewById(R.id.number_wan);
        number_shi_wan = findViewById(R.id.number_shi_wan);
        number_bai_wan = findViewById(R.id.number_bai_wan);
        number_qian_wan = findViewById(R.id.number_qian_wan);
        number_yi = findViewById(R.id.number_yi);
        number_shi_yi = findViewById(R.id.number_shi_yi);
        number_bai_yi = findViewById(R.id.number_bai_yi);
        number_point_one = findViewById(R.id.number_point_one);
        number_point_two = findViewById(R.id.number_point_two);

        numberList.add(number_ge);
        numberList.add(number_shi);
        numberList.add(number_bai);
        numberList.add(number_qian);
        numberList.add(number_wan);
        numberList.add(number_shi_wan);
        numberList.add(number_bai_wan);
        numberList.add(number_qian_wan);
        numberList.add(number_yi);
        numberList.add(number_shi_yi);
        numberList.add(number_bai_yi);
        numberPointList.add(number_point_one);
        numberPointList.add(number_point_two);
    }


    public void analyString(String numberString) {

        // 判断支付串是否为空
        if (TextUtils.isEmpty(numberString)) {
            Log.e("error", "analyString: ");
            return;
        }
        // 判断字符串中是否含有小数点
        if (!numberString.contains(".")) {
            // 默认最后两位填充
            number_point_one.setImageResource(R.mipmap.zero);
            number_point_two.setImageResource(R.mipmap.zero);

            // 去除非数字的字符
            numberString = numberString.replaceAll("[^\\d]+", "");
            // 获取前11位
            if (numberString.length() > 11) {
                numberString = numberString.substring(0, 11);
            }

            // 将String 转位Char类型数组
            int resId; // 默认资源id
            char[] numberC = numberString.toCharArray();
            for (int i = numberC.length - 1; i >= 0; i--) {
                switch (numberC[i]) {
                    case '1':
                        resId = R.mipmap.one;
                        break;
                    case '2':
                        resId = R.mipmap.two;
                        break;
                    case '3':
                        resId = R.mipmap.three;
                        break;
                    case '4':
                        resId = R.mipmap.four;
                        break;
                    case '5':
                        resId = R.mipmap.five;
                        break;
                    case '6':
                        resId = R.mipmap.six;
                        break;
                    case '7':
                        resId = R.mipmap.seven;
                        break;
                    case '8':
                        resId = R.mipmap.eight;
                        break;
                    case '9':
                        resId = R.mipmap.night;
                        break;
                    case '0':
                        resId = R.mipmap.zero;
                        break;
                    default:
                        resId = R.mipmap.zero;
                }
                numberList.get(numberC.length - 1 - i).setImageResource(resId);
            }
        } else {
            // 将字符串进行切割
            // 获取"."首次出现的位置
            int position = numberString.indexOf(".");
            String numberFirst = numberString.substring(0, position);
            String numberSecond = numberString.substring(position + 1);


            // 去除非数字的字符
            numberFirst = numberFirst.replaceAll("[^\\d]+", "");
            // 获取前11位
            if (numberFirst.length() > 11) {
                numberFirst = numberFirst.substring(numberFirst.length() - 11);
            }

            // 将String 转位Char类型数组
            int resId; // 默认资源id
            char[] numberC = numberFirst.toCharArray();
            for (int i = numberC.length - 1; i >= 0; i--) {
                switch (numberC[i]) {
                    case '1':
                        resId = R.mipmap.one;
                        break;
                    case '2':
                        resId = R.mipmap.two;
                        break;
                    case '3':
                        resId = R.mipmap.three;
                        break;
                    case '4':
                        resId = R.mipmap.four;
                        break;
                    case '5':
                        resId = R.mipmap.five;
                        break;
                    case '6':
                        resId = R.mipmap.six;
                        break;
                    case '7':
                        resId = R.mipmap.seven;
                        break;
                    case '8':
                        resId = R.mipmap.eight;
                        break;
                    case '9':
                        resId = R.mipmap.night;
                        break;
                    case '0':
                        resId = R.mipmap.zero;
                        break;
                    default:
                        resId = R.mipmap.zero;
                }
                numberList.get(numberC.length - 1 - i).setImageResource(resId);
            }

            // 去除非数字的字符
            numberSecond = numberSecond.replaceAll("[^\\d]+", "");
            // 获取前11位
            if (numberSecond.length() >= 2) {
                numberSecond = numberSecond.substring(0, 2);
            } else if (numberSecond.length() == 0) {
                number_point_one.setImageResource(R.mipmap.zero);
                number_point_two.setImageResource(R.mipmap.zero);
            }
            // 将String 转位Char类型数组
            int resIdSecond; // 默认资源id
            char[] numberCSecond = numberSecond.toCharArray();
            for (int i = 0; i < numberCSecond.length; i++) {
                switch (numberCSecond[i]) {
                    case '1':
                        resIdSecond = R.mipmap.one;
                        break;
                    case '2':
                        resIdSecond = R.mipmap.two;
                        break;
                    case '3':
                        resIdSecond = R.mipmap.three;
                        break;
                    case '4':
                        resIdSecond = R.mipmap.four;
                        break;
                    case '5':
                        resIdSecond = R.mipmap.five;
                        break;
                    case '6':
                        resIdSecond = R.mipmap.six;
                        break;
                    case '7':
                        resIdSecond = R.mipmap.seven;
                        break;
                    case '8':
                        resIdSecond = R.mipmap.eight;
                        break;
                    case '9':
                        resIdSecond = R.mipmap.night;
                        break;
                    case '0':
                        resIdSecond = R.mipmap.zero;
                        break;
                    default:
                        resIdSecond = R.mipmap.zero;
                }
                numberPointList.get(i).setImageResource(resIdSecond);
            }
        }

    }


}
