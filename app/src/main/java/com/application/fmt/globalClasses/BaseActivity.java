package com.application.fmt.globalClasses;

import androidx.appcompat.app.AppCompatActivity;

public class BaseActivity extends AppCompatActivity {

    BaseAndroidViewModel baseAndroidViewModel;


    public void setBaseAndroidViewModel(BaseAndroidViewModel baseAndroidViewModel) {
        this.baseAndroidViewModel = baseAndroidViewModel;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        this.baseAndroidViewModel.onActivityDestroy();
    }
}
