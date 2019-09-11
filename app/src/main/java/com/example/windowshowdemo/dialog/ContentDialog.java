package com.example.windowshowdemo.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

import com.example.windowshowdemo.R;

/**
 * Created by apple on 2019-09-10.
 * description: 内容dialog
 */
public class ContentDialog extends Dialog {
    public ContentDialog(@NonNull Context context) {
        super(context);
    }

    public ContentDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCancelable(true);
        setCanceledOnTouchOutside(true);
        setContentView(R.layout.dialog_content);
        Window window = getWindow();
        if (window != null){
            window.getDecorView().setPadding(0,0,0,0);
            WindowManager.LayoutParams lp = window.getAttributes();
            lp.width = WindowManager.LayoutParams.MATCH_PARENT;
            lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
            window.setAttributes(lp);
        }
    }
}
