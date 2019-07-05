package com.application.fmt.activities;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import com.application.fmt.R;
import com.application.fmt.databinding.ActivitySignupOtpBinding;
import com.application.fmt.globalClasses.BaseActivity;
import com.application.fmt.viewModels.signupViewModels.SignupOtpViewModel;

public class SignupOtpActivity extends BaseActivity {
private ActivitySignupOtpBinding activitySignupOtpBinding;
private SignupOtpViewModel signupOtpViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activitySignupOtpBinding = DataBindingUtil.setContentView(this,R.layout.activity_signup_otp);
        signupOtpViewModel = ViewModelProviders.of(this).get(SignupOtpViewModel.class);
        activitySignupOtpBinding.setViewModel(signupOtpViewModel);
        //setContentView(R.layout.activity_signup_otp);
    }
}
