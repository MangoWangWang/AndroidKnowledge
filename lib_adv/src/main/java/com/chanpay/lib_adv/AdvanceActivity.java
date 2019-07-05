package com.chanpay.lib_adv;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.chanpay.lib_adv.CustomAnimation.CustomAnimationActivity;

public class AdvanceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advance);
    }

    public void startClickShare(View view) {
        startActivity(new Intent(AdvanceActivity.this,ShareClickActivity.class));
    }

    public void startCustomAnimationOrComponent(View view) {
        startActivity(new Intent(AdvanceActivity.this, CustomAnimationActivity.class));
    }
}
