package com.application.fmt.customViews.customFontViews;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;

import com.broooapps.otpedittext2.OtpEditText;

public class CustomOtpEdittext extends OtpEditText {
    public CustomOtpEdittext(Context context) {
        super(context);
        init();
    }

    public CustomOtpEdittext(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CustomOtpEdittext(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void init() {
        Typeface tf = Typeface.createFromAsset(getContext().getAssets(), Font.FONT_BOLD);
        setTypeface(tf, Typeface.BOLD);

    }
}
