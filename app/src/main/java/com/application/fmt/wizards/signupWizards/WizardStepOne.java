package com.application.fmt.wizards.signupWizards;

import com.application.fmt.fragments.signupFragments.StepOne;

import me.panavtec.wizard.WizardPage;

public class WizardStepOne extends WizardPage<StepOne> {
    @Override
    public StepOne createFragment() {
        return new StepOne();
    }
}