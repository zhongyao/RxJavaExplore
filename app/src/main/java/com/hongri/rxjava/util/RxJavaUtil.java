package com.hongri.rxjava.util;

import android.util.Log;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;

public class RxJavaUtil {
    private static final String TAG = "RxJavaUtil";


    public static void initObserver() {
        //创建一个观察者
        Observer<String> observer = new Observer<String>() {

            @Override
            public void onSubscribe(@NonNull Disposable d) {
                Log.d(TAG, "onSubscribe:" + d.toString());
            }

            @Override
            public void onNext(@NonNull String s) {
                Log.d(TAG, "onNext:" + s);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.d(TAG, "onError:" + e.toString());
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "onComplete");
            }
        };

        //使用Observable.create()创建被观察者
//        Observable<String> observable = Observable.create(new ObservableOnSubscribe<String>() {
//            @Override
//            public void subscribe(@NonNull ObservableEmitter<String> emitter) throws Throwable {
//                Log.d(TAG, "subscribe:" + emitter.toString());
//                emitter.onNext("Hello");
//                emitter.onNext("World");
//                emitter.onComplete();
//            }
//        });

        //just操作符的功能就是将一个对象转化为Observable
//        Observable<String> observable = Observable.just("Hello, world!");

        //from接收一个对象数组，然后逐一发射给观察者。
        Observable<String> observable = Observable.fromArray("Hello"," world!");


        //订阅
        observable.subscribe(observer);
    }
}
