package com.chanpay.lib_view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class CustomViewActivity extends AppCompatActivity {

    RectProgressView progressView;
    ImageNumberView imageNumberView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_view);
        progressView =   findViewById(R.id.progressView);
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
        progressView.setData(present,number);

        imageNumberView = findViewById(R.id.imageNumberView);
        imageNumberView.analyString("12345678901.12");
    }
}
