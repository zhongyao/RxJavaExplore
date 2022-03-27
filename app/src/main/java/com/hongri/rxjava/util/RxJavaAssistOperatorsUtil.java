package com.hongri.rxjava.util;

import android.util.Log;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

/**
 * @author：hongri
 * @date：3/27/22
 * @description：RxJava辅助操作符
 */
public class RxJavaAssistOperatorsUtil {
    public static final String TAG = "RxJavaAssistOperators";
    /**
     * subscribeOn操作符：
     * 指定Observable自身在哪个调度器上执行
     */
    public static void subscribeOnOperator() {
        //举例参考observeOn操作符
    }

    /**
     * observeOn操作符：
     * 指定一个观察者在哪个调度器上观察这个Observable
     */
    public static void observeOnOperator() {
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<String> emitter) throws Throwable {
                //TODO 在此处进行网络请求的操作
                Log.d(TAG, ("subscribe ---> isMainThread:" + RxJavaUtil.isMainThread()));
                Thread.sleep(3000);
                emitter.onNext("request data finish");

            }
        }).subscribeOn(Schedulers.io())//指定被观察者中的方法在io线程中进行处理
                .observeOn(AndroidSchedulers.mainThread())//指定观察者接收数据在主线程中
                .subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull String data) {
                //TODO 在此处主线程中进行UI的更新
                Log.d(TAG, ("onNext ---> isMainThread:" + RxJavaUtil.isMainThread()));
                Log.d(TAG, "onNext ---> data:" + data);
            }

            @Override
            public void onError(@NonNull Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }
}
