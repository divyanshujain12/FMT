package com.application.fmt.ApiUtils;

import android.app.Application;

import com.application.fmt.globalClasses.MyApp;
import com.application.fmt.utils.NetworkError;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import rx.Observable;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class ApiHandler {
    private static final ApiHandler ourInstance = new ApiHandler();
    private static MyApp myApplication;
    private static Gson gson;

    public static ApiHandler getInstance(Application myApp) {
        myApplication = (MyApp) myApp;
        if (gson == null)
            gson = new Gson();
        return ourInstance;
    }

    private ApiHandler() {
    }

    public Subscription validateEmailAddress(JsonObject requestJson, Class targetClass, GetNonArrayResponseCallback getNonArrayResponseCallback) {
        return getNonArraySubscription(myApplication.getGetDataService().checkEmailExist(requestJson), targetClass, getNonArrayResponseCallback);

    }

    public Subscription getCountries(Class targetClass, GetNonArrayResponseCallback getNonArrayResponseCallback) {
        return getNonArraySubscription(myApplication.getGetDataService().getCountries(), targetClass, getNonArrayResponseCallback);
    }

    public Subscription validateMobile(Class targetClass, JsonObject requestJson, GetNonArrayResponseCallback getNonArrayResponseCallback) {
        return getNonArraySubscription(myApplication.getGetDataService().checkMobileExist(requestJson), targetClass, getNonArrayResponseCallback);
    }


    private Subscription getNonArraySubscription(Observable<JsonElement> observable, final Class targetClass, final GetNonArrayResponseCallback getNonArrayResponseCallback) {
        return observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<JsonElement>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        getNonArrayResponseCallback.onError(new NetworkError(e));
                    }

                    @Override
                    public void onNext(JsonElement baseObservable) {

                        getNonArrayResponseCallback.onSuccess(gson.fromJson(baseObservable, targetClass));
                    }
                });
    }


    public interface GetNonArrayResponseCallback {
        <T> void onSuccess(T data);

        void onError(NetworkError networkError);
    }


}