package com.application.fmt.wizards.signupWizards;

import com.application.fmt.fragments.signupFragments.StepTwo;

import me.panavtec.wizard.WizardPage;

public class WizardStepTwo extends WizardPage<StepTwo> {
        @Override
        public StepTwo createFragment() {
            return new StepTwo();
        }
    }