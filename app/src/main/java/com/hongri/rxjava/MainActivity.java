package com.hongri.rxjava;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.hongri.rxjava.util.RxJavaUtil;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //基础应用
//        RxJavaUtil.useObservable();

        RxJavaUtil.useFlowable();

    }
}