package com.application.fmt.customViews.customFontViews;

import android.content.Context;
import android.graphics.Typeface;

import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatCheckBox;

/**
 * Created by divyanshu.jain on 8/8/2017.
 */

public class CustomCheckboxRegular extends AppCompatCheckBox {
    public CustomCheckboxRegular(Context context) {
        super(context);
        init();
    }

    public CustomCheckboxRegular(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CustomCheckboxRegular(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void init() {
        Typeface tf = Typeface.createFromAsset(getContext().getAssets(), Font.FONT_REGULAR);
        setTypeface(tf, Typeface.NORMAL);

    }
}
