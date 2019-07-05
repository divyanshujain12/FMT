package com.application.fmt.globalClasses;

import androidx.fragment.app.Fragment;

public class BaseFragment extends Fragment {


    BaseAndroidViewModel baseAndroidViewModel;


    public void setBaseAndroidViewModel(BaseAndroidViewModel baseAndroidViewModel) {
        this.baseAndroidViewModel = baseAndroidViewModel;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (this.baseAndroidViewModel != null)
            this.baseAndroidViewModel.onActivityDestroy();
    }
}
