package com.application.fmt.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;

import androidx.core.content.FileProvider;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import com.application.fmt.R;
import com.application.fmt.databinding.ActivityLoginOtpBinding;
import com.application.fmt.globalClasses.BaseActivity;
import com.application.fmt.viewModels.loginViewModels.LoginOtpViewModel;

import java.io.File;
import java.io.IOException;

public class LoginOtpActivity extends BaseActivity {

    ActivityLoginOtpBinding activityLoginOtpBinding;
    private LoginOtpViewModel loginOtpViewModel;
    private String dir;
    int TAKE_PHOTO_CODE = 0;
    public static int count = 0;
    private File newfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityLoginOtpBinding = DataBindingUtil.setContentView(this, R.layout.activity_login_otp);
        loginOtpViewModel = ViewModelProviders.of(this).get(LoginOtpViewModel.class);
        activityLoginOtpBinding.setViewModel(loginOtpViewModel);

    }

    public void makeFolder() {
        dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) + "/picFolder/";
        File newdir = new File(dir);
        newdir.mkdirs();
        takePicture();
    }

    private void takePicture() {
        count++;
        String file = dir + count + ".jpg";
        newfile = new File(file);
        try {
            newfile.createNewFile();
        } catch (IOException e) {
        }

        Uri outputFileUri = FileProvider.getUriForFile(this, getApplicationContext().getPackageName() + ".provider", newfile);
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);
        startActivityForResult(cameraIntent, TAKE_PHOTO_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == TAKE_PHOTO_CODE && resultCode == RESULT_OK) {
            loginOtpViewModel.uploadFileToServer(newfile);
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}

