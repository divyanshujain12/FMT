package com.application.fmt.viewModels.loginViewModels;

import android.app.Application;

import androidx.annotation.NonNull;

import com.application.fmt.ApiUtils.ApiHandler;
import com.application.fmt.Constants.ApiKeys;
import com.application.fmt.Models.CheckOnlyModel;
import com.application.fmt.Models.User;
import com.application.fmt.activities.LoginOtpActivity;
import com.application.fmt.globalClasses.BaseAndroidViewModel;
import com.application.fmt.globalClasses.MyApp;
import com.application.fmt.utils.CommonFunctions;
import com.application.fmt.utils.NetworkError;
import com.application.fmt.utils.RxBus;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class LoginViewModel extends BaseAndroidViewModel {

    private User user = new User();

    public LoginViewModel(@NonNull Application application) {
        super(application);
    }


    public User getUser() {
        return user;
    }

    public void goToLoginOtp() {

        if (user.loginOtpValidation(getApplication())) {
            ApiHandler.getInstance(getApplication()).sendLoginOtp(CheckOnlyModel.class, createRequestJsonForSendOtp(), new ApiHandler.GetNonArrayResponseCallback() {
                @Override
                public <T> void onSuccess(T data) {
                    if (data instanceof CheckOnlyModel) {
                        CheckOnlyModel checkOnlyModel = (CheckOnlyModel) data;
                        if (checkOnlyModel.getSuccess()) {
                            CommonFunctions.getInstance().showSuccessMessage(getApplication(), checkOnlyModel.getMessage());
                            RxBus.getInstance().publish(user);
                            CommonFunctions.getInstance().moveToNextActivity(((MyApp) getApplication()).getCurrentActivity(), LoginOtpActivity.class);
                        } else {
                            CommonFunctions.getInstance().showErrorMessage(getApplication(), checkOnlyModel.getMessage());
                        }
                    }

                }

                @Override
                public void onError(NetworkError networkError) {
                    CommonFunctions.getInstance().showErrorMessage(getApplication(), networkError.getMessage());
                }
            });
        }


    }


    private JsonObject createRequestJsonForSendOtp() {
        Gson gson = new Gson();
        JsonElement jsonElement = gson.toJsonTree(user);
        JsonObject jsonObject = new JsonObject();
        jsonObject.add(ApiKeys.USER, jsonElement);

        return jsonObject;
    }

    @Override
    public void onActivityDestroy() {

    }


}
