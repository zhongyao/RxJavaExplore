package com.hongri.rxjava;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.hongri.rxjava.util.RxJavaCombineOperatorUtil;
import com.hongri.rxjava.util.RxJavaCreateOperatorsUtil;
import com.hongri.rxjava.util.RxJavaFilterOperatorUtil;

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

        /**
         * RxJava创建操作符
         */
        //from操作符
//        RxJavaCreateOperatorsUtil.fromOperator();

        //just操作符
//        RxJavaCreateOperatorsUtil.justOperator();
//        RxJavaCreateOperatorsUtil.rangeOperator();
//        RxJavaCreateOperatorsUtil.intervalOperator();
//        RxJavaCreateOperatorsUtil.repeatOperator();
//          RxJavaCreateOperatorsUtil.createOperator();
          RxJavaCreateOperatorsUtil.timerOperator();



        /**
         * RxJava合并操作符
         */
//        RxJavaCombineOperatorUtil.concatOperator();
//        RxJavaCombineOperatorUtil.zipOperator();
//        RxJavaCombineOperatorUtil.mergeOperator();




        /**
         * RxJava变换操作符
         */
        //map操作符
//        RxJavaTransformOperatorUtil.mapOperator();

        //flatMap操作符
//        RxJavaTransformOperatorUtil.flatMapOperator();




        /**
         * RxJava过滤操作符
         */
//        RxJavaFilterOperatorUtil.firstOperator();
//        RxJavaFilterOperatorUtil.lastOperator();
        RxJavaFilterOperatorUtil.takeOperator();
    }
}