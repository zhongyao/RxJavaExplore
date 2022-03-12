package com.hongri.rxjava.util;


import android.util.Log;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.functions.BiFunction;
import io.reactivex.rxjava3.functions.Consumer;

/**
 * RxJava ---  合并操作符
 */
public class RxJavaCombineOperatorUtil {
    private static final String TAG = "RxJavaCombineOperator";

    /**
     * concat操作符:
     * 该操作符连接多个Observable的输出，就好像它们是一个Observable，
     * 第一个Observable发射的所有数据在第二个Observable发射的任何数据前面，以此类推。
     */
    public static void concatOperator() {
        Observable<Integer> observable1 = Observable.just(1, 2, 3, 4);
        Observable<Integer> observable2 = Observable.just(4, 5, 6);

        //发送所有
        Observable.concat(observable1, observable2).subscribe(integer -> {
            Log.d(TAG, "integer:" + integer);
        });
//
//        //发送前2个
//        Observable.concat(observable1, observable2).take(2).subscribe(integer -> {
//            Log.d(TAG, "integer:" + integer);
//        });

        //发送数据的第一个值，如果没有则发送默认值【0】
//        Observable.concat(observable1, observable2).first(0).subscribe(integer -> {
//            Log.d(TAG, "integer:" + integer);
//        });
//
//        //发送数据的最后一个值，如果没有则发送默认值【0】
//        Observable.concat(observable1, observable2).last(0).subscribe(integer -> {
//            Log.d(TAG, "integer:" + integer);
//        });
    }

    /**
     * zip操作符：
     * 将两个数据流进行指定的函数规则合并。
     */
    public static void zipOperator() {
        Observable<String> observable1 = Observable.just("A", "B", "C");
        Observable<String> observable2 = Observable.just("1", "2", "3");

        Observable.zip(observable1, observable2, (s1, s2) -> s1 + s2).subscribe(result -> Log.d(TAG, "result:" + result));
    }

    /**
     * merge/mergeWith操作符：
     * 可作用所有数据源类型，用于合并多个数据源到一个数据源。
     */
    public static void mergeOperator() {
        Observable<String> observable1 = Observable.just("My", "name");
        Observable<String> observable2 = Observable.just("is", "Lucas", "!");

        Observable.merge(observable1, observable2).subscribe(v -> Log.d(TAG, v));
        observable1.mergeWith(observable2).subscribe(v -> Log.d(TAG, v));
    }
}
