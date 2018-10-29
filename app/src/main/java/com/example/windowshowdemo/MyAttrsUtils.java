package com.example.windowshowdemo;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.TypedValue;

/**
 * @author crazyZhangxl on 2018/10/28.
 * Describe:
 */
public class MyAttrsUtils {
    /**
     * attrs PopupWindow down or up icon
     *
     * @param mContext
     * @param attr
     * @return
     */
    public static Drawable getTypeValuePopWindowImg(Context mContext, int attr) {
        TypedValue typedValue = new TypedValue();
        int[] attribute = new int[]{attr};
        TypedArray array = mContext.obtainStyledAttributes(typedValue.resourceId, attribute);
        Drawable drawable = array.getDrawable(0);
        array.recycle();
        return drawable;
    }
}
