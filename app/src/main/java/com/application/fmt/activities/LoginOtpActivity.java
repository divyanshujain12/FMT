package com.application.fmt.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import com.application.fmt.R;
import com.application.fmt.databinding.ActivityLoginOtpBinding;
import com.application.fmt.globalClasses.BaseActivity;
import com.application.fmt.viewModels.loginViewModels.LoginOtpViewModel;

public class LoginOtpActivity extends BaseActivity {

    ActivityLoginOtpBinding activityLoginOtpBinding;
    private LoginOtpViewModel loginOtpViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityLoginOtpBinding = DataBindingUtil.setContentView(this, R.layout.activity_login_otp);
        loginOtpViewModel = ViewModelProviders.of(this).get(LoginOtpViewModel.class);
        activityLoginOtpBinding.setViewModel(loginOtpViewModel);

    }
}
