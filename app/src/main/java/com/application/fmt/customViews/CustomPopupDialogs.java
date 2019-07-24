package com.application.fmt.customViews;

import androidx.appcompat.app.AppCompatActivity;

import com.application.fmt.R;

import br.com.joinersa.oooalertdialog.Animation;
import br.com.joinersa.oooalertdialog.OoOAlertDialog;

public class CustomPopupDialogs {
    private static final CustomPopupDialogs ourInstance = new CustomPopupDialogs();

    public static CustomPopupDialogs getInstance() {
        return ourInstance;
    }

    private CustomPopupDialogs() {
    }

    public void showConfirmationDialog(AppCompatActivity context, String title, String message, OnDialogDismiss onDialogDismiss) {
        new OoOAlertDialog.Builder(context)
                .setTitle(title)
                .setMessage(message)
                .setAnimation(Animation.POP)
                .setCancelable(false)
                .setBackgroundColor(R.color.colorPrimaryDark)
                .setTitleColor(R.color.green)
                .setMessageColor(R.color.white)
                .setPositiveButtonColor(R.color.btn_secondary_yellow)
                .setNegativeButtonColor(R.color.error_tooltip)
                .setPositiveButtonTextColor(R.color.white)
                .setNegativeButtonTextColor(R.color.white)
                .setPositiveButton("OK", onDialogDismiss::Yes)
                .setNegativeButton("Cancel", onDialogDismiss::No)
                .build();
    }

    public interface OnDialogDismiss {
        void Yes();

        void No();
    }
}

