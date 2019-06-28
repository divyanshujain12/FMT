package com.application.fmt.viewModels.signupViewModels;

import android.app.Application;

import androidx.annotation.NonNull;

import com.application.fmt.ApiUtils.ApiHandler;
import com.application.fmt.Models.SignupRequestModel;
import com.application.fmt.globalClasses.BaseAndroidViewModel;
import com.application.fmt.utils.NetworkError;
import com.application.fmt.utils.RxBus;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;


public class StepTwoViewModel extends BaseAndroidViewModel implements ApiHandler.GetNonArrayResponseCallback {

    private Disposable disposable;
    private SignupRequestModel signupRequestModel;

    public StepTwoViewModel(@NonNull Application application) {
        super(application);
        getSignupRequestModel();
    }


    private void getSignupRequestModel() {
        disposable = RxBus.getInstance().subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Exception {
                if (o instanceof SignupRequestModel) {
                    signupRequestModel = (SignupRequestModel) o;
                }
            }
        });
    }

    @Override
    public void onActivityDestroy() {
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }
    }


    @Override
    public <T> void onSuccess(T dataArray) {

    }

    @Override
    public void onError(NetworkError networkError) {

    }
}
