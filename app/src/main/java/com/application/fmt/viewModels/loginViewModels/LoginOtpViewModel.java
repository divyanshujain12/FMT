package com.application.fmt.viewModels.loginViewModels;

import android.app.Application;

import androidx.annotation.NonNull;

import com.application.fmt.ApiUtils.ApiHandler;
import com.application.fmt.Constants.ApiKeys;
import com.application.fmt.Models.LoginUserProfileModel;
import com.application.fmt.Models.User;
import com.application.fmt.activities.HomeActivity;
import com.application.fmt.globalClasses.BaseAndroidViewModel;
import com.application.fmt.globalClasses.MyApp;
import com.application.fmt.utils.CommonFunctions;
import com.application.fmt.utils.NetworkError;
import com.application.fmt.utils.RxBus;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public class LoginOtpViewModel extends BaseAndroidViewModel {

    private Disposable disposable;
    private User user;

    public LoginOtpViewModel(@NonNull Application application) {
        super(application);
        getUserModel();
    }

    private void getUserModel() {
        disposable = RxBus.getInstance().subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Exception {
                if (o instanceof User) {
                    user = (User) o;

                    disposeDisposable();
                }
            }
        });
    }

    public User getUser() {
        return user;
    }

    public void onSubmitClick() {
        if (user.loginValidation(getApplication())) {
            ApiHandler.getInstance(getApplication()).login(LoginUserProfileModel.class, createRequestJsonForLogin(), new ApiHandler.GetNonArrayResponseCallback() {
                @Override
                public <T> void onSuccess(T data) {
                    LoginUserProfileModel loginUserProfileModel = (LoginUserProfileModel) data;
                    if (loginUserProfileModel.getSuccess()) {
                        CommonFunctions.getInstance().showSuccessMessage(getApplication(), loginUserProfileModel.getMessage());
                        CommonFunctions.getInstance().moveToNextActivity(((MyApp) getApplication()).getCurrentActivity(), HomeActivity.class);
                    } else {
                        CommonFunctions.getInstance().showErrorMessage(getApplication(), loginUserProfileModel.getMessage());
                    }
                }

                @Override
                public void onError(NetworkError networkError) {
                    CommonFunctions.getInstance().showErrorMessage(getApplication(), networkError.getMessage());
                }
            });
        }
    }

    public void onResendOtpClick() {

    }


    private JsonObject createRequestJsonForLogin() {
        Gson gson = new Gson();
        JsonElement jsonElement = gson.toJsonTree(user);
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

}
