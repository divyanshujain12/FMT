package com.application.fmt.globalClasses;

import android.app.Application;

import androidx.appcompat.app.AppCompatActivity;

import com.application.fmt.ApiUtils.GetDataService;
import com.application.fmt.ApiUtils.RetrofitClientInstance;


public class MyApp extends Application {

    private GetDataService getDataService;

    public void onCreate() {
        super.onCreate();
        getDataService = RetrofitClientInstance.getRetrofitInstance(RetrofitClientInstance.BASE_URL).create(GetDataService.class);

    }

    private AppCompatActivity mCurrentActivity = null;

    public AppCompatActivity getCurrentActivity() {
        return mCurrentActivity;
    }

    public void setCurrentActivity(AppCompatActivity mCurrentActivity) {
        this.mCurrentActivity = mCurrentActivity;
    }

    public GetDataService getGetDataService() {
        return getDataService;
    }

    public void setGetDataService(GetDataService getDataService) {
        this.getDataService = getDataService;
    }
}