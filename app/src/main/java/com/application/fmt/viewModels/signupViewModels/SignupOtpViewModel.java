package com.application.fmt.viewModels.signupViewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.application.fmt.Models.SignupRequestModel;
import com.application.fmt.globalClasses.BaseAndroidViewModel;
import com.application.fmt.utils.RxBus;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public class SignupOtpViewModel extends BaseAndroidViewModel {
    private Disposable disposable;
    private SignupRequestModel signupRequestModel;

    public SignupOtpViewModel(@NonNull Application application) {
        super(application);
        getRequestModel();
    }

    @Override
    public void onActivityDestroy() {

    }

    private void getRequestModel() {
        disposable = RxBus.getInstance().subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Exception {
                if (o instanceof SignupRequestModel) {
                    signupRequestModel = (SignupRequestModel) o;
                    disposeDisposable();
                }
            }
        });
    }

    private void disposeDisposable() {
        if (disposable != null && !disposable.isDisposed())
            disposable.dispose();
    }
}
