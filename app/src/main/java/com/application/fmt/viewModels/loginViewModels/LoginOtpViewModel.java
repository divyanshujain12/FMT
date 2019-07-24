package com.application.fmt.viewModels.loginViewModels;

import android.app.Application;

import androidx.annotation.NonNull;

import com.application.fmt.ApiUtils.ApiHandler;
import com.application.fmt.Constants.ApiKeys;
import com.application.fmt.Constants.Constants;
import com.application.fmt.Models.CheckOnlyModel;
import com.application.fmt.Models.LoginUserProfileModel;
import com.application.fmt.Models.User;
import com.application.fmt.activities.LoginOtpActivity;
import com.application.fmt.customViews.CustomPopupDialogs;
import com.application.fmt.globalClasses.BaseAndroidViewModel;
import com.application.fmt.globalClasses.MyApp;
import com.application.fmt.utils.CommonFunctions;
import com.application.fmt.utils.NetworkError;
import com.application.fmt.utils.RxBus;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.File;
import java.io.IOException;

import id.zelory.compressor.Compressor;
import io.reactivex.disposables.Disposable;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import rx.Subscription;

public class LoginOtpViewModel extends BaseAndroidViewModel {

    private Disposable disposable;
    private User user;
    private Subscription subscription;

    public LoginOtpViewModel(@NonNull Application application) {
        super(application);
        getUserModel();
    }

    private void getUserModel() {
        disposable = RxBus.getInstance().subscribe(o -> {
            if (o instanceof User) {
                user = (User) o;
                disposeDisposable();
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
                        user = loginUserProfileModel.getUser();
                        CommonFunctions.getInstance().showSuccessMessage(getApplication(), loginUserProfileModel.getMessage());
                        CustomPopupDialogs.getInstance().showConfirmationDialog(((MyApp) getApplication()).getCurrentActivity(), Constants.TITLE_TAKE_PHOTO, Constants.MESSAGE_TAKE_PHOTO, onDialogDismiss);
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
        if (user.loginOtpValidation(getApplication())) {
            user.setOtp("");
            ApiHandler.getInstance(getApplication()).sendLoginOtp(CheckOnlyModel.class, createRequestJsonForSendOtp(), new ApiHandler.GetNonArrayResponseCallback() {
                @Override
                public <T> void onSuccess(T data) {
                    if (data instanceof CheckOnlyModel) {
                        CheckOnlyModel checkOnlyModel = (CheckOnlyModel) data;
                        if (checkOnlyModel.getSuccess()) {
                            CommonFunctions.getInstance().showSuccessMessage(getApplication(), checkOnlyModel.getMessage());
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

    private CustomPopupDialogs.OnDialogDismiss onDialogDismiss = new CustomPopupDialogs.OnDialogDismiss() {
        @Override
        public void Yes() {
            ((LoginOtpActivity) ((MyApp) getApplication()).getCurrentActivity()).makeFolder();
        }

        @Override
        public void No() {
            ((MyApp) getApplication()).getCurrentActivity().onBackPressed();
        }
    };

    private JsonObject createRequestJsonForLogin() {
        Gson gson = new Gson();
        JsonElement jsonElement = gson.toJsonTree(user);
        JsonObject jsonObject = new JsonObject();
        jsonObject.add(ApiKeys.USER, jsonElement);

        return jsonObject;
    }

    private JsonObject createRequestJsonForSendOtp() {
        Gson gson = new Gson();
        JsonElement jsonElement = gson.toJsonTree(user);
        JsonObject jsonObject = new JsonObject();
        jsonObject.add(ApiKeys.USER, jsonElement);

        return jsonObject;
    }

    public void uploadFileToServer(File newfile) {
        try {
            newfile = new Compressor(getApplication()).compressToFile(newfile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        RequestBody requestBody = RequestBody.create(MediaType.parse("*/*"), newfile);
        MultipartBody.Part part = MultipartBody.Part.createFormData("profile_image", newfile.getName(), requestBody);
        RequestBody mobile = RequestBody.create(MediaType.parse("*/*"), user.getMobile());

        subscription = ApiHandler.getInstance(getApplication()).uploadFile(CheckOnlyModel.class, part, mobile, new ApiHandler.GetNonArrayResponseCallback() {
            @Override
            public <T> void onSuccess(T data) {
                if (data instanceof CheckOnlyModel) {
                    CheckOnlyModel checkOnlyModel = (CheckOnlyModel) data;
                    if (checkOnlyModel.getSuccess()) {
                        CommonFunctions.getInstance().showSuccessMessage(getApplication(), checkOnlyModel.getMessage());
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

    @Override
    public void onActivityDestroy() {
        disposeDisposable();
    }

    private void disposeDisposable() {
        if (disposable != null && !disposable.isDisposed())
            disposable.dispose();
        if (subscription != null && !subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }
}
