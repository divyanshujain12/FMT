package com.application.fmt.fragments.signupFragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import com.application.fmt.R;
import com.application.fmt.databinding.FragmentStepTwoBinding;
import com.application.fmt.globalClasses.BaseFragment;
import com.application.fmt.utils.CommonUiUtils;
import com.application.fmt.viewModels.signupViewModels.StepTwoViewModel;


public class StepTwo extends BaseFragment {

    private FragmentStepTwoBinding fragmentStepTwoBinding;
    private StepTwoViewModel stepTwoViewModel;

    public StepTwo() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentStepTwoBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_step_two, container, false);
        stepTwoViewModel = ViewModelProviders.of(this).get(StepTwoViewModel.class);
        fragmentStepTwoBinding.setViewModel(stepTwoViewModel);
        setBaseAndroidViewModel(stepTwoViewModel);
        return fragmentStepTwoBinding.getRoot();
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initViews();
    }

    private void initViews() {
        fragmentStepTwoBinding.countrySP.setAdapter(CommonUiUtils.getInstance().setCustomSpinnerAdapter(getContext(), R.array.countries_array));
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
