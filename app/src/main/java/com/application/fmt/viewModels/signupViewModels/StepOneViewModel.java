package com.application.fmt.viewModels.signupViewModels;

import android.app.Application;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.application.fmt.ApiUtils.ApiHandler;
import com.application.fmt.Constants.JsonKeys;
import com.application.fmt.Models.SignupRequestModel;
import com.application.fmt.activities.SignupActivity;
import com.application.fmt.customViews.customFontViews.CustomTextviewRegular;
import com.application.fmt.globalClasses.MyApp;
import com.application.fmt.utils.NetworkError;

import org.json.JSONException;
import org.json.JSONObject;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

public class StepOneViewModel extends AndroidViewModel implements ApiHandler.GetNonArrayResponseCallback {
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
        //signupActivity.getWizard().navigateNext();
    }

    public void onSelectItem(AdapterView<?> parent, View view, int pos, long id) {
        if (view != null)
            this.signupRequestModel.setAge(((CustomTextviewRegular) view).getText().toString());

    }

    private void hitCheckEmailApi() {
        if (signupRequestModel.validationForSignUpStepOne(getApplication())) {
            Subscription subscription = ApiHandler.getInstance().validateEmailAddress(((MyApp) getApplication()).getGetDataService(), creteRequestJson(), this);
            compositeSubscription.add(subscription);
        }
    }

    private String creteRequestJson() {
        JSONObject outerObject = new JSONObject();
        try {
            JSONObject innerObject = new JSONObject();
            innerObject.put(JsonKeys.EMAIL, signupRequestModel.getEmail());
            outerObject.put(JsonKeys.USER, innerObject);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return outerObject.toString();
    }

    @Override
    public <T> void onSuccess(T dataArray) {

    }

    @Override
    public void onError(NetworkError networkError) {

    }
}
