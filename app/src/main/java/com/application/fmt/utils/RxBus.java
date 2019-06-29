package com.application.fmt.utils;

import androidx.annotation.NonNull;

import java.util.ArrayList;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.subjects.BehaviorSubject;

public class RxBus {
    private static final RxBus ourInstance = new RxBus();

    public static RxBus getInstance() {
        return ourInstance;
    }

    private RxBus() {
    }

    private static final BehaviorSubject<Object> behaviorSubject
            = BehaviorSubject.create();
    private static final BehaviorSubject<ArrayList<Object>> arrayListBehaviourSubject = BehaviorSubject.create();

    public Disposable subscribe(@NonNull Consumer<Object> action) {
        return behaviorSubject.subscribe(action);
    }

    public void publish(@NonNull Object message) {
        behaviorSubject.onNext(message);
    }

    public Disposable subscribeForArray(@NonNull Consumer<ArrayList<Object>> action) {
        return arrayListBehaviourSubject.subscribe(action);
    }

    public void publishForArray(@NonNull ArrayList<Object> message) {
        arrayListBehaviourSubject.onNext(message);
    }
}
