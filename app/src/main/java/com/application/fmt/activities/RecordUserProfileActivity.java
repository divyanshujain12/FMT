package com.application.fmt.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;

import androidx.core.content.FileProvider;

import com.application.fmt.ApiUtils.ApiHandler;
import com.application.fmt.Models.CheckOnlyModel;
import com.application.fmt.R;
import com.application.fmt.globalClasses.BaseActivity;
import com.application.fmt.utils.NetworkError;

import java.io.File;
import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import rx.Subscription;

public class RecordUserProfileActivity extends BaseActivity {

    private String dir;
    int TAKE_PHOTO_CODE = 0;
    public static int count = 0;
    private File newfile;
    private Subscription subscription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record_user_profile);
        makeFolder();
        //onCreateActivity();
    }

    private void makeFolder() {
        dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) + "/picFolder/";
        File newdir = new File(dir);
        newdir.mkdirs();
    }

    public void takePicture(View v) {
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
            uploadFileToServer();
        }
    }

    private void uploadFileToServer() {
        RequestBody requestBody = RequestBody.create(MediaType.parse("*/*"), newfile);
        MultipartBody.Part fileToUpload = MultipartBody.Part.createFormData("user", newfile.getName(), requestBody);
        RequestBody mobile = RequestBody.create(MediaType.parse("text/plain"), "17147233172");

        subscription = ApiHandler.getInstance(getApplication()).uploadFile(CheckOnlyModel.class, fileToUpload, mobile, new ApiHandler.GetNonArrayResponseCallback() {
            @Override
            public <T> void onSuccess(T data) {

            }

            @Override
            public void onError(NetworkError networkError) {

            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (subscription != null && !subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }
}
