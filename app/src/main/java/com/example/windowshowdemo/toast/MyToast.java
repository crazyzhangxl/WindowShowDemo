package com.example.windowshowdemo.toast;

import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.windowshowdemo.R;

/**
 * Created by apple on 2019-09-05.
 * description:
 */
public class MyToast {
    public static final int LENGTH_SHORT = Toast.LENGTH_SHORT;
    public static final int LENGTH_LONG = Toast.LENGTH_LONG;

    public static Toast showText(@NonNull Context context, @NonNull CharSequence message){
        return custom(context,message,LENGTH_SHORT);
    }

    public static Toast showText(@NonNull Context context, @NonNull CharSequence message,int duration){
        return custom(context,message,duration);
    }


    public static Toast custom(@NonNull Context context, @NonNull CharSequence message,int duration){
        final Toast currentToast = Toast.makeText(context, "", duration);
        final View toastLayout = ((LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE))
                .inflate(R.layout.toast_layout, null);
        final TextView toastTextView = toastLayout.findViewById(R.id.toast_text);
        toastTextView.setText(message);
        currentToast.setView(toastLayout);
        return currentToast;
    }
}
