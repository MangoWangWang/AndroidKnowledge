package com.chanpay.lib_view;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.chanpay.lib_view.banner.MZBannerView;
import com.chanpay.lib_view.banner.holder.MZHolderCreator;
import com.chanpay.lib_view.banner.holder.MZViewHolder;

import java.util.ArrayList;
import java.util.List;

public class CustomViewActivity extends AppCompatActivity {

    RectProgressView progressView;
    ImageNumberView imageNumberView;
    MZBannerView mMZBanner;
    CircularProgressPlusView circularProgressView;
     FiveRuandProgressView fiveRuandProgressView;
     CellProgressView cellProgress;
     WheelCircleFiveView wheelCircleFive;
     ActionProgressView action_progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_view);
        progressView = findViewById(R.id.progressView);
        fiveRuandProgressView = findViewById(R.id.fiveRuandProgressView);
        circularProgressView = findViewById(R.id.CircularProgressView);
        wheelCircleFive = findViewById(R.id.wheelCircleFive);
        cellProgress = findViewById(R.id.cell_progress);
        action_progress = findViewById(R.id.action_progress);
        List<Double> present = new ArrayList<>();
        List<String> number = new ArrayList<>();
        present.add(0.1);
        present.add(0.1);
        present.add(0.1);
        present.add(0.1);
        number.add("88.00");
        number.add("88.00");
        number.add("88.00");
        number.add("88.00");
        progressView.setData(present, number);

        imageNumberView = findViewById(R.id.imageNumberView);
        imageNumberView.analyString("12345678901.12");


        mMZBanner = findViewById(R.id.banner);
        mMZBanner.setDuration(1500);
        List<Integer> list = new ArrayList<>();
        list.add(R.mipmap.banner1);
        list.add(R.mipmap.banner1);
        list.add(R.mipmap.banner1);


        // 设置数据
        mMZBanner.setPages(list, new MZHolderCreator<BannerViewHolder>() {
            @Override
            public BannerViewHolder createViewHolder() {
                return new BannerViewHolder();
            }
        });

        circularProgressView.setSweepAngle(270);


        List<Integer> datasss = new ArrayList<>();
        datasss.add(1);
        datasss.add(1);
        datasss.add(1);
        datasss.add(4);
        datasss.add(1);
        fiveRuandProgressView.setData(datasss,8);

        cellProgress.setTextAndProgress("啊旺旺","1000",8,0);
        wheelCircleFive.setDataDan(1,1,1,2,1,6);
        List<RewardBean>  datas = new ArrayList<>();
        datas.add(new RewardBean());
        datas.add(new RewardBean());
        datas.add(new RewardBean());
        datas.add(new RewardBean());
        action_progress.setDatas(datas);

    }


    public static class BannerViewHolder implements MZViewHolder<Integer> {
        private ImageView mImageView;

        @Override
        public View createView(Context context) {
            // 返回页面布局
            View view = LayoutInflater.from(context).inflate(R.layout.banner_item, null);
            mImageView = (ImageView) view.findViewById(R.id.banner_image);
            return view;
        }

        @Override
        public void onBind(Context context, int position, Integer data) {
            // 数据绑定
            mImageView.setImageResource(data);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        mMZBanner.start();
    }


}

