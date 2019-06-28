package com.application.fmt.viewModels.signupViewModels;

import android.app.Application;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;

import androidx.annotation.NonNull;

import com.application.fmt.ApiUtils.ApiHandler;
import com.application.fmt.Constants.JsonKeys;
import com.application.fmt.Models.CheckOnlyModel;
import com.application.fmt.Models.SignupRequestModel;
import com.application.fmt.activities.SignupActivity;
import com.application.fmt.customViews.customFontViews.CustomTextviewRegular;
import com.application.fmt.globalClasses.BaseAndroidViewModel;
import com.application.fmt.globalClasses.MyApp;
import com.application.fmt.utils.NetworkError;
import com.application.fmt.utils.RxBus;
import com.google.gson.JsonObject;

import rx.Observable;
import rx.Observer;
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
            Observable<CheckOnlyModel> checkOnlyModelObservable = ApiHandler.getInstance().validateEmailAddress(((MyApp) getApplication()).getGetDataService(), creteRequestJson());
            checkOnlyModelObservable.subscribe(new Observer<CheckOnlyModel>() {
                @Override
                public void onCompleted() {

                }

                @Override
                public void onError(Throwable e) {

                }

                @Override
                public void onNext(CheckOnlyModel checkOnlyModel) {
                    if (checkOnlyModel.getSuccess()) {
                        RxBus.getInstance().publish(signupRequestModel);
                        signupActivity.getWizard().navigateNext();
                    }
                }
            });
            //compositeSubscription.add(subscription);
        }
    }

    private JsonObject creteRequestJson() {
        JsonObject outerObject = new JsonObject();

        JsonObject innerObject = new JsonObject();
        innerObject.addProperty(JsonKeys.EMAIL, signupRequestModel.getEmail());
        outerObject.add(JsonKeys.USER, innerObject);


        return outerObject;
    }

    @Override
    public <T> void onSuccess(T dataArray) {
        if (((CheckOnlyModel) dataArray).getSuccess()) {
            RxBus.getInstance().publish(signupRequestModel);
            signupActivity.getWizard().navigateNext();
        }
    }

    @Override
    public void onError(NetworkError networkError) {

    }

    @Override
    public void onActivityDestroy() {
        if (compositeSubscription != null && !compositeSubscription.isUnsubscribed()) {
            compositeSubscription.unsubscribe();
        }
    }
}
