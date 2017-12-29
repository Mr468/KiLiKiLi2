package com.mk.kilikili.utils.manager;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;

import com.mk.kilikili.R;


/**
 * Created by Mr_468 on 2017/4/5.
 */

public class StringUtil {

    public static SpannableStringBuilder changeSpan(Context context, String str, int start, int end) {
        SpannableStringBuilder stringBuilder = new SpannableStringBuilder(str);
        ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(
                ContextCompat.getColor(context, R.color.color_Primary));
        stringBuilder.setSpan(foregroundColorSpan, start,
                end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        return stringBuilder;
    }
}
