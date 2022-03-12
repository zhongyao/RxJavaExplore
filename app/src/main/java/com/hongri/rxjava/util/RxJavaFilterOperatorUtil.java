package com.hongri.rxjava.util;

import android.util.Log;

import com.hongri.rxjava.bean.Course;
import com.hongri.rxjava.bean.Student;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;

/**
 * RxJava ---  过滤操作符
 */
public class RxJavaFilterOperatorUtil {
    private static final String TAG = "RxJavaFilterOperator";

    /**
     * first操作符：
     * 发射数据源第一个数据，如果没有则发送默认值。
     */
    public static void firstOperator() {
        Observable<String> observable = Observable.just("A", "B", "C");
        Single<String> firstOrDefault = observable.first("D");
        firstOrDefault.subscribe(string -> Log.d(TAG, "result:" + string));
    }

    /**
     * last操作符：
     * 发射数据源最后一个数据，如果没有则发送默认值。
     */
    public static void lastOperator() {
        Observable<String> observable = Observable.just("A", "B", "C");
        observable.last("D").subscribe(string -> Log.d(TAG, "result:" + string));
    }

    /**
     * take/takeLast操作符：
     * take发射前n个元素。takeLast发射后n个元素
     */
    public static void takeOperator() {
        Observable<String> observable = Observable.just("A", "B", "C", "D", "E");
        observable.take(2).subscribe(string -> Log.d(TAG, "result:" + string));
        observable.takeLast(2).subscribe(string -> Log.d(TAG, "result:" + string));
    }
}
