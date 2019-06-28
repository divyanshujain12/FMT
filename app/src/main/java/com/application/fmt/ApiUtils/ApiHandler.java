package com.application.fmt.ApiUtils;

import com.application.fmt.Models.CheckOnlyModel;
import com.application.fmt.utils.NetworkError;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class ApiHandler {
    private static final ApiHandler ourInstance = new ApiHandler();

    public static ApiHandler getInstance() {
        return ourInstance;
    }

    private ApiHandler() {
    }

    public Subscription validateEmailAddress(GetDataService getDataService, String requestJson, final GetNonArrayResponseCallback getNonArrayResponseCallback) {
        return getDataService.checkEmailExist(requestJson)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .onErrorResumeNext(new Func1<Throwable, Observable<? extends CheckOnlyModel>>() {
                    @Override
                    public Observable<? extends CheckOnlyModel> call(Throwable throwable) {
                        return Observable.error(throwable);
                    }
                }).subscribe(new Subscriber<CheckOnlyModel>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        getNonArrayResponseCallback.onError(new NetworkError(e));
                    }

                    @Override
                    public void onNext(CheckOnlyModel checkOnlyModel) {
                        getNonArrayResponseCallback.onSuccess(checkOnlyModel);
                    }
                });
    }


    public interface GetNonArrayResponseCallback {
        <T> void onSuccess(T dataArray);

        void onError(NetworkError networkError);
    }
}