package com.application.fmt.utils;

import android.content.Context;
import android.widget.ArrayAdapter;

import com.application.fmt.R;

import java.util.Objects;

public class CommonUiUtils {
    private static final CommonUiUtils ourInstance = new CommonUiUtils();

    public static CommonUiUtils getInstance() {
        return ourInstance;
    }

    private CommonUiUtils() {
    }

    public ArrayAdapter<CharSequence> setCustomSpinnerAdapter(Context context, int dataArrayResourceId) {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(Objects.requireNonNull(context),
                dataArrayResourceId, R.layout.custom_text_view_regular_adapter);
        adapter.setDropDownViewResource(R.layout.custom_drop_down_adapter);

        return adapter;
    }
}
