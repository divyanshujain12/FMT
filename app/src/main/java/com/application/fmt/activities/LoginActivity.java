package com.application.fmt.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import com.application.fmt.R;
import com.application.fmt.databinding.ActivityLoginBinding;
import com.application.fmt.globalClasses.BaseActivity;
import com.application.fmt.viewModels.loginViewModels.LoginViewModel;
import com.somesh.permissionmadeeasy.enums.Permission;
import com.somesh.permissionmadeeasy.helper.PermissionHelper;
import com.somesh.permissionmadeeasy.intefaces.PermissionListener;

import java.util.ArrayList;

public class LoginActivity extends BaseActivity {

    ActivityLoginBinding activityLoginBinding;
    private LoginViewModel loginViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityLoginBinding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        loginViewModel = ViewModelProviders.of(this).get(LoginViewModel.class);
        activityLoginBinding.setViewModel(loginViewModel);
        createRequestPermissionHelper();
    }

    public void goToSignup(View v) {
        startActivity(new Intent(this, SignupActivity.class));
    }

    private void createRequestPermissionHelper() {
        PermissionHelper permissionHelper = PermissionHelper.Builder()
                .with(this)
                .requestCode(101)
                .setPermissionResultCallback(new PermissionListener() {
                    @Override
                    public void onPermissionsGranted(int requestCode, ArrayList<String> acceptedPermissionList) {

                    }

                    @Override
                    public void onPermissionsDenied(int requestCode, ArrayList<String> deniedPermissionList) {
                        onBackPressed();
                    }
                })
                .askFor(Permission.LOCATION, Permission.CAMERA, Permission.STORAGE, Permission.MICROPHONE)
                .rationalMessage("Permissions are required for app to work properly") //Optional
                .build();
        permissionHelper.requestPermissions();
        //stepTwoViewModel.setPermissionHelper(permissionHelper);
    }
}
