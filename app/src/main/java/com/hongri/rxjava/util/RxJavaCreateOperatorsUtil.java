package com.hongri.rxjava.util;

import android.util.Log;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.BooleanSupplier;
import io.reactivex.rxjava3.functions.Consumer;

/**
 * RxJava ---  创建操作符
 */
public class RxJavaCreateOperatorsUtil {

    private static final String TAG = "RxJavaOperators";

    /**
     * from操作符：
     * 将其它种类的对象和数据类型转换为Observable
     */
    public static void fromOperator() {
        Integer[] items = {0, 1, 2, 3, 4, 5};
        Observable.fromArray(items).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Throwable {
                Log.d(TAG, "accept: " + integer);
            }
        });
    }

    /**
     * just操作符：
     * 创建一个发射指定值的Observable
     * <p>
     * Just类似于From，但是From会将数组或Iterable的数据取出然后逐个发射，
     * 而Just只是简单的原样发射，将数组或Iterable当做单个数据。
     */
    public static void justOperator() {
        Integer[] items = {0, 1, 2, 3, 4, 5};
        Observable.just(items).subscribe(new Observer<Integer[]>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                Log.d(TAG, "onSubscribe: " + d);
            }

            @Override
            public void onNext(Integer @NonNull [] items) {
                Log.d(TAG, "Next: " + Arrays.toString(items));
            }


            @Override
            public void onError(Throwable error) {
                Log.d(TAG, "Error: " + error.getMessage());
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "onComplete");
            }
        });
    }

    /**
     * range操作符：
     */
    public static void rangeOperator() {
        //发送从10开始的整数，发送4个(发到13)
        Observable.range(10, 4)
                .subscribe(integer -> Log.i(TAG, "" + integer));
        //发送从10开始的长整型数，发送6个(发到15)
        Observable.rangeLong(10, 6)
                .subscribe(integer -> Log.i(TAG, "" + integer));
    }

    /**
     * interval操作符：
     */
    public static void intervalOperator() {
        //每3秒发个自增整数
//        Observable.interval(3, TimeUnit.SECONDS);
        //初始延时1秒，每3秒发一个自增整数
//        Observable.interval(1, 3, TimeUnit.SECONDS);
        //初始延时2秒，后每1秒发一个从10开始的整数，发5个（发到14）停止
        Observable.intervalRange(10, 5, 2, 1, TimeUnit.SECONDS).subscribe(result -> Log.d(TAG, "result:" + result));
    }

    /**
     * repeat操作符：
     */
    public static void repeatOperator() {
        //一直重复
//        Observable.fromArray(1, 2, 3, 4).repeat();
        //重复发送5次
        Observable.fromArray(1, 2, 3, 4).repeat(5).subscribe(result -> Log.d(TAG, "result:" + result));
        //重复发送直到符合条件时停止重复
//        Observable.fromArray(1, 2, 3, 4).repeatUntil(new BooleanSupplier() {
//            @Override
//            public boolean getAsBoolean() throws Exception {
//                //自定判断条件，为true即可停止，默认为false
//                return false;
//            }
//        });

    }
}
