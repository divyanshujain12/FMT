package com.application.fmt.ApiUtils;

import com.application.fmt.Models.CheckOnlyModel;
import com.application.fmt.utils.NetworkError;
import com.google.gson.JsonObject;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class ApiHandler {
    private static final ApiHandler ourInstance = new ApiHandler();

    public static ApiHandler getInstance() {
        return ourInstance;
    }

    private ApiHandler() {
    }

    public Observable<CheckOnlyModel> validateEmailAddress(GetDataService getDataService, JsonObject requestJson) {
        return getDataService.checkEmailExist(requestJson)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

    }




    public interface GetNonArrayResponseCallback {
        <T> void onSuccess(T dataArray);

        void onError(NetworkError networkError);
    }



}