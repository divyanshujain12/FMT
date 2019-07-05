package com.application.fmt.viewModels.signupViewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;

import com.application.fmt.Models.SignupRequestModel;
import com.application.fmt.globalClasses.BaseAndroidViewModel;
import com.application.fmt.utils.RxBus;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public class SignupOtpViewModel extends BaseAndroidViewModel {
    private Disposable disposable;
    private SignupRequestModel signupRequestModel;


    public ObservableField<String> formattedPasswordWithCode = new ObservableField<>();

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
                    formattedPasswordWithCode.set(getFormattedPhoneNumberWithCode());
                    disposeDisposable();
                }
            }
        });
    }

    private void disposeDisposable() {
        if (disposable != null && !disposable.isDisposed())
            disposable.dispose();
    }

    public String getFormattedPhoneNumberWithCode() {
        String mobileNumber = signupRequestModel.getMobile();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < mobileNumber.length(); i++) {
            if (i < mobileNumber.length() - 2)
                stringBuilder.append('*');
            else
                stringBuilder.append(mobileNumber.charAt(i));
        }
        return signupRequestModel.getCountryCode() + " " + stringBuilder.toString();
    }

    public ObservableField<String> getFormattedPasswordWithCode() {
        return formattedPasswordWithCode;
    }
}


