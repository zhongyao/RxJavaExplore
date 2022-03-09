package com.hongri.rxjava;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.hongri.rxjava.util.RxJavaOperatorsUtil;
import com.hongri.rxjava.util.RxJavaUtil;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initRxMethod();
    }

    private void initRxMethod() {
        //基础应用
//        RxJavaUtil.useObservable();

//        RxJavaUtil.useFlowable();

        //just操作符
//        RxJavaOperatorsUtil.justOperator();

        //map操作符
        RxJavaOperatorsUtil.mapOperator();
    }
}