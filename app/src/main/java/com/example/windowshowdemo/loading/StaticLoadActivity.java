package com.example.windowshowdemo.loading;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.windowshowdemo.R;
import com.example.windowshowdemo.toast.MyToast;

/**
 * 静态进度条活动...
 */
public class StaticLoadActivity extends AppCompatActivity {

    private ProgressBar mProgressBar;
    private Button mBtnProgressBar;
    private ImageView ivProgress;
    private ProgressDrawable mProgressDrawable;
    public static void show(Activity activity){
        Intent intent = new Intent(activity,StaticLoadActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_static_load);
        initViews();
        initListener();
    }

    private void initListener() {
        mBtnProgressBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mProgressBar.getVisibility() == View.VISIBLE){
                    mProgressBar.setVisibility(View.GONE);
                }else {
                    mProgressBar.setVisibility(View.VISIBLE);
                }
            }
        });

        findViewById(R.id.btnNative).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(StaticLoadActivity.this, "发送成功", Toast.LENGTH_SHORT).show();
            }
        });

        findViewById(R.id.btnCustom).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyToast.showText(StaticLoadActivity.this,"发送成功").show();

            }
        });
    }

    private void initViews() {
        mProgressBar = findViewById(R.id.progressbar);
        mBtnProgressBar = findViewById(R.id.btnProgressBar);
        ivProgress = findViewById(R.id.ivProgress);

        mProgressDrawable = new ProgressDrawable();
        mProgressDrawable.setColor(0xff666666);
        ivProgress.setImageDrawable(mProgressDrawable);
        ((Animatable)(ivProgress.getDrawable())).start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ((Animatable)(ivProgress.getDrawable())).start();
    }
}
