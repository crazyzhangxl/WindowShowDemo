package com.example.windowshowdemo.dialog;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.windowshowdemo.PopNiceActivity;
import com.example.windowshowdemo.R;
import com.example.windowshowdemo.like_alert.SimpleDialog;
import com.example.windowshowdemo.like_alert.SimpleListener;
import com.example.windowshowdemo.like_ios.TipDialog;

public class DialogActivity extends AppCompatActivity {
    private TipDialog mQuitDialog;
    private SimpleDialog mSimpleDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);

        findViewById(R.id.btnSHow).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new ContentDialog(DialogActivity.this,R.style.ShowDialog).show();
            }
        });

        findViewById(R.id.iosDialog).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showQuitDialog();
            }
        });

        findViewById(R.id.btnAlert).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSimpleDialog();
            }
        });

    }

    private void showSimpleDialog(){
        if (mSimpleDialog == null){
            mSimpleDialog = new SimpleDialog.Builder(this)
                    .setTitle("主题")
                    .setContent("内容")
                    .setSimpleListener(new SimpleListener() {
                        @Override
                        public void onChangeListener() {
                            mSimpleDialog.setContent("我是内容2");
                        }
                    })
                    .build();
        }
        mSimpleDialog.show();
    }

    private void showQuitDialog(){
        if (mQuitDialog == null){
            mQuitDialog = new TipDialog.Builder(this)
                    .setMessage("合同暂未提交,确定退出？")
                    .setCanCancelOutside(true)
                    .setEnsureClickListener(new TipDialog.EnsureClickListener() {
                        @Override
                        public void onEnsureClick(TipDialog tipDialog) {
                            tipDialog.dismiss();
                        }
                    }).build();
        }
        mQuitDialog.show();
    }
}
