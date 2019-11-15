package com.example.windowshowdemo.like_ios;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.example.windowshowdemo.R;

/**
 * Created by apple on 2019-09-29.
 * description:仿ios弹出确认框
 */
public class TipDialog extends Dialog {
    public TipDialog(@NonNull Context context) {
        this(context,0);
    }


    public TipDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    public interface CancelClickListener{
        void onCancelClick(TipDialog tipDialog);
    }

    public interface EnsureClickListener{
        void onEnsureClick(TipDialog tipDialog);
    }

    public static class Builder{
        // 默认可点击退出
        private boolean closeOnTouchOutside = true;
        private Context context;
        private String title,message;
        private String cancelString = "取消";
        private String ensureString = "确定";
        private CancelClickListener mCancelClickListener;
        private EnsureClickListener mEnsureClickListener;

        public Builder(Context context) {
            this.context = context;
        }

        public Builder setTitle(String title){
            this.title = title;
            return this;
        }

        public Builder setMessage(String message){
            this.message = message;
            return this;
        }

        public Builder setCancelText(String cancelString){
            this.cancelString = cancelString;
            return this;
        }

        public Builder setEnsureText(String ensureString){
            this.ensureString = ensureString;
            return this;
        }

        public Builder setCancelCickListener(CancelClickListener cancelCickListener){
            this.mCancelClickListener = cancelCickListener;
            return this;
        }

        public Builder setEnsureClickListener(EnsureClickListener ensureClickListener){
            this.mEnsureClickListener = ensureClickListener;
            return this;
        }

        public Builder setCanCancelOutside(boolean canCancelOutside){
            this.closeOnTouchOutside = canCancelOutside;
            return this;
        }

        /**
         * 进行对象的构建
         * @return
         */
        public TipDialog build(){
            final  TipDialog tipDialog = new TipDialog(context,R.style.MyDialog);
            View localView =  LayoutInflater.from(context).inflate(R.layout.dialog_tip,null);
            TextView tvTitle =  localView.findViewById(R.id.tvTitle);
            TextView tvContent = localView.findViewById(R.id.tvContent);
            TextView tvCancel = localView.findViewById(R.id.btnCancel);
            TextView tvEnsure = localView.findViewById(R.id.btnEnsure);

            if (TextUtils.isEmpty(title)){
                tvTitle.setVisibility(View.GONE);
            }else {
                tvTitle.setVisibility(View.VISIBLE);
                tvTitle.setText(title);
            }
            if (TextUtils.isEmpty(message)){
                tvContent.setVisibility(View.GONE);
            }else {
                tvContent.setVisibility(View.VISIBLE);
                tvContent.setText(message);
            }
            tvCancel.setText(cancelString);
            tvEnsure.setText(ensureString);

            // 取消事件
            tvCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mCancelClickListener != null){
                        mCancelClickListener.onCancelClick(tipDialog);
                    }else {
                        tipDialog.dismiss();
                    }
                }
            });
            // 确定事件
            tvEnsure.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mEnsureClickListener != null){
                        mEnsureClickListener.onEnsureClick(tipDialog);
                    }else {
                        tipDialog.dismiss();
                    }
                }
            });
            tipDialog.setCanceledOnTouchOutside(closeOnTouchOutside);
            tipDialog.setContentView(localView);
            return tipDialog;
        }
    }
}

