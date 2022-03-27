package com.hongri.rxjava.util;

import android.os.Looper;
import android.util.Log;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.BackpressureStrategy;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.FlowableEmitter;
import io.reactivex.rxjava3.core.FlowableOnSubscribe;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;

/**
 * 参考：
 * https://blog.csdn.net/LucasXu01/article/details/105279367
 */
public class RxJavaUtil {
    private static final String TAG = "RxJavaUtil";


    public static void useObservable() {
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
        Observable<String> observable = Observable.create(new ObservableOnSubscribe<String>() {
            //这是新加入的方法，在订阅后发送数据之前，会首先调用这个方法，而Disposable可用于取消订阅
            @Override
            public void subscribe(@NonNull ObservableEmitter<String> emitter) throws Throwable {
                Log.d(TAG, "subscribe:" + emitter.toString());
                emitter.onNext("Hello");
                emitter.onNext("World");
                emitter.onComplete();
            }
        });

        //just操作符的功能就是将一个对象转化为Observable
//        Observable<String> observable = Observable.just("Hello, world!");

        //from接收一个对象数组，然后逐一发射给观察者。
//        Observable<String> observable = Observable.fromArray("Hello"," world!");


        //订阅
        observable.subscribe(observer);
    }


    /**
     * 支持被压:
     * Flowable是支持背压的，也就是说，一般而言，上游的被观察者会响应下游观察者的数据请求，
     * 下游调用request(n)来告诉上游发送多少个数据。这样避免了大量数据堆积在调用链上，使内存一直处于较低水平。
     */
    public static void useFlowable() {
        Flowable.range(0, 10).subscribe(new Subscriber<Integer>() {
            Subscription sub;

            //当订阅后，会首先调用这个方法，其实就相当于onStart()，传入的Subscription s参数可以用于请求数据或者取消订阅
            @Override
            public void onSubscribe(Subscription s) {
                Log.d(TAG, "onSubscribe start");
                sub = s;
                sub.request(1);
                Log.d(TAG, "onSubscribe end");

            }

            @Override
            public void onNext(Integer o) {
                Log.d(TAG, "onNext--->" + o);
                sub.request(1);

            }

            @Override
            public void onError(Throwable t) {
                t.printStackTrace();
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "onComplete");
            }
        });

        //也可以通过create创建
        Flowable.create(new FlowableOnSubscribe<Integer>() {
                            @Override
                            public void subscribe(FlowableEmitter<Integer> e) throws Exception {
                                e.onNext(1);
                                e.onNext(2);
                                e.onNext(3);
                                e.onNext(4);
                                e.onComplete();
                            }
                        }
                //需要指定背压策略
                , BackpressureStrategy.BUFFER);
    }

    /**
     * 是否是主线程
     * @return
     */
    public static boolean isMainThread() {
        return Looper.getMainLooper().getThread().getName().equals(Thread.currentThread().getName());
    }
}
