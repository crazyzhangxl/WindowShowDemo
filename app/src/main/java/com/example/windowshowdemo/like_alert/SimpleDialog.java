package com.example.windowshowdemo.like_alert;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.example.windowshowdemo.R;

/**
 * Created by apple on 2019-09-29.
 * description: 简单的dialog
 */
public class SimpleDialog extends Dialog {
    private SimpleController mSimpleController;

    public SimpleDialog(@NonNull Context context) {
        this(context,0);
    }

    public SimpleDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
        mSimpleController = new SimpleController(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSimpleController.installContent(this);
    }


    public void setContent(String content){
        mSimpleController.setTitle(content);
    }

    /**
     * 参数构建的内部类
     */
    public static class Builder{
        private SimpleController.SimpleParams mParams;
        public Builder(Context context){
            mParams = new SimpleController.SimpleParams(context);
        }

        public Builder setTitle(String title){
            mParams.title = title;
            return this;
        }

        public Builder setContent(String content){
            mParams.content = content;
            return this;
        }

        public Builder setSimpleListener(SimpleListener simpleListener){
            mParams.mSimpleListener = simpleListener;
            return this;
        }

        public SimpleDialog build(){
            SimpleDialog simpleDialog = new SimpleDialog(mParams.mContext, R.style.MyDialog);
            mParams.apply(simpleDialog.mSimpleController);
            return simpleDialog;
        }
    }
}
