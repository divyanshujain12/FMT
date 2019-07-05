package com.application.fmt.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.application.fmt.R;
import com.application.fmt.globalClasses.BaseActivity;
import com.application.fmt.wizards.signupWizards.WizardStepOne;
import com.application.fmt.wizards.signupWizards.WizardStepTwo;

import me.panavtec.wizard.Wizard;

public class SignupActivity extends BaseActivity {


    private Wizard wizard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        initViews();
    }

    private void initViews() {
        wizard = new Wizard.Builder(this, new WizardStepOne(), new WizardStepTwo())
                .containerId(R.id.content)
                .build();
        wizard.init();
    }

    public Wizard getWizard() {
        return wizard;
    }
}
