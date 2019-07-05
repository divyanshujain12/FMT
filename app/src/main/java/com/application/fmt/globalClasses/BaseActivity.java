package com.application.fmt.globalClasses;

import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

public class BaseActivity extends AppCompatActivity {

    public BaseActivity() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                ((MyApp) getApplication()).setCurrentActivity(BaseActivity.this);
            }
        }, 200);

    }

    BaseAndroidViewModel baseAndroidViewModel;

    public void setBaseAndroidViewModel(BaseAndroidViewModel baseAndroidViewModel) {
        this.baseAndroidViewModel = baseAndroidViewModel;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (this.baseAndroidViewModel != null)
            this.baseAndroidViewModel.onActivityDestroy();
    }
}
