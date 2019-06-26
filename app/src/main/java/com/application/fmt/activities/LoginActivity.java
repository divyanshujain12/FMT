package com.application.fmt.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.application.fmt.R;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void goToSignup(View v) {
        startActivity(new Intent(this, SignupActivity.class));
    }
    public void goToHome(View v) {
        startActivity(new Intent(this, HomeActivity.class));
    }
}
