package com.chanpay.lib_base.LayoutUse;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.chanpay.lib_base.LayoutUse.framelayout.FrameLayoutActivity;
import com.chanpay.lib_base.LayoutUse.linearLayout.LinearLayoutActivity;
import com.chanpay.lib_base.R;

public class LayoutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout);
    }

    public void startFrameLayout(View view) {
        startActivity(new Intent(LayoutActivity.this, FrameLayoutActivity.class));
    }

    public void startAbsoluteLayout(View view) {
        startActivity(new Intent(LayoutActivity.this, AbsoluteLayoutActivity.class));
    }

    public void startLinearLayout(View view) {
        startActivity(new Intent(LayoutActivity.this, LinearLayoutActivity.class));
    }

    public void startRelativeLayout(View view) {
        startActivity(new Intent(LayoutActivity.this, RelativeLayoutActivity.class));
    }

    public void startTableLayout(View view) {
        startActivity(new Intent(LayoutActivity.this, TableLayoutActivity.class));
    }

    public void startConstranintLayout(View view) {
        startActivity(new Intent(LayoutActivity.this, ConstraintLayoutActivity.class));
    }
}
