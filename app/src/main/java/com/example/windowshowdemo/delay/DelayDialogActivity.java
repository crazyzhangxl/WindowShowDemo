package com.example.windowshowdemo.delay;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.windowshowdemo.R;

/**
 * 弹出延迟2秒退出的dialog
 */
public class DelayDialogActivity extends AppCompatActivity {


    public static void show(Activity activity){
        Intent intent = new Intent(activity,DelayDialogActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delay_dialog);

        findViewById(R.id.btnQuit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
