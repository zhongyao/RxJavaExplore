package com.hongri.rxjava.util;

import android.util.Log;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.BooleanSupplier;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;

/**
 * RxJava ---  创建操作符
 */
public class RxJavaCreateOperatorsUtil {

    private static final String TAG = "RxJavaOperators";


    /**
     * create操作符：
     * RxJava使用create方法来创建一个Observable，并为它定义事件触发规则。
     * 通过调用subscribe()方法不断将事件添加到任务队列中
     */
    public static void createOperator() {
        Observable<Integer> observable = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Integer> emitter) throws Throwable {
                emitter.onNext(1);
                emitter.onNext(3);
                emitter.onComplete();

            }
        });

        Observer<Integer> observer = new Observer<Integer>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                Log.d(TAG, "onSubscribe:" + d.toString());
            }

            @Override
            public void onNext(@NonNull Integer integer) {
                Log.d(TAG, "onNext:" + integer);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.d(TAG, "onError:" + e.getMessage());
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "onComplete");
            }
        };

        observable.subscribe(observer);

    }

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
     * 创建一个发射特定整数序列的Observable
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
     * 创建一个按固定时间间隔发射整数序列的Observable
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
     * repeat/repeatWhen/repeatUntil操作符：
     * 创建一个发射特定数据重复多次的Observable
     */
    public static void repeatOperator() {
        //一直重复
//        Observable.fromArray(1, 2, 3, 4).repeat();
        //1、repeat操作符重复发送5次
//        Observable.fromArray(1, 2, 3, 4).repeat(5).subscribe(result -> Log.d(TAG, "result:" + result));


        //2、repeatWhen操作符：相当于一个有条件的repeat
        Observable.fromArray(1, 2, 3, 4).repeatWhen(new Function<Observable<Object>, ObservableSource<?>>() {
            @Override
            public ObservableSource<?> apply(Observable<Object> observable) throws Throwable {
                //【1】只会轮询一次
//              return Observable.timer(3, TimeUnit.SECONDS);
//              return Observable.delay(3, TimeUnit.SECONDS);
                //【2】会持续轮询
                return observable.delay(3, TimeUnit.SECONDS);
            }
        }).subscribe(result -> Log.d(TAG, "repeatWhen --->result:" + result));


        //3、repeatUntil操作符：重复发送直到符合条件时停止重复
//        Observable.fromArray(1, 2, 3, 4).repeatUntil(new BooleanSupplier() {
//            @Override
//            public boolean getAsBoolean() throws Exception {
//                //自定判断条件，为true即可停止，默认为false
//                return false;
//            }
//        });

    }

    /**
     * timer操作符：
     * 创建一个Observable，它在一个给定的延迟后发射一个特殊的值。
     */
    public static void timerOperator() {
        //延时2s执行
//        Observable.timer(2000, TimeUnit.MILLISECONDS).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<Long>() {
//            @Override
//            public void accept(Long aLong) {
//                Log.d(TAG, "倒计时已结束");
//            }
//        });
        //间隔2s执行
        //原timer方法 Observable.timer(0,5,TimeUnit.SECONDS).subscribe()已过时,使用如下interval代替
        //take控制执行个数【即发送前n个数据】
        Disposable disposable = Observable.interval(2000, TimeUnit.MILLISECONDS, AndroidSchedulers.mainThread()).take(10).subscribe(new Consumer<Long>() {
            @Override
            public void accept(Long aLong) throws Throwable {
                Log.d(TAG, "accept:" + aLong);
            }
        });

        //stop时的调用
//        if (disposable != null && disposable.isDisposed()) {
//            disposable.dispose();
//            disposable = null;
//        }

    }
}
