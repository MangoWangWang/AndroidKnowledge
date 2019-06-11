package com.chanpay.lib_base.activity;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class LifecycleActivity extends AppCompatActivity {

    int i = 1;


    private String TAG ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TAG = this.getLocalClassName();
        ActionBar bar = getSupportActionBar();
        bar.setTitle("生命周期");
        Log.e(TAG, "onCreate: " + i );
        i++;
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e(TAG, "onStart: " + i );
        i++;

    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e(TAG, "onResume: "  + i);
        i++;
    }


    @Override
    protected void onPause() {
        super.onPause();
        Log.e(TAG, "onPause: " + i);
        i++;
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e(TAG, "onStop: " + i );
        i++;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "onDestroy: "+ i );
        i++;
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.e(TAG, "onRestart: " + i );
        i++;
    }
}
