package com.application.fmt.customViews.customFontViews;

import android.content.Context;
import android.graphics.Typeface;

import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatEditText;


/**
 * Created by divyanshu.jain on 8/8/2017.
 */

public class CustomEditTextBold extends AppCompatEditText {

    public CustomEditTextBold(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public CustomEditTextBold(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CustomEditTextBold(Context context) {
        super(context);
        init();
    }

    public void init() {
        Typeface tf = Typeface.createFromAsset(getContext().getAssets(), Font.FONT_BOLD);
        setTypeface(tf, Typeface.BOLD);

    }
}
