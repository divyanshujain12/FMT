package com.application.fmt.customViews.customFontViews;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatTextView;


/**
 * Created by divyanshu.jain on 8/8/2017.
 */

public class CustomTextviewRegular extends AppCompatTextView {

    public CustomTextviewRegular(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public CustomTextviewRegular(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CustomTextviewRegular(Context context) {
        super(context);
        init();
    }

    public void init() {
        Typeface tf = Typeface.createFromAsset(getContext().getAssets(), Font.FONT_REGULAR);
        setTypeface(tf ,Typeface.NORMAL);

    }

}
