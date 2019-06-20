package com.application.fmt.customViews.customFontViews;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.Button;

import androidx.appcompat.widget.AppCompatButton;

/**
 * Created by divyanshu.jain on 8/8/2017.
 */

public class CustomButtonRegular extends AppCompatButton {

    public CustomButtonRegular(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public CustomButtonRegular(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CustomButtonRegular(Context context) {
        super(context);
        init();
    }

    public void init() {
        Typeface tf = Typeface.createFromAsset(getContext().getAssets(), Font.FONT_REGULAR);
        setTypeface(tf ,Typeface.NORMAL);

    }

}
