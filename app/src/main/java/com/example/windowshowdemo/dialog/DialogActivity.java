package com.example.windowshowdemo.dialog;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.windowshowdemo.PopNiceActivity;
import com.example.windowshowdemo.R;

public class DialogActivity extends AppCompatActivity {

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

        findViewById(R.id.btnNext).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DialogActivity.this, PopNiceActivity.class));
            }
        });
    }
}
