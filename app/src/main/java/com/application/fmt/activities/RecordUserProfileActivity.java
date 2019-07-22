package com.application.fmt.activities;

import android.os.Bundle;

import com.application.fmt.R;
import com.application.fmt.videoRecorderUtils.BaseCameraActivity;

public class RecordUserProfileActivity extends BaseCameraActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record_user_profile);
        onCreateActivity();
    }
}
