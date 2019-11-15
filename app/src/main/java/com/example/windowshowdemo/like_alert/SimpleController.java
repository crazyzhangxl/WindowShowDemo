package com.example.windowshowdemo.like_alert;

import android.app.Dialog;
import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.windowshowdemo.R;

/**
 * Created by apple on 2019-09-29.
 * description:
 */
public class SimpleController {
    private Context mContext;
    private TextView tvTitle,tvContent,tvChange;
    private SimpleListener mSimpleListener;
    private String mTitle,mContent;

    /**
     * 进行属性的设置
     * @param title
     */
    public void setTitle(String title){
        mTitle = title;
        if (tvTitle != null){
            tvTitle.setText(title);
        }
    }

    public void setContent(String content){
        mContent = content;
        if (tvContent != null){
            tvContent.setText(content);
        }
    }

    public void setSimpleListener(SimpleListener simpleListener){
        this.mSimpleListener = simpleListener;
    }

    public SimpleController(Context context) {
        mContext = context;
    }

    /**
     * 初始化view
     * @param dialog
     */
    public void installContent(Dialog dialog){
        View view = LayoutInflater.from(mContext).inflate(R.layout.dialog_simple, null, false);
        tvContent = view.findViewById(R.id.tvContent);
        tvTitle = view.findViewById(R.id.tvTitle);
        tvChange = view.findViewById(R.id.tvChange);
        Window window = dialog.getWindow();
        if (window != null){
            WindowManager.LayoutParams attributes = window.getAttributes();
            attributes.width = WindowManager.LayoutParams.MATCH_PARENT;
            attributes.height = WindowManager.LayoutParams.WRAP_CONTENT;
            window.setAttributes(attributes);
        }
        setupViews();
        initListener();
        dialog.setContentView(view);
    }

    private void setupViews() {
        if (!TextUtils.isEmpty(mTitle)){
            tvTitle.setText(mTitle);
        }

        if (!TextUtils.isEmpty(mContent)){
            tvContent.setText(mContent);
        }
    }

    private void initListener() {
        tvChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mSimpleListener != null){
                    mSimpleListener.onChangeListener();
                }
            }
        });
    }

    /**
     * 参数信息
     */
    public static class SimpleParams{
        public String title;
        public String content;
        public final Context mContext;
        public SimpleListener mSimpleListener;
        public SimpleParams(Context context){
            this.mContext = context;
        }

        public void apply(SimpleController controller){
            if (title != null){
                controller.setTitle(title);
            }

            if (content != null){
                controller.setContent(content);
            }

            controller.setSimpleListener(mSimpleListener);
        }
    }
}
