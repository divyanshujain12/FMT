package com.application.fmt.viewModels.signupViewModels;

import android.app.Application;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;

import androidx.annotation.NonNull;

import com.application.fmt.ApiUtils.ApiHandler;
import com.application.fmt.Constants.ApiKeys;
import com.application.fmt.Models.CheckOnlyModel;
import com.application.fmt.Models.SignupRequestModel;
import com.application.fmt.activities.SignupActivity;
import com.application.fmt.customViews.customFontViews.CustomTextviewRegular;
import com.application.fmt.globalClasses.BaseAndroidViewModel;
import com.application.fmt.utils.NetworkError;
import com.application.fmt.utils.RxBus;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

public class StepOneViewModel extends BaseAndroidViewModel implements ApiHandler.GetNonArrayResponseCallback {
    private String LOG_TAG = StepOneViewModel.class.getName();
    private SignupActivity signupActivity;

    private SignupRequestModel signupRequestModel;
    private CompositeSubscription compositeSubscription;

    public StepOneViewModel(@NonNull Application application) {
        super(application);
        signupRequestModel = new SignupRequestModel();
        signupRequestModel.setGender(false);
        compositeSubscription = new CompositeSubscription();

    }


    public SignupRequestModel getSignupRequestModel() {
        return signupRequestModel;
    }

    public void setSignupActivity(SignupActivity signupActivity) {
        this.signupActivity = signupActivity;
    }

    public void onNextClick() {
        Log.d(LOG_TAG, String.valueOf(signupRequestModel));
        hitCheckEmailApi();
    }

    public void onSelectItem(AdapterView<?> parent, View view, int pos, long id) {
        if (view != null)
            this.signupRequestModel.setAge(((CustomTextviewRegular) view).getText().toString());

    }

    private void hitCheckEmailApi() {
        if (signupRequestModel.validationForSignUpStepOne(getApplication())) {
            Subscription subscription = ApiHandler.getInstance(getApplication()).validateEmailAddress(creteRequestJson(), CheckOnlyModel.class, this);
            compositeSubscription.add(subscription);
        }
    }

    private JsonObject creteRequestJson() {
        Gson gson = new Gson();
        JsonElement jsonElement = gson.toJsonTree(signupRequestModel);
        JsonObject outerObject = new JsonObject();
        JsonObject innerObject = new JsonObject();
        innerObject.addProperty(ApiKeys.EMAIL, signupRequestModel.getEmail());
        outerObject.add(ApiKeys.USER, innerObject);

        return outerObject;
    }

    @Override
    public void onActivityDestroy() {
        if (compositeSubscription != null && !compositeSubscription.isUnsubscribed()) {
            compositeSubscription.unsubscribe();
        }
    }

    @Override
    public <T> void onSuccess(T data) {
        if (((CheckOnlyModel) data).getSuccess()) {
            RxBus.getInstance().publish(signupRequestModel);
            signupActivity.getWizard().navigateNext();
        }
    }

    @Override
    public void onError(NetworkError networkError) {

    }
}
