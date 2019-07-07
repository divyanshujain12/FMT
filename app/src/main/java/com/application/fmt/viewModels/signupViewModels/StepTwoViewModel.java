package com.application.fmt.viewModels.signupViewModels;

import android.app.Application;
import android.view.View;
import android.widget.AdapterView;

import androidx.annotation.NonNull;

import com.application.fmt.ApiUtils.ApiHandler;
import com.application.fmt.Constants.ApiKeys;
import com.application.fmt.Models.CheckOnlyModel;
import com.application.fmt.Models.CountriesModel;
import com.application.fmt.Models.SignupRequestModel;
import com.application.fmt.activities.LoginActivity;
import com.application.fmt.activities.SignupOtpActivity;
import com.application.fmt.globalClasses.BaseAndroidViewModel;
import com.application.fmt.globalClasses.MyApp;
import com.application.fmt.utils.CommonFunctions;
import com.application.fmt.utils.NetworkError;
import com.application.fmt.utils.RxBus;
import com.google.gson.JsonObject;
import com.somesh.permissionmadeeasy.helper.PermissionHelper;
import com.somesh.permissionmadeeasy.intefaces.PermissionListener;

import java.util.ArrayList;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;


public class StepTwoViewModel extends BaseAndroidViewModel implements ApiHandler.GetNonArrayResponseCallback {

    private Disposable disposable;
    private SignupRequestModel signupRequestModel;
    private CountriesModel countriesModel;
    private PermissionHelper permissionHelper;
    private boolean parternedCharitiesChecked = true;
    private boolean locationChecked = true;
    private boolean termAndConditionsChecked = true;
    public StepTwoViewModel(@NonNull Application application) {
        super(application);
        getRequestModel();
        getCountriesFromServer();
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

    public SignupRequestModel getSignupRequestModel() {
        return signupRequestModel;
    }

    public void onSignupClick() {
        if (parternedCharitiesChecked && locationChecked && termAndConditionsChecked) {
            permissionHelper.requestPermissions();
        } else {
            CommonFunctions.getInstance().showErrorMessage(getApplication(), "You need to check all checkbox");
        }


    }

    private void getCountriesFromServer() {
        ApiHandler.getInstance(getApplication()).getCountries(CountriesModel.class, new ApiHandler.GetNonArrayResponseCallback() {
            @Override
            public <T> void onSuccess(T data) {
                countriesModel = (CountriesModel) data;
                RxBus.getInstance().publish(countriesModel);
            }

            @Override
            public void onError(NetworkError networkError) {

            }
        });
    }

    public void onSelectItem(AdapterView<?> parent, View view, int pos, long id) {


        if (view != null && countriesModel != null)
            this.signupRequestModel.setCountryCode("+"+countriesModel.getCountries().get(pos).getCountryCode());

    }

    private JsonObject creteRequestJson() {
        JsonObject outerObject = new JsonObject();
        JsonObject innerObject = new JsonObject();
        innerObject.addProperty(ApiKeys.MOBILE, signupRequestModel.getMobile());
        outerObject.add(ApiKeys.USER, innerObject);

        return outerObject;
    }

    @Override
    public void onActivityDestroy() {
        disposeDisposable();
    }

    private void disposeDisposable() {
        if (disposable != null && !disposable.isDisposed())
            disposable.dispose();
    }

    public void goToSignInActivity() {
        CommonFunctions.getInstance().moveToNextActivity(((MyApp) getApplication()).getCurrentActivity(), LoginActivity.class);
    }
    @Override
    public <T> void onSuccess(T data) {
        if (data instanceof CheckOnlyModel) {
            CheckOnlyModel checkOnlyModel = (CheckOnlyModel) data;
            if (checkOnlyModel.getSuccess()) {
                sendOtp();
            } else {
                CommonFunctions.getInstance().showErrorMessage(getApplication(), checkOnlyModel.getMessage());
            }
        }
    }

    @Override
    public void onError(NetworkError networkError) {
        CommonFunctions.getInstance().showErrorMessage(getApplication(), networkError.getMessage());
    }

    private void sendOtp() {
        ApiHandler.getInstance(getApplication()).sendOtp(CheckOnlyModel.class, getRequestJsonForRequestOtp(), new ApiHandler.GetNonArrayResponseCallback() {
            @Override
            public <T> void onSuccess(T data) {
                if (data instanceof CheckOnlyModel) {
                    CheckOnlyModel checkOnlyModel = (CheckOnlyModel) data;
                        CommonFunctions.getInstance().showSuccessMessage(getApplication(), checkOnlyModel.getMessage());
                    if (checkOnlyModel.getSuccess()) {
                        RxBus.getInstance().publish(signupRequestModel);
                        CommonFunctions.getInstance().moveToNextActivity(((MyApp) getApplication()).getCurrentActivity(), SignupOtpActivity.class);
                    }
                }
            }

            @Override
            public void onError(NetworkError networkError) {

            }
        });
    }

    private JsonObject getRequestJsonForRequestOtp() {
        JsonObject outerJsonObject = new JsonObject();
        JsonObject innerJsonObject = new JsonObject();
        innerJsonObject.addProperty(ApiKeys.MOBILE, this.signupRequestModel.getMobile());
        innerJsonObject.addProperty(ApiKeys.COUNTRY_CODE, this.signupRequestModel.getCountryCode());

        outerJsonObject.add(ApiKeys.USER, innerJsonObject);

        return outerJsonObject;
    }

    private PermissionListener permissionListener = new PermissionListener() {
        @Override
        public void onPermissionsGranted(int requestCode, ArrayList<String> acceptedPermissionList) {
            ApiHandler.getCustomDialog().cancel();
            if (signupRequestModel.validationForSignUpStepTwo(getApplication())) {
                ApiHandler.getInstance(getApplication()).validateMobile(CheckOnlyModel.class, creteRequestJson(), StepTwoViewModel.this);
            }
        }

        @Override
        public void onPermissionsDenied(int requestCode, ArrayList<String> deniedPermissionList) {
            ApiHandler.getCustomDialog().cancel();
            CommonFunctions.getInstance().showErrorMessage(getApplication(), "You need to allow to access location for sign up with FMT");
        }
    };

    public PermissionListener getPermissionListener() {
        return permissionListener;
    }

    public void setPermissionHelper(PermissionHelper permissionHelper) {
        this.permissionHelper = permissionHelper;
    }

    public boolean isParternedCharitiesChecked() {
        return parternedCharitiesChecked;
    }

    public boolean isLocationChecked() {
        return locationChecked;
    }

    public boolean isTermAndConditionsChecked() {
        return termAndConditionsChecked;
    }

    public void setParternedCharitiesChecked(boolean parternedCharitiesChecked) {
        this.parternedCharitiesChecked = parternedCharitiesChecked;
    }

    public void setLocationChecked(boolean locationChecked) {
        this.locationChecked = locationChecked;
    }

    public void setTermAndConditionsChecked(boolean termAndConditionsChecked) {
        this.termAndConditionsChecked = termAndConditionsChecked;
    }
}
