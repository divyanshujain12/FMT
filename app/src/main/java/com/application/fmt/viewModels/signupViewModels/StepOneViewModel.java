package com.application.fmt.viewModels.signupViewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.application.fmt.activities.SignupActivity;

public class StepOneViewModel extends AndroidViewModel {
    private SignupActivity signupActivity;

    public StepOneViewModel(@NonNull Application application) {
        super(application);
    }

    public void onNextClick() {
        signupActivity.getWizard().navigateNext();
    }

    public void setSignupActivity(SignupActivity signupActivity) {
        this.signupActivity = signupActivity;
    }
}
