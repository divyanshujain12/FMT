package com.application.fmt.viewModels.signupViewModels;

import android.app.Application;
import android.view.View;
import android.widget.AdapterView;

import androidx.annotation.NonNull;

import com.application.fmt.ApiUtils.ApiHandler;
import com.application.fmt.Constants.JsonKeys;
import com.application.fmt.Models.CheckOnlyModel;
import com.application.fmt.Models.CountriesModel;
import com.application.fmt.Models.SignupRequestModel;
import com.application.fmt.globalClasses.BaseAndroidViewModel;
import com.application.fmt.utils.CommonFunctions;
import com.application.fmt.utils.NetworkError;
import com.application.fmt.utils.RxBus;
import com.google.gson.JsonObject;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;


public class StepTwoViewModel extends BaseAndroidViewModel implements ApiHandler.GetNonArrayResponseCallback {

    private Disposable disposable;
    private SignupRequestModel signupRequestModel;
    private CountriesModel countriesModel;

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
        if (signupRequestModel.validationForSignUpStepTwo(getApplication())) {
            ApiHandler.getInstance(getApplication()).validateMobile(CheckOnlyModel.class, creteRequestJson(), this);
        }
    }

    private void getCountriesFromServer() {
        ApiHandler.getInstance(getApplication()).getCountries(CountriesModel.class, new ApiHandler.GetNonArrayResponseCallback() {
            @Override
            public <T> void onSuccess(T data) {
                RxBus.getInstance().publish(data);
            }

            @Override
            public void onError(NetworkError networkError) {

            }
        });
    }


    public void onSelectItem(AdapterView<?> parent, View view, int pos, long id) {
        if (view != null)
            this.signupRequestModel.setCountryCode(countriesModel.getCountries().get(pos).getCountryCode());

    }

    private JsonObject creteRequestJson() {
        JsonObject outerObject = new JsonObject();
        JsonObject innerObject = new JsonObject();
        innerObject.addProperty(JsonKeys.MOBILE, signupRequestModel.getMobile());
        outerObject.add(JsonKeys.USER, innerObject);

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

    @Override
    public <T> void onSuccess(T data) {
        if (data instanceof CheckOnlyModel) {
            CheckOnlyModel checkOnlyModel = (CheckOnlyModel) data;
            if (checkOnlyModel.getSuccess()) {
                CommonFunctions.getInstance().showSuccessMessage(getApplication(), checkOnlyModel.getMessage());
                RxBus.getInstance().publish(signupRequestModel);
            } else {
                CommonFunctions.getInstance().showErrorMessage(getApplication(), checkOnlyModel.getMessage());
            }
        }
    }

    @Override
    public void onError(NetworkError networkError) {
        CommonFunctions.getInstance().showErrorMessage(getApplication(), networkError.getMessage());
    }
}
