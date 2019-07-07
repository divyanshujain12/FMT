package com.application.fmt.fragments.signupFragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import com.application.fmt.Adapters.SpinnerAdapter;
import com.application.fmt.Models.CountriesModel;
import com.application.fmt.Models.Country;
import com.application.fmt.R;
import com.application.fmt.databinding.FragmentStepTwoBinding;
import com.application.fmt.globalClasses.BaseFragment;
import com.application.fmt.utils.CommonUiUtils;
import com.application.fmt.utils.RxBus;
import com.application.fmt.viewModels.signupViewModels.StepTwoViewModel;
import com.somesh.permissionmadeeasy.enums.Permission;
import com.somesh.permissionmadeeasy.helper.PermissionHelper;

import java.util.ArrayList;

import io.reactivex.functions.Consumer;


public class StepTwo extends BaseFragment {

    private FragmentStepTwoBinding fragmentStepTwoBinding;
    private StepTwoViewModel stepTwoViewModel;
    private ArrayList<Country> countries;
    private SpinnerAdapter spinnerAdapter;
    private PermissionHelper permissionHelper;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        createViews(inflater, container);
        return fragmentStepTwoBinding.getRoot();
    }

    private void createViews(LayoutInflater inflater, ViewGroup container) {
        fragmentStepTwoBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_step_two, container, false);
        getCountriesList();
        stepTwoViewModel = ViewModelProviders.of(this).get(StepTwoViewModel.class);
        fragmentStepTwoBinding.setViewModel(stepTwoViewModel);
        setBaseAndroidViewModel(stepTwoViewModel);
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initViews();
    }

    private void initViews() {
        fragmentStepTwoBinding.countrySP.setAdapter(CommonUiUtils.getInstance().setCustomSpinnerAdapter(getContext(), R.array.countries_array));
        createRequestPermissionHelper();
        createAdapter(countries);


    }


    private void createRequestPermissionHelper() {
        permissionHelper = PermissionHelper.Builder()
                .with(this)
                .requestCode(101)
                .setPermissionResultCallback(stepTwoViewModel.getPermissionListener())
                .askFor(Permission.LOCATION)
                .rationalMessage("Permissions are required for app to work properly") //Optional
                .build();

        stepTwoViewModel.setPermissionHelper(permissionHelper);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        permissionHelper.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    private void getCountriesList() {
        RxBus.getInstance().subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Exception {
                if (o instanceof CountriesModel) {
                    countries = ((CountriesModel) o).getCountries();
                    createAdapter(countries);
                }
            }
        });
    }

    private void createAdapter(ArrayList<Country> countries) {
        if (spinnerAdapter == null) {
            spinnerAdapter = new SpinnerAdapter(getActivity());
            fragmentStepTwoBinding.countrySP.setAdapter(spinnerAdapter);
        } else {
            spinnerAdapter.addItems(countries);
            fragmentStepTwoBinding.countrySP.setAdapter(spinnerAdapter);
        }
    }
}
