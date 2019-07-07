package com.application.fmt.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import com.application.fmt.R;
import com.application.fmt.databinding.ActivityLoginBinding;
import com.application.fmt.globalClasses.BaseActivity;
import com.application.fmt.viewModels.loginViewModels.LoginViewModel;

public class LoginActivity extends BaseActivity {

    ActivityLoginBinding activityLoginBinding;
    private LoginViewModel loginViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityLoginBinding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        loginViewModel = ViewModelProviders.of(this).get(LoginViewModel.class);
        activityLoginBinding.setViewModel(loginViewModel);
    }

    public void goToSignup(View v) {
        startActivity(new Intent(this, SignupActivity.class));
    }

}
