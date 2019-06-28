package com.application.fmt.fragments.signupFragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.application.fmt.R;
import com.application.fmt.activities.SignupActivity;
import com.application.fmt.databinding.FragmentStepOneBinding;
import com.application.fmt.globalClasses.BaseFragment;
import com.application.fmt.utils.CommonUiUtils;
import com.application.fmt.viewModels.signupViewModels.StepOneViewModel;

public class StepOne extends BaseFragment {
    public StepOne() {

    }

    private FragmentStepOneBinding fragmentStepOneBinding;
    private StepOneViewModel stepOneViewModel;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        fragmentStepOneBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_step_one, container, false);
        stepOneViewModel = ViewModelProviders.of(this).get(StepOneViewModel.class);
        stepOneViewModel.setSignupActivity(((SignupActivity) getActivity()));
        fragmentStepOneBinding.setViewModel(stepOneViewModel);

        setBaseAndroidViewModel(stepOneViewModel);
        return fragmentStepOneBinding.getRoot();
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initViews();
    }

    private void initViews() {
        fragmentStepOneBinding.ageSP.setAdapter(CommonUiUtils.getInstance().setCustomSpinnerAdapter(getContext(), R.array.age_array));
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();

    }

}
