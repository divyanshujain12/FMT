package com.application.fmt.viewModels.signupViewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;

import com.application.fmt.ApiUtils.ApiHandler;
import com.application.fmt.Constants.ApiKeys;
import com.application.fmt.Models.SignupRequestModel;
import com.application.fmt.Models.SignupResponseModel;
import com.application.fmt.globalClasses.BaseAndroidViewModel;
import com.application.fmt.utils.NetworkError;
import com.application.fmt.utils.RxBus;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public class SignupOtpViewModel extends BaseAndroidViewModel implements ApiHandler.GetNonArrayResponseCallback {
    private Disposable disposable;


    private SignupRequestModel signupRequestModel;


    public ObservableField<String> formattedPasswordWithCode = new ObservableField<>();

    public SignupOtpViewModel(@NonNull Application application) {
        super(application);
        getRequestModel();
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

    public SignupRequestModel getSignupRequestModel() {
        return signupRequestModel;
    }

    public ObservableField<String> getFormattedPasswordWithCode() {
        return formattedPasswordWithCode;
    }

    public void onSubmitClick() {
        if (signupRequestModel.validateSignupOtp(getApplication())) {
            ApiHandler.getInstance(getApplication()).register(SignupResponseModel.class, createRequestJsonForSignup(), this);
        }
    }

    public void onResendOtpClick() {

    }

    private String getFormattedPhoneNumberWithCode() {
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


    private JsonObject createRequestJsonForSignup() {
        Gson gson = new Gson();
        JsonElement jsonElement = gson.toJsonTree(signupRequestModel);
        JsonObject jsonObject = new JsonObject();
        jsonObject.add(ApiKeys.USER, jsonElement);
        return jsonObject;
    }

    @Override
    public void onActivityDestroy() {
        disposeDisposable();
    }

    private void disposeDisposable() {
        if (disposable != null && !disposable.isDisposed())
            disposable.dispose();
    }

    @Override
    public <T> void onSuccess(T data) {

    }

    @Override
    public void onError(NetworkError networkError) {

    }
}


