package com.application.fmt.customViews;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;

import com.application.fmt.R;
import com.vlad1m1r.lemniscate.funny.HeartProgressView;

public class CustomDialog extends Dialog {

    HeartProgressView heartProgressView;

    public CustomDialog(@NonNull Context context) {
        super(context);


        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.custom_dialog, null);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        heartProgressView = (HeartProgressView) view.findViewById(R.id.heartLoadingView);

        setCancelable(true);
        getWindow().setBackgroundDrawableResource(
                android.R.color.transparent);
        WindowManager.LayoutParams wlmp = getWindow().getAttributes();
        wlmp.gravity = Gravity.CENTER_HORIZONTAL;
        wlmp.height = 200;
        wlmp.width = 400;
        getWindow().setAttributes(wlmp);
        setTitle(null);
        setCancelable(false);
        setOnCancelListener(null);
        setContentView(view);
    }

    @Override
    public void show() {
        super.show();
    }

    @Override
    public void hide() {
        super.hide();

    }

    @Override
    protected void onStop() {
        super.onStop();

    }
}
