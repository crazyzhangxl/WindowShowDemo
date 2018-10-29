package com.example.windowshowdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

/**
 * @author crazyZhangxl on 2018-10-29 8:37:02.
 * Describe:
 */

public class TopViewActivity extends AppCompatActivity {
    private TextView mTvTitle;
    private FrameLayout mFragmentContent;
    private FilterDropDownDialog mFilterDropDownDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_view);
        initViews();
        initListeners();
    }

    private void initListeners() {
        mTvTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mFilterDropDownDialog != null && !mFilterDropDownDialog.isHasShow()){
                    mFilterDropDownDialog.show();
                }else if (mFilterDropDownDialog != null && mFilterDropDownDialog.isHasShow()){
                    mFilterDropDownDialog.dismiss();
                }
            }
        });
    }

    private void initViews() {
        mTvTitle = findViewById(R.id.picture_title);
        mFragmentContent = findViewById(R.id.frameContent);
        if (mFilterDropDownDialog == null){
            mFilterDropDownDialog = new FilterDropDownDialog(this,mFragmentContent,mTvTitle);
        }
    }
}
