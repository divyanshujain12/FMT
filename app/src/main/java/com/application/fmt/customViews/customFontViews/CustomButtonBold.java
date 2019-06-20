package com.application.fmt.customViews.customFontViews;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatButton;

/**
 * Created by divyanshu.jain on 8/8/2017.
 */

public class CustomButtonBold extends AppCompatButton {

    public CustomButtonBold(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public CustomButtonBold(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CustomButtonBold(Context context) {
        super(context);
        init();
    }

    public void init() {
        Typeface tf = Typeface.createFromAsset(getContext().getAssets(), Font.FONT_BOLD);
        setTypeface(tf, Typeface.BOLD);

    }

}
