package com.application.fmt.utils;

import android.content.Context;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class CommonFunctions {
    private static final CommonFunctions ourInstance = new CommonFunctions();

    public static CommonFunctions getInstance() {
        return ourInstance;
    }

    private CommonFunctions() {
    }

    public void showSuccessMessage(Context context, String message) {
        Toast toast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
        View view = toast.getView();
        view.getBackground().setColorFilter(context.getColor(android.R.color.holo_green_dark), PorterDuff.Mode.SRC_IN);
        TextView text = view.findViewById(android.R.id.message);
        text.setTextColor(context.getColor(android.R.color.white));

        toast.show();
    }

    public void showErrorMessage(Context context, String message) {
        Toast toast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
        View view = toast.getView();
        view.getBackground().setColorFilter(context.getColor(android.R.color.holo_red_dark), PorterDuff.Mode.SRC_IN);
        TextView text = view.findViewById(android.R.id.message);
        text.setTextColor(context.getColor(android.R.color.white));

        toast.show();
    }

    public void moveToNextActivity(Context context, Class nextActivity) {
        Intent intent = new Intent(context, nextActivity);
        context.startActivity(intent);
    }
}
