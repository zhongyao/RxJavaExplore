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
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;

/**
 * RxJava ---  变换操作符
 */
public class RxJavaTransformOperatorUtil {
    private static final String TAG = "RxJavaTransformOperator";

    /**
     * map操作符：
     * map操作符对原始Observable发射的每一项数据应用一个你选择的函数，然后返回一个发射这些结果的Observable。
     */
    public static void mapOperator() {
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<String> emitter) throws Throwable {
                //传入两条数据[String类型]
                emitter.onNext("1");
                emitter.onNext("2");
                emitter.onComplete();
            }
        }).map(new Function<String, Integer>() {
            @Override
            public Integer apply(String string) throws Throwable {
                return Integer.parseInt(string);
            }
        }).subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull Integer integer) {
                Log.d(TAG, "onNext: " + (integer + 1));
            }

            @Override
            public void onError(@NonNull Throwable e) {

            }

            @Override
            public void onComplete() {
                Log.d(TAG, "onComplete");
            }
        });
    }

    /**
     * flatMap操作符：
     * flatMap操作符使用一个指定的函数对原始Observable发射的每一项数据执行变换操作，这个函数返回一个本身也发射数据的Observable，
     * 然后FlatMap合并这些Observables发射的数据，最后将合并后的结果当做它自己的数据序列发射。
     */
    public static void flatMapOperator() {
        List<Student> students = new ArrayList<>();

        //学生1
        Student student1 = new Student();
        student1.name = "Jack";
        List<Course> courseList = new ArrayList<>();

        Course course = new Course();
        course.id = "3";
        course.name = "化学";
        courseList.add(course);

        Course course2 = new Course();
        course2.id = "4";
        course2.name = "物理";
        courseList.add(course2);
        student1.coursesList = courseList;

        //学生2
        Student student2 = new Student();
        student2.name = "Tom";
        List<Course> courseList2 = new ArrayList<>();

        Course course21 = new Course();
        course21.id = "33";
        course21.name = "英语";
        courseList2.add(course21);

        Course course22 = new Course();
        course22.id = "44";
        course22.name = "语文";
        courseList2.add(course22);
        student2.coursesList = courseList2;

        students.add(student2);

        /**
         * 正常写法
         */
        new Thread(new Runnable() {
            @Override
            public void run() {
                List<Student> studentList = students;
                for (Student student : studentList) {
                    List<Course> coursesList = student.coursesList;
                    for (Course course : coursesList) {
                        Log.d(TAG, "course:" + ((Course) course).name + " " + ((Course) course).id);
                    }
                }
            }
        }).start();

        /**
         * map写法
         */
        Observable.fromIterable(students).map(new Function<Student, List<Course>>() {
            @Override
            public List<Course> apply(Student student) throws Throwable {
                return student.coursesList;
            }
        }).subscribe(new Consumer<List<Course>>() {
            @Override
            public void accept(List<Course> courseList) throws Throwable {
                for (Course course : courseList) {
                    Log.d(TAG, "course:" + ((Course) course).name + " " + ((Course) course).id);
                }
            }
        });

        /**
         * flatMap写法
         */
        Observable.fromIterable(students).flatMap(new Function<Student, ObservableSource<?>>() {
            @Override
            public ObservableSource<?> apply(Student student) throws Throwable {
                return Observable.fromIterable(student.coursesList);
            }
        }).subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object course) throws Throwable {
                Log.d(TAG, "course:" + ((Course) course).name + " " + ((Course) course).id);
            }
        });
    }
}
