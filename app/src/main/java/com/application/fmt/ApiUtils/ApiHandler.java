package com.application.fmt.ApiUtils;

import android.app.Application;

import com.application.fmt.customViews.CustomDialog;
import com.application.fmt.globalClasses.MyApp;
import com.application.fmt.utils.NetworkError;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import rx.Observable;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class ApiHandler {
    private static final ApiHandler ourInstance = new ApiHandler();
    private static MyApp myApplication;
    private static Gson gson;
    private static CustomDialog customDialog;

    public static ApiHandler getInstance(Application myApp) {
        myApplication = (MyApp) myApp;
        if (customDialog == null)
            customDialog = new CustomDialog(myApplication.getCurrentActivity());
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

    public Subscription sendOtp(Class targetClass, JsonObject requestJson, GetNonArrayResponseCallback getNonArrayResponseCallback) {

        return getNonArraySubscription(myApplication.getGetDataService().sendOtp(requestJson), targetClass, getNonArrayResponseCallback);
    }

    public Subscription register(Class targetClass, JsonObject requestJson, GetNonArrayResponseCallback getNonArrayResponseCallback) {

        return getNonArraySubscription(myApplication.getGetDataService().register(requestJson), targetClass, getNonArrayResponseCallback);
    }

    public Subscription sendLoginOtp(Class targetClass, JsonObject requestJson, GetNonArrayResponseCallback getNonArrayResponseCallback) {

        return getNonArraySubscription(myApplication.getGetDataService().loginOtp(requestJson), targetClass, getNonArrayResponseCallback);
    }

    public Subscription login(Class targetClass, JsonObject requestJson, GetNonArrayResponseCallback getNonArrayResponseCallback) {

        return getNonArraySubscription(myApplication.getGetDataService().login(requestJson), targetClass, getNonArrayResponseCallback);
    }

    public Subscription uploadFile(Class targetClass, MultipartBody.Part part, RequestBody requestBody, GetNonArrayResponseCallback getNonArrayResponseCallback) {
        return getNonArraySubscription(myApplication.getGetDataService().uploadFile(part, requestBody), targetClass, getNonArrayResponseCallback);
    }

    private Subscription getNonArraySubscription(Observable<JsonElement> observable, final Class targetClass, final GetNonArrayResponseCallback getNonArrayResponseCallback) {
        customDialog.show();
        return observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<JsonElement>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        customDialog.cancel();
                        getNonArrayResponseCallback.onError(new NetworkError(e));
                    }

                    @Override
                    public void onNext(JsonElement baseObservable) {
                        customDialog.cancel();
                        getNonArrayResponseCallback.onSuccess(gson.fromJson(baseObservable, targetClass));
                    }
                });
    }


    public interface GetNonArrayResponseCallback {
        <T> void onSuccess(T data);

        void onError(NetworkError networkError);
    }

    public static CustomDialog getCustomDialog() {
        return customDialog;
    }
}